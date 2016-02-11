package com.azias.advancewarsbootleg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController extends Object {
	private Music music;
	private String currentMusicKey;
	
	public MusicController() {
		
	}
	
	public void startMusic() {
		if(this.music!=null) {
			if(!this.music.isPlaying()) {
				this.music.setVolume(Datas.volumeMusic);
				this.music.play();
			}
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to start the music.");
		}
	}
	
	/**
	 * Change the music to the given one if found.
	 * @param key
	 */
	public void changeMusic(String key) {
		if(!key.equals(this.currentMusicKey)) {
			if(this.music!=null) {
				if(this.music.isPlaying()) {
					this.music.stop();
				}
				this.music.dispose();
			}
			this.music = Assets.loadMusic(key);
			this.music.setLooping(true);
			this.currentMusicKey = key;
			this.startMusic();
		}
	}
}
