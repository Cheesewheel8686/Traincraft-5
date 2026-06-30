package train.common.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import train.common.utils.interchangetransferreport.InterchangeTransferReportGenerator;

public class PacketClientSideEvent implements IMessage
{
    String theReportAsString;

    public PacketClientSideEvent()
    {

    }

    public PacketClientSideEvent(String reportAsString)
    {
        theReportAsString = reportAsString;
    }

    @Override
    public void fromBytes(ByteBuf bbuf)
    {
        theReportAsString = ByteBufUtils.readUTF8String(bbuf);
    }

    @Override
    public void toBytes(ByteBuf bbuf)
    {
        ByteBufUtils.writeUTF8String(bbuf, theReportAsString);
    }


    public static class Handler implements IMessageHandler<PacketClientSideEvent, IMessage>
    {
        @Override
        public IMessage onMessage(PacketClientSideEvent message, MessageContext context)
        {
            new InterchangeTransferReportGenerator().createRawConsistReport(message.theReportAsString);
            return null;
        }
    }
}
