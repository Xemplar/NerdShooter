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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;
import com.xemplar.games.android.nerdshooter.screens.ui.Label;
import com.xemplar.games.android.nerdshooter.screens.ui.SwitchButton;
import com.xemplar.games.android.nerdshooter.screens.ui.View;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.xemplar.games.android.nerdshooter.NerdShooter.label_small;

public class PackScreen implements Screen, InputProcessor {
    public static PackScreen instance;
    private float buttonHeight, buttonWidth, spacer;

    protected SpriteBatch buttonRenderer;
    protected BitmapFont button;

    protected Label name;

    protected Button back;
    protected Button[] levels;
    public String pack;

    protected Array<View> views = new Array<View>();
    protected float width, height;

    public static PackScreen setPack(String p){
        instance.pack = p;
        return instance;
    }

    public PackScreen(){
        instance = this;
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

        this.spacer = buttonHeight / 10F;
        this.buttonWidth = (width * (3F / 4F));

        button = label_small;
        button.setColor(0, 0, 0, 1);
        back = new Button(NerdShooter.label, "Back", (width / 2F) - (buttonWidth / 2F), spacer, buttonWidth, buttonHeight);
        name = new Label(NerdShooter.text, pack, (width / 2F) - (buttonWidth / 2F), height - (buttonHeight + spacer), buttonWidth, buttonHeight);

        views.clear();
        views.add(name);
        views.add(back.setActionNumber(-1));

        levels = new Button[12];
        for(int i = 0; i < 12; i++){
            float w = (buttonWidth / 4F) - spacer;
            float x = (width / 2F) - (buttonWidth / 2F) + (i / 3) * (w + spacer);
            float y = height - (buttonHeight + spacer) - ((i % 3) + 1) * (buttonHeight + spacer);
            levels[i] = new Button(button, (i + 1) + "", x, y, w, buttonHeight);
            levels[i].setActionNumber(i);
            views.add(levels[i]);
        }
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

    public void doAction(int action){
        if(action == -1){
            NerdShooter.shooter.setScreen(StartScreen.instance);
        } else {
            NerdShooter.shooter.setScreen(new GameScreen(pack, (action + 1)));
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
