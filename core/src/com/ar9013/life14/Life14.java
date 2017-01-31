package com.ar9013.life14;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;


import java.util.HashMap;
import java.util.Map;

public class Life14 implements ApplicationListener, GestureDetector.GestureListener, InputProcessor {
	String TAG = "Life14";
	SpriteBatch batch;
	private BitmapFont font;
	private String message = "No gesture performed yet";
	Texture img;
	private  int w,h;

	class TouchInfo{
		float touchX = 0;
		float touchY = 0;
		boolean isTouched = false;
	}

	private Map<Integer,TouchInfo> touches = new HashMap<Integer, TouchInfo>();

// GestureListener 實做方法
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// 手勢的 touchDown 會跟 InputProcessor 重複，選其中一個實做就好了。

			message = "touchDown2 ";

			Gdx.app.log(TAG,message+x+","+y);

		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		message = "Tap performed, finger" + Integer.toString(button);
		Gdx.app.log(TAG, message);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		message = "Long press performed";
		Gdx.app.log(TAG, message);
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		message = "Fling performed, velocity:" + Float.toString(velocityX) +
				"," + Float.toString(velocityY);
		Gdx.app.log(TAG, message);
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		message = "Pan performed, delta:" + Float.toString(deltaX) +
				"," + Float.toString(deltaY);
		Gdx.app.log(TAG, message);
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
				" Distance: " + Float.toString(distance);
		Gdx.app.log(TAG, message);
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		message = "Pinch performed";
		Gdx.app.log(TAG, message);

		return true;
	}

	@Override
	public void pinchStop() {
		message = "Pinch Stop";
		Gdx.app.log(TAG, message);
	}

// Gdx 生命週期
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(this);
		im.addProcessor(gd);
		im.addProcessor(this);

		Gdx.input.setInputProcessor(im);


		for(int i = 0; i < 5; i++){
			touches.put(i, new TouchInfo());
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);

		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		font.dispose();
	}

	// InputProcessor 實做方法
	@Override
	public boolean keyDown(int keycode) {

		if(keycode==Input.Keys.A){
			message = "keyDown A";
			Gdx.app.log(TAG, message);
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Input.Keys.A){
			message = "keyUp A";
			Gdx.app.log(TAG, message);
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			touches.get(pointer).touchX = screenX;
			touches.get(pointer).touchY = screenY;
			touches.get(pointer).isTouched = true;

			message = "touchDown ";

			Gdx.app.log(TAG,message+screenX+","+screenY);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			touches.get(pointer).touchX = 0;
			touches.get(pointer).touchY = 0;
			touches.get(pointer).isTouched = false;

			message = "touchUp ";

			Gdx.app.log(TAG,message+screenX+","+screenY);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		message = "Touch Dragged";
		Gdx.app.log(TAG, message);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		message = "Mouse moved";
		Gdx.app.log(TAG, message);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
