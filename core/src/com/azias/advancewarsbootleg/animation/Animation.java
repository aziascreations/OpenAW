package com.azias.advancewarsbootleg.animation;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.annotations.Expose;

public class Animation extends Object {
	protected int formatVersion = 1;
	protected int[] position;
	protected int[] startingDrawables;
	protected String[] requiredAssetsKeys;
	@Expose(serialize = false, deserialize = false)
	protected TextureRegion[] textures;
	protected String id;
	protected boolean isScalable;
	protected ArrayList<Drawable> drawables;
	protected boolean isFinished = false;
	
	public Animation() {
		this.drawables = new ArrayList<Drawable>();
	}
	
	public Animation(String id) {
		this.id = id;
		this.drawables = new ArrayList<Drawable>();
		this.testInit();
	}
	
	public boolean load() {
		this.textures = new TextureRegion[this.requiredAssetsKeys.length];
		for(int i=0; i<this.requiredAssetsKeys.length; i++) {
			this.textures[i] = Assets.animationParts.get(this.requiredAssetsKeys[i]);
		}
		/*for(int j=0; j<this.startingDrawables.length; j++) {
			this.drawables.get(j).setup();
		}*/
		return true;
	}
	
	public void start() {
		for(int i=0; i<this.drawables.size(); i++) {
			int drawableId = this.drawables.get(i).id;
			for(int j=0; j<this.startingDrawables.length; j++) {
				//this.drawables.get(j).setup();
				if(drawableId==this.startingDrawables[j]) {
					j=this.startingDrawables.length;
					this.drawables.get(i).start(this);
				}
			}
		}
	}
	
	public void render(SpriteBatch batch) {
		for(int i=0; i<drawables.size(); i++) {
			this.drawables.get(i).render(batch, this);
		}
	}
	
	public void tick() {
		for(int i=0; i<drawables.size(); i++) {
			this.drawables.get(i).tick(this);
		}
	}
	
	@Override
	public String toString() {
		return "DataObject [formatVersion="+this.formatVersion+",id="+this.id+
				",position="+this.position+",drawables="+this.drawables+",requiredAssetsKeys="+this.requiredAssetsKeys+
				",startingDrawables="+this.startingDrawables+",isFinished="+this.isFinished+",isScalable="+this.isScalable+"]";
	}
	
	@Deprecated
	public void testInit() {
		this.position = new int[]{0,0};
		this.startingDrawables = new int[]{0};
		this.requiredAssetsKeys = new String[]{"doomEye_1", "doomEye_2", "doomEye_3", "doomEye_4"};
		Drawable tmpDrw = new Drawable();
		tmpDrw.position = new int[]{-1,-1};
		tmpDrw.assetId = 0;
		tmpDrw.timeToLive = 200;
		tmpDrw.isRenderedWhenFinished = false;
		tmpDrw.isPlaying = false;
		tmpDrw.finishActionId = new int[]{0};
		tmpDrw.finishActionParams = new int[][]{{0},{0}};
		tmpDrw.tickActionId = 0;
		tmpDrw.tickActionParams = new int[]{0};
		tmpDrw.untilActionId = 0;
		tmpDrw.untilActionParams = new int[]{0};
		this.drawables.add(tmpDrw);
	}
}
