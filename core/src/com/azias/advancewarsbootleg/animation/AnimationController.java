package com.azias.advancewarsbootleg.animation;

import java.io.IOException;
import java.util.ArrayList;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AnimationController {
	protected ArrayList<Animation> animations;
	
	public AnimationController() {
		this.animations = new ArrayList<Animation>();
	}
	
	public void testNewAnimation(String id) {
		this.animations.add(new Animation(id));
	}
	
	public boolean startAnimation(String key) {
		for(int i=0; i<this.animations.size(); i++) {
			if(animations.get(i).id.equals(key)) {
				animations.get(i).start();
				return true;
			}
		}
		Gdx.app.log(Utils.getFormatedTime(), "Error: Unable to find the specified Animation. ("+key+")");
		return false;
	}
	
	public void startAllAnimations() {
		for(int i=0; i<this.animations.size(); i++) {
			if(animations.get(i).isFinished) {
				animations.get(i).start();
			}
		}
	}
	
	public boolean loadAnimation(String key, boolean renameAsKey) {
		//TODO: Check the formatVersion value
		Animation anim = new Animation();
		String json = Assets.animationFiles.get(key);
		if(json != null) {
			Gson gson = new Gson();
			anim = gson.fromJson(json, Animation.class);
			if(anim.load()) {
				if(renameAsKey) {
					anim.id = key;
				}
				this.animations.add(anim);
				return true;
			}
		}
		return false;
	}
	
	//Temporary
	public boolean loadAnimation(String key, boolean renameSpecial, String newName, int posX, int posY) {
		//TODO: Check the formatVersion value
		Animation anim = new Animation();
		String json = Assets.animationFiles.get(key);
		if(json != null) {
			Gson gson = new Gson();
			anim = gson.fromJson(json, Animation.class);
			if(anim.load()) {
				if(renameSpecial) {
					anim.id = newName;
				}
				//Gdx.app.log(Utils.getFormatedTime(), "id: "+anim.id);
				anim.position = new int[]{posX, posY};
				this.animations.add(anim);
				return true;
			} else {
				Gdx.app.log(Utils.getFormatedTime(), "Error: Failed to load the Animation.");
			}
		} else {
			Gdx.app.log(Utils.getFormatedTime(), "Error: Failed to get the Animation's json String.");
		}
		return false;
	}
	
	public void render(SpriteBatch batch) {
		for(int i=0; i<this.animations.size(); i++) {
			this.animations.get(i).render(batch);
		}
	}
	
	public void tick() {
		//TODO: Check for finished animations
		for(int i=0; i<this.animations.size(); i++) {
			if(!this.animations.get(i).isFinished) {
				this.animations.get(i).tick();
			} else {
				this.animations.remove(i);
			}
		}
	}
	
	public boolean exportAnimations() {
		for(int i=0; i<this.animations.size(); i++) {
			try {
				Gson gson = new GsonBuilder().create();
				String json = gson.toJson(this.animations.get(i));
				if(Utils.saveByteArray("./"+this.animations.get(i).id+".json", json.getBytes("UTF-8"), true)) {
					//Gdx.app.log(Utils.getFormatedTime(), "Your animation as been saved as: "+this.animations.get(i).id+".josn");
					//Gdx.app.log(Utils.getFormatedTime(), "File's size: "+json.getBytes("UTF-8").length);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
