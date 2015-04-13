package com.xemplar.games.android.nerdshooter.screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.*;
import com.xemplar.games.android.nerdshooter.utils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.xemplar.games.android.nerdshooter.*;

public class CompletedLevel implements Screen, InputProcessor {
    public static final String KEY_COMPLETED_TIME = "comp";
    public static final String KEY_FINISH_TYPE = "type";
    public static final String KEY_LEVEL_NUM = "level";
    
    public static CompletedLevel instance;
    
    private double completed;
    private int level;
    private String message;
    
    private float[] colors = {0.5F, 0.5F, 0.5F, 1.0F};
    private float[] pressedColors = {0.7F, 0.7F, 0.7F, 1.0F};
    
    private ScreenButton replay;
    private ScreenButton menu;
    
    private BitmapFont text;
    private float buttonHeight;
    
    private int width, height;
    private SpriteBatch batch;
    private ShapeRenderer buttons;
    
    public CompletedLevel(){
        instance = this;
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		
        float font = text.getBounds(message).width;
        
        batch.begin();{
            text.draw(batch, message, (width / 2F) - (font / 2F), height - text.getCapHeight());
            replay.renderText(batch);
            menu.renderText(batch);
        } batch.end();
        
        buttons.begin(ShapeRenderer.ShapeType.Filled);{
            replay.renderButton(buttons);
            menu.renderButton(buttons);
        } buttons.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        
        float spacer = 10F;
        float buttonWidth = (width * (3F / 4F));
        buttonHeight = height / 9F;
        
        text = new BitmapFont(Gdx.files.internal("font/digital.fnt"));

        replay = new ScreenButton(text, "Replay", colors, pressedColors, (width / 2F) - (buttonWidth / 2F), buttonHeight + spacer, buttonWidth, buttonHeight);
        menu = new ScreenButton(text, "Menu", colors, pressedColors, (width / 2F) - (buttonWidth / 2F), replay.y + spacer + buttonHeight, buttonWidth, buttonHeight);
    }

    public void show() {
        Object det = InterScreenData.getInstance(NerdShooter.COMP_DATA).getData();

        if(det != null){
            XPMLItem item = (XPMLItem) det;
            completed = Long.parseLong(item.getElementValue(KEY_COMPLETED_TIME)) / 10D;
            int finishType = Integer.parseInt(item.getElementValue(KEY_FINISH_TYPE));
            level = Integer.parseInt(item.getElementValue(KEY_LEVEL_NUM));

            switch(finishType){
            case ExitBlock.EXIT_NOCLEAR:
                message = "Didn't Finish Level " + level;
                break;
            case ExitBlock.EXIT_NORMAL:
                message = "Completed Level " + level + "in\n" + completed + " seconds.";
                break;
            default:
                message = "Hacker";
                break;
            }
        }
        
        batch = new SpriteBatch();
        buttons = new ShapeRenderer();
        
        Gdx.input.setInputProcessor(this);
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void pause() {
        
    }

    public void resume() {
        
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
    
    public boolean touchDown(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;

        boolean value = false;

        if(replay.isInside(x, y)){
            replay.setPressed(true);
            value |= true;
        }
        
        if(menu.isInside(x, y)){
            replay.setPressed(true);
            value |= true;
        }
        
        return value;
    }

    public boolean touchUp(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;

        boolean value = false;

        if(replay.isInside(x, y)){
            replay.setPressed(false);
            NerdShooter.shooter.setScreen(new GameScreen(level));
            value |= true;
        }

        if(menu.isInside(x, y)){
            menu.setPressed(false);
            NerdShooter.shooter.setScreen(StartScreen.instance);
            value |= true;
        }

        return value;
    }

    public boolean touchDragged(int pX, int pY, int pointer) {
        float x = pX;
        float y = height - pY;

        if(replay.isInside(x, y)){
            replay.setPressed(replay.isInside(x, y));
        }

        if(menu.isInside(x, y)){
            menu.setPressed(menu.isInside(x, y));
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
