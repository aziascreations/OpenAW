package com.azias.advancewarsbootleg.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.annotations.Expose;

public class Drawable extends Object {
	protected int id;
	protected int[] position;
	protected int assetId;
	protected long timeToLive = -1;
	@Expose(serialize = false, deserialize = false)
	protected long originalTTL = -1;
	protected long lastTick;
	protected boolean isPlaying, isRenderedWhenFinished;
	
	protected int tickActionId;
	protected int[] tickActionParams;
	
	protected int untilActionId;
	protected int[] untilActionParams;
	
	protected int[] finishActionId;
	protected int[][] finishActionParams;
	
	public Drawable() {
		
	}
	
	public void setup() {
		this.originalTTL = this.timeToLive;
	}
	
	public void start(Animation animation) {
		if(this.originalTTL == -1) {
			this.originalTTL = this.timeToLive;
		}
		this.timeToLive = this.originalTTL;
		if(this.position[0]==-1) {
			this.position[0]=animation.position[0];
		}
		if(this.position[1]==-1) {
			this.position[1]=animation.position[1];
		}
		this.isPlaying = true;
		this.lastTick = System.currentTimeMillis();
	}
	
	public boolean render(SpriteBatch batch, Animation animation) {
		if(!isPlaying && !isRenderedWhenFinished) {
			return false;
		}
		batch.draw(animation.textures[this.assetId], this.position[0], this.position[1]);
		return true;
	}
	
	public boolean tick(Animation animation) {
		if(!isPlaying) {
			return false;
		}
		
		//Checking if the animation has ended.
		if(this.untilActionId!=-1) {
			switch(this.untilActionId) {
			case 0:
				//Until TTL is lower or equal to zero.
				if(this.timeToLive <= 0) {
					this.finish(animation);
				}
				break;
			default:
				//Wrong until id
			}
		}
		
		//Ticking the animation.
		if(this.tickActionId!=-1) {
			switch(this.tickActionId) {
			case 0:
				//Still undefined
				break;
			default:
				//Wrong tick id
			}
		}
		if(this.timeToLive>0) {
			this.timeToLive -= System.currentTimeMillis()-this.lastTick;
			this.lastTick = System.currentTimeMillis();
		}
		return true;
	}
	
	private void finish(Animation animation) {
		for(int i=0; i<this.finishActionId.length; i++) {
			if(this.finishActionId[i]!=-1) {
				/* TODO: Implement these: 
				 * 	* Start another if counter is positive (Used for looping animations)
				 */
				switch(this.finishActionId[i]) {
				case 0:
					//Finish Animation
					animation.isFinished = true;
					break;
				case 1:
					//Start another Drawable
					//animation.drawables.get(this.finishActionParams[i][0]).resetTTL();
					animation.drawables.get(this.finishActionParams[i][0]).start(animation);
					//System.out.println("Hi 1");
					break;
				default:
					//Wrong finish id
				}
				this.isPlaying = false;
			}
		}
	}
	
	private void resetTTL() {
		this.timeToLive = this.originalTTL;
	}

	@Override
	public String toString() {
		return "DataObject [id="+this.id+",assetId="+this.assetId+",originalTTL="+this.originalTTL+
				",position="+this.position+",timeToLive="+this.timeToLive+
				",isPlaying="+this.isPlaying+",isRenderedWhenFinished="+this.isRenderedWhenFinished+
				",tickActionId="+this.tickActionId+",tickActionParams="+this.tickActionParams+
				",untilActionId="+this.untilActionId+",untilActionParams="+this.untilActionParams+
				",finishActionId="+this.finishActionId+",finishActionParams="+this.finishActionParams+"]";
	}

}
