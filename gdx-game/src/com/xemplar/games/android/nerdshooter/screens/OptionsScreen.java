package com.xemplar.games.android.nerdshooter.screens;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;

public class OptionsScreen implements Screen, InputProcessor {
    public static StartScreen instance;
	private float buttonHeight;
	
    protected SpriteBatch buttonRenderer;
    protected BitmapFont text;

	protected Array<Button> buttons = new Array<Button>();
	protected float width, height;

	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
		
	}
	
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.buttonHeight = height / 9F;
		
		float spacer = 10F;
		float buttonWidth = (width * ((3F / 4F) / 2F));
        
        text = NerdShooter.label;
	}

	public void show() {
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

	public boolean touchDown(int pX, int pY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDragged(int pX, int pY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchUp(int pX, int pY, int pointer, int button) {
		// TODO Auto-generated method stub
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
	public boolean mouseMoved(int p1, int p2) {
		return false;
	}
	public boolean scrolled(int e) {
		return false;
	}	
}
