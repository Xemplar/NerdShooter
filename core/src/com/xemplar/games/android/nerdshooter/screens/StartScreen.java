/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2015  Rohan Loomis
 *
 * This file is part of NerdShooter
 *
 * NerdShooter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License, or
 * any later version.
 *
 * NerdShooter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.screens;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.net.NetworkHandle;
import com.xemplar.games.android.nerdshooter.net.NetworkListener;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;
import org.json.JSONObject;

public class StartScreen implements Screen, InputProcessor {
    public static StartScreen instance;
	private float buttonHeight;
	
	protected Button levelExp;
    protected Button level1;
    protected Button level2;
    protected Button level3;
    protected Button level4;
    protected Button level5;
    protected Button level6;
    protected Button level7;
    protected Button level8;
    protected Button level9;
    protected Button levelA;
    protected Button levelB;
    protected Button levelC;

    protected Button download;
	protected Button options;
    protected Button exit;
	
    protected SpriteBatch buttonRenderer;
    protected BitmapFont text;
	
	protected float width, height;
	protected Array<Button> buttons = new Array<Button>();

    private static Music aud;
    private static Texture tex;

    private NetworkHandle net;
	
	public StartScreen(){
        instance = this;

        tex = new Texture(Gdx.files.internal("images/options.png"));
        net = new NetworkHandle("The_Next_Guy", "loomis240");
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
		
		buttonRenderer.begin(); {
		    for(Button button : buttons){
                button.render(buttonRenderer);
            }
		} buttonRenderer.end();
	}

	public void resize(int width, int height) {
		NerdShooter.shooter.setCurrentScreen(NerdShooter.START_SCREEN);
		
		this.width = width;
		this.height = height;

		buttonHeight = height / 9F;
		
        float spacer = 10F;
		float buttonWidth = (width * ((3F / 4F) / 2F));
        
        text = NerdShooter.label;
		text.setColor(0, 0, 0, 1);
        
		levelExp = new Button(text, "External Level", (width / 2F) - (buttonWidth), height - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);
        
        level1 = new Button(text, "1", (width / 2F) - ((buttonWidth)), levelExp.y - ((buttonHeight + spacer) * 1F), (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level2 = new Button(text, "2", level1.x + level1.width + spacer, level1.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level3 = new Button(text, "3", level2.x + level2.width + spacer, level1.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level4 = new Button(text, "4", level3.x + level3.width + spacer, level1.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);

        level5 = new Button(text, "5", (width / 2F) - ((buttonWidth)), level1.y - (buttonHeight + spacer), (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level6 = new Button(text, "6", level5.x + level5.width + spacer, level5.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level7 = new Button(text, "7", level6.x + level6.width + spacer, level5.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        level8 = new Button(text, "8", level7.x + level7.width + spacer, level5.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);

        level9 = new Button(text, "9", (width / 2F) - ((buttonWidth)), level5.y - (buttonHeight + spacer), (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        levelA = new Button(text, "10", level9.x + level9.width + spacer, level9.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        levelB = new Button(text, "11", levelA.x + levelA.width + spacer, level9.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);
        levelC = new Button(text, "12", levelB.x + levelB.width + spacer, level9.y, (buttonWidth / 2F) - (spacer * 0.75F), buttonHeight);

        download = new Button(text, "Downloaded", (width / 2F) - ((buttonWidth)), level9.y - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);

        options = new Button(text, "", ((width / 2F) - (buttonWidth)) + ((buttonWidth * 2F) - buttonHeight), spacer, buttonHeight, buttonHeight);
        exit = new Button(text, "Exit", (width / 2F) - (buttonWidth), spacer, (buttonWidth * 2F)  - (buttonHeight + spacer), buttonHeight);

        options.setTexture(tex);

        buttons.clear();

        if(Gdx.files.isExternalStorageAvailable()){
            FileHandle file = new FileHandle(Gdx.files.getExternalStoragePath() + "levelExp.txt");
            System.out.println(file.path());
            if(file.exists()){
                buttons.add(levelExp.setActionNumber(-1));
            }
        }
        
        buttons.add(level1.setActionNumber(1));
        buttons.add(level2.setActionNumber(2));
        buttons.add(level3.setActionNumber(3));
        buttons.add(level4.setActionNumber(4));

        buttons.add(level5.setActionNumber(5));
        buttons.add(level6.setActionNumber(6));
        buttons.add(level7.setActionNumber(7));
        buttons.add(level8.setActionNumber(8));

        buttons.add(level9.setActionNumber(9));
        buttons.add(levelA.setActionNumber(10));
        buttons.add(levelB.setActionNumber(11));
        buttons.add(levelC.setActionNumber(12));

        buttons.add(download.setActionNumber(-4));
        buttons.add(options.setActionNumber(-3));
        buttons.add(exit.setActionNumber(-2));
	}

	@Override
	public void show() {
		buttonRenderer = new SpriteBatch();
        
		if(aud != null && !NerdShooter.PREF_AUDIO){
			aud.stop();
			aud.dispose();
			aud = null;
		}
		
		if(aud == null && NerdShooter.PREF_AUDIO) {
            if (!NerdShooter.sanic) {
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/Game.mp3"));
            } else {
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/SANIC.mp3"));
            }
            aud.play();
            aud.setLooping(true);
        }

		Gdx.input.setInputProcessor(this);
	}

	public static void reloadMusic(){
		if(NerdShooter.PREF_AUDIO){
			aud.stop();
			aud.dispose();
			aud = null;
			if(!NerdShooter.sanic){
				aud = Gdx.audio.newMusic(Gdx.files.internal("music/Game.mp3"));
			} else {
				aud = Gdx.audio.newMusic(Gdx.files.internal("music/SANIC.mp3"));
			}
			aud.play();
			aud.setLooping(true);
		}
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		
	}
	
	public void resume() {
		
	}
	
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		aud.dispose();
	}
	
    public void doAction(int action){
        if(action == -2){
            Gdx.app.exit();
        } else if(action == -3){
            NerdShooter.shooter.setScreen(OptionsScreen.instance);
        } else if(action == -4){
            NerdShooter.shooter.setScreen(DownloadScreen.instance);
        } else {
            NerdShooter.shooter.setScreen(new GameScreen(action));
        }
    }
    
	public boolean touchDown(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;
        
        boolean value = false;
        
        for(int i = 0; i < buttons.size; i++){
            if(buttons.get(i).isInside(x, y)){
                buttons.get(i).setPressed(true);
                value |= true;
            }
        }
        
        return value;
	}

	public boolean touchUp(int pX, int pY, int pointer, int button) {
		float x = pX;
        float y = height - pY;

        boolean value = false;

        for(int i = 0; i < buttons.size; i++){
            if(buttons.get(i).isInside(x, y)){
                buttons.get(i).setPressed(false);
                doAction(buttons.get(i).getAction());
                value |= true;
                break;
            }
        }

        return value;
	}

	public boolean touchDragged(int pX, int pY, int pointer) {
        float x = pX;
        float y = height - pY;
        
        for(int i = 0; i < buttons.size; i++){
            buttons.get(i).setPressed(buttons.get(i).isInside(x, y));
        }
        
		return false;
	}

	public boolean mouseMoved(int p1, int p2) { return touchDragged(p1, p2, 0); }
	public boolean scrolled(int p1) { return false; }
	public boolean keyDown(int keycode) {
        if ((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)){
            Gdx.app.exit();
        }
        
        return false;
    }
	public boolean keyUp(int p1) { return false; }
	public boolean keyTyped(char p1) { return false; }
	
}
