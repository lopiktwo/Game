package ru.leonkik.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ru.leonkik.game.Main;
import ru.leonkik.game.objects.Player;
import ru.leonkik.game.objects.SimpleObject;

public class GameScreen extends ScreenAdapter {
    Texture test;
    Main game;
    SimpleObject object;
    Player player;

    public GameScreen(Main game) {
        this.game = game;
        test = new Texture("libgdx.png");
        object = new SimpleObject(10, 10, "libgdx.png");
        player = new Player(game.world,"Ellipse 1.png");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.stepWorld();
        game.batch.setProjectionMatrix(game.camera.combined);
        InputTouch();
        game.batch.begin();
        object.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }
    private void InputTouch() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveLeft();
        }
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}
