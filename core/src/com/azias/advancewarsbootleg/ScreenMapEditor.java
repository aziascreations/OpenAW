package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.gui.Gui;
import com.azias.advancewarsbootleg.gui.GuiSelectTile;
import com.azias.advancewarsbootleg.map.TerrainType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScreenMapEditor extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private BitmapFont font;
	private int[] pointerPosition;
	private TerrainType terrain;
	
	private boolean isGuiOpen = false;
	private Gui openedGui = null;

	public ScreenMapEditor(AdvanceWarsBootleg game) {
		this.game = game;	
		this.font = new BitmapFont();
		this.font.setColor(Color.RED);
		this.pointerPosition = new int[] {0,0};
		this.terrain = TerrainType.Plain;
		
		//TODO: Change the place where the loadMap function is casted.
		//Datas.coMap.loadMap("custom", "develop01");
		Datas.coMap.loadMap("custom", "develop03");
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void create() {
		//Doesn't seem to work...
	}

	public void update() {
		//TODO: Calculate the delta here ?
		//Use System.CurrentMillis instead.
	}

	public void draw() {
		//Gdx.gl.glClearColor(back.r, back.g, back.b, back.a);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		game.batch.begin();
		//Background
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		
		//Map
		Datas.coMap.render(game.batch);
		//Pointer
		int startingPoint = Gdx.graphics.getHeight()+Assets.renderOffset[0]-Assets.tileRenderSize[Assets.tileRenderSizeIndex];
		game.batch.draw(
				Assets.pointer,
				0+(pointerPosition[0]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]),
				startingPoint-(pointerPosition[1]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]),
				Assets.tileRenderSize[Assets.tileRenderSizeIndex],
				Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
		
		//Side Menu
		//game.batch.draw(Assets.arrowOpen,Gdx.graphics.getWidth()-Assets.arrowOpen.getWidth()*2,Gdx.graphics.getHeight()/2-Assets.arrowOpen.getHeight(),Assets.arrowOpen.getWidth()*2,Assets.arrowOpen.getHeight()*2);
		
		//Tests
		//font.draw(game.batch, "Hello World", 200, 100);
		if(this.isGuiOpen) {
			this.openedGui.render(game.batch);
		}
		game.batch.end();
	}

	@Override
	public void render() {
		draw();
	}

	@Override
	public void render(float delta) {
		update();
		render();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
        switch (keycode) {
        case Keys.UP:
        	if(pointerPosition[1]-1 >= 0) {
        		pointerPosition[1]--;
        	}
            break;
        case Keys.DOWN:
        	if(pointerPosition[1]+1 < Datas.coMap.getMapSize()[1]) {
        		pointerPosition[1]++;
        	}
            break;
        case Keys.LEFT:
        	if(pointerPosition[0]-1 >= 0) {
        		pointerPosition[0]--;
        	}
            break;
        case Keys.RIGHT:
        	if(pointerPosition[0]+1 < Datas.coMap.getMapSize()[0]) {
        		pointerPosition[0]++;
        	}
            break;
        case Keys.SPACE:
        	if(!this.isGuiOpen) {
        		this.isGuiOpen = true;
        		this.openedGui = new GuiSelectTile();
        	} else if(this.openedGui instanceof GuiSelectTile) {
        		this.isGuiOpen = false;
        		this.openedGui = null;
        	}
            break;
        case Keys.C:
        	this.terrain = Datas.coMap.getTileTerrainType(this.pointerPosition[0], this.pointerPosition[1]);
            break;
        case Keys.V:
        	Datas.coMap.setTileTerrainType(this.pointerPosition[0], this.pointerPosition[1], this.terrain);
            break;
            
        case Keys.E:
        	Datas.coMap.exportMap("export", "test01", "Test 1", "Azias");
            break;
            
        case Keys.PLUS:
        	if(Assets.tileRenderSizeIndex!=Assets.tileRenderSize.length-1) {
        		Assets.tileRenderSizeIndex++;
        		Assets.renderOffset = new int[] {0,0};
        	}
            break;
        case Keys.MINUS:
        	if(Assets.tileRenderSizeIndex!=0) {
        		Assets.tileRenderSizeIndex--;
        		Assets.renderOffset = new int[] {0,0};
        	}
            break;
        }
        return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//System.out.println("Mouse"+button+" Clicked at: "+screenX+":"+screenY);

		//This is used to fix the issue with lwjgl and libgdx starting point (topleft/bottomleft)
		//int correctedScreenY = Gdx.graphics.getHeight()-screenY; Forget it...
		//Was this used in the rendering part ?
		int diffX = screenX-0; //screenX - MapRenderStartPoint
		//int diffY = correctedScreenY-0; //correctedScreenY - MapRenderStartPoint
		int diffY = screenY-0; //correctedScreenY - MapRenderStartPoint
		//System.out.println("diffX/Y: "+diffX+":"+diffY);
		
		switch(button) {
		case 0:
			if(!this.isGuiOpen) {
				if(diffX>=0 && diffY>=0) {
					int x = (int)Math.floor(diffX/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					int y = (int)Math.floor(diffY/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					//System.out.println("You clicked on the tile at: "+x+":"+y);
					if(Datas.coMap.getMapSize()[0]-1>=x && Datas.coMap.getMapSize()[1]-1>=y) {
						//System.out.println("Your action has been accepted");
						this.pointerPosition[0] = x;
						this.pointerPosition[1] = y;
						Datas.coMap.setTileTerrainType(this.pointerPosition[0], this.pointerPosition[1], this.terrain);
						return true;
					}
				}
				return false;
			} else {
				this.openedGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
			}
			//Old stuff
			/*for(int y=0; y<Datas.coMap.getMapSize()[1]; y++) {
				for(int x=0; x<Datas.coMap.getMapSize()[0]; x++) {
					Rectangle rectTile = new Rectangle(0,0,Assets.tileRenderSize[Assets.tileRenderSizeIndex],Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
				}
			}
			return true;*/
		case 1:
			if(diffX>=0 && diffY>=0) {
				int x = (int)Math.floor(diffX/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
				int y = (int)Math.floor(diffY/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
				if(Datas.coMap.getMapSize()[0]-1>=x && Datas.coMap.getMapSize()[1]-1>=y) {
					this.pointerPosition[0] = x;
					this.pointerPosition[1] = y;
					this.terrain = Datas.coMap.getTileTerrainType(this.pointerPosition[0], this.pointerPosition[1]);
					return true;
				}
			}
			return false;
		case 3:
			//TODO: Open Tab
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
