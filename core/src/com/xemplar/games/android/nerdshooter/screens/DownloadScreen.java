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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;
import com.xemplar.games.android.nerdshooter.screens.ui.Label;
import com.xemplar.games.android.nerdshooter.screens.ui.SwitchButton;
import com.xemplar.games.android.nerdshooter.screens.ui.View;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class DownloadScreen implements Screen, InputProcessor {
    public static DownloadScreen instance;
    private float buttonHeight;
    private boolean hasFiles;
    private String dat;

    protected SpriteBatch buttonRenderer;
    protected BitmapFont button;

    protected Label lbl, non;

    protected Button back, down;
    protected Button[] worlds;

    protected Array<View> views = new Array<View>();
    protected float width, height;

    public DownloadScreen(){
        instance = this;
        FileHandle downloaded = Gdx.files.local("levels/dwn.nsd");
        if(!downloaded.exists()){
            downloaded.writeString("<none>", false);
        }

        dat = downloaded.readString();
        if(dat.equals("<none>")){
            hasFiles = false;
        } else {
            hasFiles = true;
        }
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        buttonRenderer.begin(); {
            for(View view : views){
                view.render(buttonRenderer);
            }
        } buttonRenderer.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        this.buttonHeight = height / 9F;

        float spacer = 10F;
        float buttonWidth = (width * ((3F / 4F) / 2F));

        button = NerdShooter.label_small;
        button.setColor(0, 0, 0, 1);

        lbl = new Label(NerdShooter.text, "Downloaded: ", (width / 2F) - (buttonWidth), height - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);
        non = new Label(NerdShooter.text, "Press Get More!!!", (width / 2F) - (buttonWidth), lbl.y - (buttonHeight + spacer) * 2F, (buttonWidth * 2F), buttonHeight);

        down = new Button(NerdShooter.label, "Get More", (width / 2F) - (buttonWidth), spacer, (buttonWidth) - (spacer), buttonHeight);
        back = new Button(NerdShooter.label, "Back", down.x + down.width + spacer, spacer, (buttonWidth) - (spacer), buttonHeight);
        back.setActionNumber(1);

        views.clear();

        if(!hasFiles){
            views.add(non);
        }

        views.add(lbl);
        views.add(down);
        views.add(back);

        String[] lines = dat.split("\n");
        worlds = new Button[lines.length];
        for(int i = 0; i < lines.length; i++){
            String nsp = lines[i].replace(" ", "");
            boolean dirExists = Gdx.files.local("levels/dwn/" + nsp).isDirectory();

            if(dirExists){
                float w = (buttonWidth * 2F) / 3F - spacer;
                float x = lbl.x + (i / 3) * (w + spacer);
                float y = lbl.y - ((i % 3) + 1) * (buttonHeight + spacer);
                worlds[i] = new Button(NerdShooter.label_small, lines[i], x, y, w, buttonHeight);
                views.add(worlds[i]);
            } else {

            }
        }

        loadSettings();
    }

    public void show() {
        buttonRenderer = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }


    public void pause() {

    }
    public void resume() {

    }

    public void loadSettings(){
        Array<View> saveAbles = new Array<View>();
        for(View v : views){
            if(v instanceof SwitchButton){
                saveAbles.add(v);
            }
        }

        for(View v : saveAbles){
            if(v instanceof SwitchButton){
                SwitchButton b = (SwitchButton)v;
                String pref = NerdShooter.prefs.getString(b.getKey());
                if(pref != null){
                    b.setFromSave(pref);
                }
            }
        }
    }

    public void saveSettings(){
        Array<View> saveAbles = new Array<View>();
        for(View v : views){
            if(v instanceof SwitchButton){
                saveAbles.add(v);
            }
        }

        for(View v : saveAbles){
            if(v instanceof SwitchButton){
                SwitchButton b = (SwitchButton)v;
                NerdShooter.prefs.putString(b.getKey(), b.getSaveState());
            }
        }
        NerdShooter.prefs.flush();
        NerdShooter.reloadSettings();
    }

    public void doAction(int action){
        if(action == 1){
            saveSettings();
            NerdShooter.shooter.setScreen(StartScreen.instance);
        }
    }

    public boolean touchDown(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;

        boolean value = false;

        for(int i = 0; i < views.size; i++){
            View current = views.get(i);
            if(current.isInside(x, y)){
                if(current instanceof SwitchButton){
                    continue;
                } else if(current instanceof Button){
                    ((Button)current).setPressed(true);
                }
                value |= true;
            }
        }

        return value;
    }

    public boolean touchUp(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;

        boolean value = false;

        for(int i = 0; i < views.size; i++){
            View current = views.get(i);
            if(current.isInside(x, y)){
                if(current instanceof SwitchButton){
                    ((SwitchButton)current).toggle();
                } else if(current instanceof Button){
                    ((Button)current).setPressed(false);
                    doAction(((Button)current).getAction());
                }
                value |= true;
            }
        }

        return value;
    }

    public boolean touchDragged(int pX, int pY, int pointer) {
        float x = pX;
        float y = height - pY;

        for(int i = 0; i < views.size; i++){
            if(views.get(i) instanceof Button) {
                ((Button)views.get(i)).setPressed(views.get(i).isInside(x, y));
            }
        }

        return false;
    }

    public boolean keyDown(int key) {
        return false;
    }
    public boolean keyTyped(char key) {
        return false;
    }
    public boolean keyUp(int key) {
        return false;
    }
    public boolean mouseMoved(int p1, int p2) { return touchDragged(p1, p2, 0); }
    public boolean scrolled(int e) {
        return false;
    }
}
