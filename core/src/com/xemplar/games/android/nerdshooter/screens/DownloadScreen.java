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
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.exceptions.PackIDMismatchException;
import com.xemplar.games.android.nerdshooter.net.NetworkListener;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;
import com.xemplar.games.android.nerdshooter.screens.ui.Label;
import com.xemplar.games.android.nerdshooter.screens.ui.SwitchButton;
import com.xemplar.games.android.nerdshooter.screens.ui.View;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.xemplar.games.android.nerdshooter.NerdShooter.NETWORK_HANDLE;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.xemplar.games.android.nerdshooter.NerdShooter.START_SCREEN;

public class DownloadScreen implements Screen, InputProcessor {
    public static DownloadScreen instance;
    private float buttonHeight, buttonWidth, spacer;

    protected SpriteBatch buttonRenderer;
    protected BitmapFont button;

    protected Label lbl, non;

    protected Button back, delt;
    protected Button[] worlds;
    protected boolean ready = false;
    protected JSONArray dat;

    protected Array<View> views = new Array<View>();
    protected float width, height;

    public DownloadScreen(){
        instance = this;
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        if(ready){
            System.out.println("Levels Creation Started");
            Array<String> down = StartScreen.getPackList();

            int length = dat.length();

            worlds = new Button[length];
            for(int i = 0; i < dat.length(); i++){
                JSONObject current = dat.getJSONObject(i);
                String name = current.getString("name");

                boolean downd = false;
                for(String s : down){
                    downd |= s.equals(name);
                }

                float w = (buttonWidth * 2F) / 3F - spacer;
                float x = lbl.x + (i / 3) * (w + spacer);
                float y = lbl.y - ((i % 3) + 1) * (buttonHeight + spacer);
                worlds[i] = new Button(NerdShooter.label_small, NerdShooter.button, name, x, y, w, buttonHeight);
                worlds[i].setActionNumber(100000 + current.getInt("id"));
                worlds[i].setPressedColor(downd ? Color.RED : Color.BLUE);
                worlds[i].setNotPressedColor(downd ? Color.GREEN : Color.GRAY);
                views.add(worlds[i]);
            }
            ready = false;
        }

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
        this.buttonWidth = (width * ((3F / 4F) / 2F));

        button = NerdShooter.label_small;
        button.setColor(0, 0, 0, 1);

        lbl = new Label(NerdShooter.text, "Downloadable: ", (width / 2F) - (buttonWidth), height - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);
        non = new Label(NerdShooter.text, "You have them all!!!", (width / 2F) - (buttonWidth), lbl.y - (buttonHeight + spacer) * 2F, (buttonWidth * 2F), buttonHeight);

        delt = new Button(NerdShooter.label, NerdShooter.button, "Delete", (width / 2F) - (buttonWidth), spacer, (buttonWidth) - (spacer), buttonHeight);
        back = new Button(NerdShooter.label, NerdShooter.button, "Back", delt.x + delt.width + spacer, spacer, (buttonWidth) - (spacer), buttonHeight);

        back.setActionNumber(1);

        views.clear();

        views.add(lbl);
        views.add(delt);
        views.add(back);

        System.out.println("Levels Retrieval Started");
        NETWORK_HANDLE.getPackList(new NetworkListener() {
            public void finished(String data) {
                System.out.println("Levels Retrieval Success");
                System.out.println(data);
                dat = new JSONArray(data);
                ready = true;
            }

            public void length(long l){
                System.out.println(l);
            }

            public void failed(Throwable t) {
                System.out.println("Levels Retrieval Failed: " + t.toString());
            }
        });
        System.out.println("Levels Retrieval Sent");
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
        if(action == 1){
            NerdShooter.shooter.setScreen(StartScreen.instance);
        } else if(action > 100000){
            final int packid = action - 100000;
            System.out.println("Pack Request Sent");
            NETWORK_HANDLE.getPack(packid, new NetworkListener(){
                public void finished(String data) {
                    JSONObject obj = new JSONObject(data);
                    String name = obj.getString("name");

                    int received = obj.getInt("id");

                    if(received != packid) {
                        throw new PackIDMismatchException(packid, received);
                    } else {
                        String levels = obj.getString("levels");
                        System.out.println(levels);
                        String[] array = levels.split("\",\"");
                        for(int i = 0; i < array.length; i++) {
                            String current = array[i].replace("[","").replace("]","").replace("\"","");
                            String number = (i + 1) + "";
                            if(i < 100){
                                number = "0" + number;
                            }
                            if(i < 10){
                                number = "0" + number;
                            }
                            FileHandle h = Gdx.files.local("levels/" + name + "/" + number + ".nsl");

                            String[] lines = current.split("=");
                            for(int j = 0; j < lines.length; j++) {
                                h.writeString(lines[j] + "\n", true);
                            }
                        }

                        FileHandle dwn = Gdx.files.local("levels/dwn.nsd");
                        String dat = dwn.readString();

                        dwn.writeString(name, !dat.equals("<none>"));
                    }
                }

                public void length(long l){
                    System.out.println(l);
                }

                public void failed(Throwable t) {
                    System.out.println("Levels Retrieval Failed: ");
                    t.printStackTrace();
                }
            });
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
