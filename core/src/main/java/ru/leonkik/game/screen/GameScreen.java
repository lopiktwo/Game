package ru.leonkik.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ru.leonkik.game.GameSettings;
import ru.leonkik.game.Main;
import ru.leonkik.game.objects.ContactManager;
import ru.leonkik.game.objects.Enemy;
import ru.leonkik.game.objects.Player;
import ru.leonkik.game.objects.SimpleObject;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenAdapter {
    Texture test;
    Main game;
    SimpleObject object;
    Player player;
    Enemy enemy;
    ContactManager contactManager;
    Random random;
    ArrayList<Enemy> enemyList;

    public GameScreen(Main game) {
        random = new Random();
        this.game = game;
        contactManager = new ContactManager(game.world);
        enemyList = new ArrayList<>();
        //
        enemyList.add(new Enemy(game.world, "Rectangle 3.png", GameSettings.SCREEN_WIDTH / 2, GameSettings.SCREEN_HEIGHT / 2, 150, 150));
        //
        test = new Texture("libgdx.png");
        object = new SimpleObject(10, 10, "libgdx.png");
        player = new Player(game.world, "Ellipse 1.png", 0, 0, 150, 150);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        Logic();
        game.batch.begin();
        for (Enemy i : enemyList) {
            i.draw(game.batch);
        }
        object.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }

    private void InputTouch() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
    }

    private void Logic() {
        System.out.println(enemyList.size());
        InputTouch();
        game.stepWorld();
        for (int q = 0; q < enemyList.size(); q++) {
            Enemy i = enemyList.get(q);
            if (i.getContact()) {
                i.destroy();
                i.setContact(false);
            }

            if (i.getIsDead()) {
                enemyList.add(new Enemy(game.world,
                    "Rectangle 3.png",
                    (int) (i.getPhysicalObject().getX() + 500),
                    (int) i.getPhysicalObject().getY(),
                    150,
                    150));
              enemyList.add(new Enemy(game.world,
                 "Rectangle 3.png",
                  (int) (i.getPhysicalObject().getX() - 500),
                   (int) i.getPhysicalObject().getY(),
                 150,
                   150));
              enemyList.get(enemyList.size() - 1 ).setIsDead(false);
              enemyList.get(enemyList.size() - 1 ).setContact(false);
               enemyList.add(new Enemy(game.world,
                   "Rectangle 3.png",
                   (int) (i.getPhysicalObject().getX()),
                   (int) i.getPhysicalObject().getY()+100,
                    150,
                   150));
               i.setIsDead(false);
                enemyList.remove(i);
               break;
            }
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
