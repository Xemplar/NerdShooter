/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class StartScreen implements Screen, InputProcessor {
    public static StartScreen instance;
    private float buttonHeight, buttonWidth, spacer;

    protected Button levelExp;

    protected Button download;
    protected Button options;
    protected Button exit;

    protected Button[] worlds;
    private static String dat;

    protected SpriteBatch buttonRenderer;
    protected BitmapFont text, button;
    
    protected float width, height;
    protected Array<Button> buttons = new Array<Button>();

    private static Music aud;
    private static Texture tex;
    
    public StartScreen(){
        instance = this;
        tex = new Texture(Gdx.files.internal("images/options.png"));

        FileHandle downloaded = Gdx.files.local("levels/dwn.nsd");
        if(!downloaded.exists()){
            downloaded.writeString("<none>", false);
        }
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

        this.buttonHeight = height / 9F;

        this.spacer = buttonHeight / 10F;
        this.buttonWidth = (width * ((3F / 4F) / 2F));
        
        text = NerdShooter.label;
        text.setColor(0, 0, 0, 1);

        button = NerdShooter.label_small;
        button.setColor(0, 0, 0, 1);

        levelExp = new Button(text, NerdShooter.button, "External Level", (width / 2F) - (buttonWidth), height - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);
        
        options = new Button(text, NerdShooter.button, "", ((width / 2F) - (buttonWidth)) + ((buttonWidth * 2F) - buttonHeight), spacer, buttonHeight, buttonHeight);
        exit = new Button(text, NerdShooter.button, "Exit", (width / 2F) - (buttonWidth), spacer, (buttonWidth * 2F)  - (buttonHeight + spacer), buttonHeight);
        download = new Button(text, NerdShooter.button, "Get More!!!", (width / 2F) - ((buttonWidth)), exit.y + (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);

        options.setTexture(tex);

        buttons.clear();

        if(Gdx.files.isExternalStorageAvailable()){
            FileHandle file = new FileHandle(Gdx.files.getExternalStoragePath() + "levelExp.txt");
            System.out.println(file.path());
            if(file.exists()){
                buttons.add(levelExp.setActionNumber(-1));
            }
        }

        buttons.add(download.setActionNumber(-4));
        buttons.add(options.setActionNumber(-3));
        buttons.add(exit.setActionNumber(-2));

        checkLevels();
    }

    private void checkLevels(){
        Array<String> levels = getPackList();

        worlds = new Button[levels.size];
        for(int i = 0; i < levels.size; i++){
            float w = (buttonWidth * 2F) / 3F - spacer;
            float x = levelExp.x + (i / 3) * (w + spacer);
            float y = levelExp.y - ((i % 3) + 1) * (buttonHeight + spacer);
            worlds[i] = new Button(button, NerdShooter.button, levels.get(i), x, y, w, buttonHeight);
            worlds[i].setActionNumber(1);
            buttons.add(worlds[i]);
        }
    }

    public static Array<String> getPackList(){
        Array<String> ret = new Array<String>();

        FileHandle downloaded = Gdx.files.local("levels/dwn.nsd");
        dat = downloaded.readString();

        String[] lines = dat.split("\n");

        for(int i = 0; i < lines.length; i++){
            String nsp = lines[i];
            boolean dirExists = Gdx.files.local("levels/" + nsp).isDirectory();

            if(dirExists){
                ret.add(lines[i]);
            }
        }

        return ret;
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
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/Game.ogg"));
            } else {
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/SANIC.ogg"));
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
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/Game.ogg"));
            } else {
                aud = Gdx.audio.newMusic(Gdx.files.internal("music/SANIC.ogg"));
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
    
    public void doAction(String text, int action){
        if(action == -1){
            NerdShooter.shooter.setScreen(new GameScreen("", -1));
        } else if(action == -2){
            Gdx.app.exit();
        } else if(action == -3){
            NerdShooter.shooter.setScreen(OptionsScreen.instance);
        } else if(action == -4){
            NerdShooter.shooter.setScreen(DownloadScreen.instance);
        } else {
            NerdShooter.shooter.setScreen(PackScreen.setPack(text));
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
                doAction(buttons.get(i).getText(), buttons.get(i).getAction());
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
