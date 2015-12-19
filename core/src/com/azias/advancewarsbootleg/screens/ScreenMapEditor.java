package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.azias.advancewarsbootleg.gui.GuiEditorMenu;
import com.azias.advancewarsbootleg.gui.GuiEditorSelector;
import com.azias.advancewarsbootleg.map.Building;
import com.azias.advancewarsbootleg.map.BuildingGeneric;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenMapEditor extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private int[] pointerPosition;
	private EnumTerrainType terrain;
	private Building building;

	public ScreenMapEditor(AdvanceWarsBootleg game) {
		this(game, "custom", "iskander");
		//this(game, "custom", "develop03");
		//this(game, "error", "fy");
	}
	
	public ScreenMapEditor(AdvanceWarsBootleg game, String mapPath, String mapName) {
		this.game = game;
		this.pointerPosition = new int[] {0,0};
		this.terrain = EnumTerrainType.Plain;
		this.building = null;
		
		Datas.coMap.loadMap(mapPath, mapName);
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

	@Override
	public void render() {
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
		
		Datas.coGui.render(game.batch);
		game.batch.end();
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
			return true;
		case Keys.DOWN:
			if(pointerPosition[1]+1 < Datas.coMap.getMapSize()[1]) {
				pointerPosition[1]++;
			}
			return true;
		case Keys.LEFT:
			if(pointerPosition[0]-1 >= 0) {
				pointerPosition[0]--;
			}
			return true;
		case Keys.RIGHT:
			if(pointerPosition[0]+1 < Datas.coMap.getMapSize()[0]) {
				pointerPosition[0]++;
			}
			return true;
			
		case Keys.C:
			this.terrain = Datas.coMap.getTileTerrainType(this.pointerPosition[0], this.pointerPosition[1]);
			if(this.terrain == EnumTerrainType.Property || this.terrain == EnumTerrainType.Port) {
				this.building = Datas.coMap.getBuidingType(this.pointerPosition[0], this.pointerPosition[1]);
			} else {
				this.building = null;
			}
			return true;
		case Keys.V:
			Datas.coMap.setTileTerrainType(this.pointerPosition[0], this.pointerPosition[1], this.terrain, this.building);
			return true;
			
		case Keys.E:
			Datas.coMap.exportMap("export", "test01", "Test 1", "Azias");
			return true;
		
		case Keys.ESCAPE:
			if(Datas.coGui.doesGuiExists(3)) {
				Datas.coGui.killGui(3);
				Assets.soundMenuOut.play(Datas.volumeEffects);
			} else {
				Datas.coGui.createGui(3, new GuiEditorMenu(3));
				Assets.soundMenuIn.play(Datas.volumeEffects);
			}
			return true;
		case Keys.SPACE:
			if(Datas.coGui.doesGuiExists(2)) {
				Datas.coGui.killGui(2);
				Assets.soundMenuOut.play(Datas.volumeEffects);
			} else {
				int a = 0, b = 0;
				if(this.building!=null) {
					a = this.building.getFaction().getId();
					b = 1;
				}
				Datas.coGui.createGui(2, new GuiEditorSelector(2, b, a));
				Assets.soundMenuIn.play(Datas.volumeEffects);
			}
			return true;
		
		case Keys.PLUS:
			if(Assets.tileRenderSizeIndex!=Assets.tileRenderSize.length-1) {
				Assets.tileRenderSizeIndex++;
				Assets.renderOffset = new int[] {0,0};
			}
			return true;
		case Keys.MINUS:
			if(Assets.tileRenderSizeIndex!=0) {
				Assets.tileRenderSizeIndex--;
				Assets.renderOffset = new int[] {0,0};
			}
			return true;
		}
		return false;
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
			if(!Datas.coGui.isMouseClickLocked()) {
				if(diffX>=0 && diffY>=0) {
					int x = (int)Math.floor(diffX/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					int y = (int)Math.floor(diffY/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					//System.out.println("You clicked on the tile at: "+x+":"+y);
					if(Datas.coMap.getMapSize()[0]-1>=x && Datas.coMap.getMapSize()[1]-1>=y) {
						//System.out.println("Your action has been accepted");
						this.pointerPosition[0] = x;
						this.pointerPosition[1] = y;
						Datas.coMap.setTileTerrainType(this.pointerPosition[0], this.pointerPosition[1], this.terrain, this.building);
						return true;
					}
				}
			} else {
				String actionID = Datas.coGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
				if(actionID != null) {
					return this.actionPerformed(actionID);
				}
			}
			return false;
		case 1:
			if(!Datas.coGui.isMouseClickLocked()) {
				if(diffX>=0 && diffY>=0) {
					int x = (int)Math.floor(diffX/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					int y = (int)Math.floor(diffY/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
					if(Datas.coMap.getMapSize()[0]-1>=x && Datas.coMap.getMapSize()[1]-1>=y) {
						this.pointerPosition[0] = x;
						this.pointerPosition[1] = y;
						this.terrain = Datas.coMap.getTileTerrainType(this.pointerPosition[0], this.pointerPosition[1]);
						if(this.terrain == EnumTerrainType.Property || this.terrain == EnumTerrainType.Port) {
							this.building = Datas.coMap.getBuidingType(this.pointerPosition[0], this.pointerPosition[1]);
						} else {
							this.building = null;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		/*if(!Datas.coGui.isMouseClickLocked()) {
			int diffX = screenX-0;
			int diffY = screenY-0;
			if(diffX>=0 && diffY>=0) {
				int x = (int)Math.floor(diffX/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
				int y = (int)Math.floor(diffY/Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
				if(Datas.coMap.getMapSize()[0]-1>=x && Datas.coMap.getMapSize()[1]-1>=y) {
					this.pointerPosition[0] = x;
					this.pointerPosition[1] = y;
				}
			}
			
		}/**/
		return false;
	}
	
	private boolean actionPerformed(String actionID) {
		if(actionID.contains("tile")) {
			int chosenTile = Integer.parseInt(actionID.split("\\.")[1]);
			switch(chosenTile) {
			case 0:
				this.terrain = EnumTerrainType.Plain;
				break;
			case 1:
				this.terrain = EnumTerrainType.Forest;
				break;
			case 2:
				this.terrain = EnumTerrainType.Mountain;
				break;
			case 4:
				this.terrain = EnumTerrainType.Shoal;
				break;
			case 5:
				this.terrain = EnumTerrainType.Sea;
				break;
			case 7:
				this.terrain = EnumTerrainType.Road;
				break;
			default:
				this.terrain = EnumTerrainType.Plain;
				break;
			}
			this.building = null;
			return true;
		}
		
		if(actionID.contains("building")) {
			int actionSubID = Character.getNumericValue(actionID.charAt(actionID.length()-3));
			int teamID = Character.getNumericValue(actionID.charAt(actionID.length()-1));
			//System.out.println("Selected a building: "+actionID);
			//System.out.println("actionSubID: "+actionSubID);
			//System.out.println("teamID: "+teamID);
			
			if(actionSubID==0) {
				//HQ
				return true;
			} else if(actionSubID==1) {
				//Town
				this.terrain = EnumTerrainType.Property;
				this.building = new BuildingGeneric(0, -1, -1, teamID);
				return true;
			} else if(actionSubID==2) {
				//Factory
				this.terrain = EnumTerrainType.Property;
				this.building = new BuildingGeneric(1, -1, -1, teamID);
				return true;
			} else if(actionSubID==3) {
				//Airport
				this.terrain = EnumTerrainType.Property;
				this.building = new BuildingGeneric(2, -1, -1, teamID);
				return true;
			} else if(actionSubID==5) {
				//Antenna
				this.terrain = EnumTerrainType.Property;
				this.building = new BuildingGeneric(3, -1, -1, teamID);
				return true;
			}
		}
		
		if(actionID.equals("menu.save")) {
			Datas.coMap.exportMap("export", "test01", "Test 1", "Azias");
			return true;
		}
		if(actionID.equals("menu.exit")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMainMenu(this.game));
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
	public boolean scrolled(int amount) {
		return false;
	}
}
