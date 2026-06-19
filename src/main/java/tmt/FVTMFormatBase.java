package tmt;

import net.minecraft.entity.Entity;

import java.util.ArrayList;

/**
 * Basic Compatiblity class for FVTM Format Models
 * 
 * @author Ferdinand (FEX___96)
 *
 */
public class FVTMFormatBase extends ModelBase {
	
	public int textureX, textureY;
	public String name;
	public ArrayList<TurboList> groups = new ArrayList<TurboList>();

	public void addToCreators(String author){
		//
	}
	
	@SuppressWarnings("serial")
	public static class TurboList extends ArrayList<ModelRendererTurbo>{
		
		public final String name;
		
		public TurboList(String groupname){
			this.name = groupname;
		}
		
	}
	
	@Override
	public void render(){
		ArrayList<ModelRendererTurbo> runtimeSuppressed = ModelRendererTurboBatch.renderFVTMRuntimeGroups(this, groups, 0.0625F, false);
		try {
		for(TurboList list : groups){
			for(ModelRendererTurbo turbo : list) turbo.render();
		}
		}
		finally {
			ModelRendererTurboBatch.releaseRuntimeSuppressions(runtimeSuppressed);
		}
	}
	

	@Override
	public void render(Entity entity, float f0, float f1, float f2, float f3, float f4, float scale){
		ArrayList<ModelRendererTurbo> runtimeSuppressed = ModelRendererTurboBatch.renderFVTMRuntimeGroups(this, groups, scale, false);
		try {
		for(TurboList list : groups){
			for(ModelRendererTurbo turbo : list) turbo.render();
		}
		}
		finally {
			ModelRendererTurboBatch.releaseRuntimeSuppressions(runtimeSuppressed);
		}
	}

}
