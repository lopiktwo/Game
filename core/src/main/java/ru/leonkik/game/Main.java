package ru.leonkik.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import ru.leonkik.game.screen.GameScreen;

import static ru.leonkik.game.GameSettings.*;


public class Main extends Game {
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public GameScreen gameScreen;
    public World world;
    private double accumulator;
    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);


    }
    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= TIME_STEP) {
            accumulator -= TIME_STEP;
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
}
