package train.common.core.util;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;
import train.common.entity.rollingStock.EntityJukeBoxCart;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author broscolotos
 */
public class ReplacementStreamPlayer extends StreamPlayer implements StreamPlayerListener {

    private EntityJukeBoxCart source = null;

    static {
        Logger.getLogger("com.goxr3plus.streamplayer").setLevel(Level.OFF);
    }

    private String audioSource;

    private AudioInputStream getSafeAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
        String name = file.getName().toLowerCase();
        try {
            if (name.endsWith(".ogg")) {
                return new javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader().getAudioInputStream(file);
            } else if (name.endsWith(".mp3")) {
                return new javazoom.spi.mpeg.sampled.file.MpegAudioFileReader().getAudioInputStream(file);
            } else if (name.endsWith(".flac")) {
                return new org.jflac.sound.spi.FlacAudioFileReader().getAudioInputStream(file);
            }
        } catch (Throwable t) {
            System.err.println("[ReplacementStreamPlayer] Explicit file reader failed for " + name + ", falling back to AudioSystem...");
        }
        return AudioSystem.getAudioInputStream(file);
    }

    private AudioInputStream getSafeAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        // We must wrap in a BufferedInputStream that supports mark/reset so readers can test the header
        InputStream streamToUse = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream);
        streamToUse.mark(1024 * 1024); // Mark 1MB buffer safely

        // Try Vorbis/Ogg
        try {
            return new javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader().getAudioInputStream(streamToUse);
        } catch (Throwable ignored) {
            streamToUse.reset();
        }

        // Try MP3
        try {
            return new javazoom.spi.mpeg.sampled.file.MpegAudioFileReader().getAudioInputStream(streamToUse);
        } catch (Throwable ignored) {
            streamToUse.reset();
        }

        // Try Flac
        try {
            return new org.jflac.sound.spi.FlacAudioFileReader().getAudioInputStream(streamToUse);
        } catch (Throwable ignored) {
            streamToUse.reset();
        }

        // Fallback to standard JVM system formats (WAV, AIFF, AU)
        return AudioSystem.getAudioInputStream(streamToUse);
    }

    @Override
    public void open(File file) throws StreamPlayerException {
        try {
            AudioInputStream safeStream = getSafeAudioInputStream(file);
            super.open(safeStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println("Unsupported audio source from " + file);
        }
    }

    @Override
    public void open(InputStream stream) throws StreamPlayerException {
        try {
            AudioInputStream safeStream = getSafeAudioInputStream(stream);
            super.open(safeStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println("Unsupported audio source from " + stream);
        }
    }

    void start() {
        try {
            addStreamPlayerListener(this);
            try {
                URL url = new URL(audioSource);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                InputStream raw = connection.getInputStream();
                BufferedInputStream buffered = new BufferedInputStream(raw);

                open(buffered);
            } catch (IOException e) {
                open(new File(audioSource));
            }
            play();

        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stopPlayer() {
        try {
            removeStreamPlayerListener(this);
            stop();

            System.out.println("[ReplacementStreamPlayer] Stream stopped and background thread released successfully.");
        } catch (Exception e) {
            System.err.println("[ReplacementStreamPlayer] Error during stop: " + e.getMessage());
        }
    }

    @Override
    public void opened(Object dataSource, Map<String, Object> properties) { }

    @Override
    public void progress(int nEncodedBytes, long microsecondPosition, byte[] pcmData, Map<String, Object> properties) {
        if (this.source.isDead || !this.source.isPlaying) {
            stopPlayer();
        }
    }


    @Override
    public void statusUpdated(StreamPlayerEvent event) { }

    /**
     * Creates a new StreamPlayer. Threaded boolean is mostly used for something that could take a stream, such as the
     * jukebox cart. That said, if you want a thread, then thread :)
     * @param audioFileName
     * @param threaded
     */
    public ReplacementStreamPlayer(String audioFileName, EntityJukeBoxCart cart,  boolean threaded) {
        this.audioSource = audioFileName;
        this.source = cart;
        if (threaded) {
            Thread networkThread = new Thread(this::start, "StreamPlayer-Network-Worker");
            networkThread.setDaemon(true);
            networkThread.start();
        } else {
            start();
        }
    }

}
