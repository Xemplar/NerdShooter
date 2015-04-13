package com.xemplar.games.android.nerdshooter.screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.utils.*;
import com.xemplar.games.android.nerdshooter.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import java.io.*;

public class StartScreen implements Screen, InputProcessor {
    public static StartScreen instance;
	private float buttonHeight;
	
	private ScreenButton levelExp;
	private ScreenButton level1;
	private ScreenButton level2;
    private ScreenButton level3;
    private ScreenButton level4;
    private ScreenButton level5;
    private ScreenButton level6;
    private ScreenButton exit;
	
	private ShapeRenderer buttonRenderer;
	private SpriteBatch textRenderer;
	
	private BitmapFont text;
	
	private float width, height;
    private Array<ScreenButton> buttons = new Array<ScreenButton>();
    
	private float[] colors = {0.5F, 0.5F, 0.5F, 1.0F};
    private float[] pressedColors = {0.7F, 0.7F, 0.7F, 1.0F};
    
	public StartScreen(){
        instance = this;
	}
	
	public void render(float delta) {
        NerdShooter.shooter.setCurrentScreen(NerdShooter.START_SCREEN);
        
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		
		buttonRenderer.begin(ShapeRenderer.ShapeType.Filled); {
		    for(ScreenButton button : buttons){
                button.renderButton(buttonRenderer);
            }
		} buttonRenderer.end();
		
		textRenderer.begin(); {
		    for(ScreenButton button : buttons){
                button.renderText(textRenderer);
            }
		} textRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		
        float spacer = 10F;
		float buttonWidth = (width * ((3F / 4F) / 2F));
		buttonHeight = height / 9F;
		float centerY = (height / 2F) - (buttonHeight / 2F);
        
        text = new BitmapFont(Gdx.files.internal("font/digital.fnt"));
        
		levelExp = new ScreenButton(text, "External Level", colors, pressedColors, (width / 2F) - (buttonWidth), height - (buttonHeight + spacer), (buttonWidth * 2F), buttonHeight);
        
        level1 = new ScreenButton(text, "Level 1", colors, pressedColors, (width / 2F) - ((buttonWidth)), levelExp.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
		level2 = new ScreenButton(text, "Level 2", colors, pressedColors, (width / 2F) - ((buttonWidth)), level1.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
        level3 = new ScreenButton(text, "Level 3", colors, pressedColors, (width / 2F) - ((buttonWidth)), level2.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
		level4 = new ScreenButton(text, "Level 4", colors, pressedColors, level1.x + level1.width + spacer, levelExp.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
        level5 = new ScreenButton(text, "Level 5", colors, pressedColors, level2.x + level2.width + spacer, level4.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
        level6 = new ScreenButton(text, "Level 6", colors, pressedColors, level3.x + level3.width + spacer, level5.y - (buttonHeight + spacer), buttonWidth - (spacer / 2F), buttonHeight);
        
        exit = new ScreenButton(text, "Exit", colors, pressedColors, (width / 2F) - (buttonWidth), spacer, (buttonWidth * 2F), buttonHeight);
        
        if(Gdx.files.isExternalStorageAvailable()){
            File file = new File(Gdx.files.getExternalStoragePath(), "levelExp.txt");
            
            if(file.exists()){
                buttons.add(levelExp.setActionNumber(-1));
            }
        }
        
        buttons.add(level1.setActionNumber(1));
        buttons.add(level2.setActionNumber(2));
        
        /*buttons.add(level3.setActionNumber(3));
        buttons.add(level4.setActionNumber(4));
        buttons.add(level5.setActionNumber(5));
        buttons.add(level6.setActionNumber(6));*/
        
        buttons.add(exit.setActionNumber(-2));
	}

	@Override
	public void show() {
		buttonRenderer = new ShapeRenderer();
		textRenderer = new SpriteBatch();
        
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}
	
    public void doAction(int action){
        if(action == -2){
            Gdx.app.exit();
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
