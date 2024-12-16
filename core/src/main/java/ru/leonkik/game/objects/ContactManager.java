package ru.leonkik.game.objects;

import com.badlogic.gdx.physics.box2d.*;
import ru.leonkik.game.GameSettings;

import java.util.Random;

public class ContactManager  {
    World world;
    Enemy enemy;
    Random rand;
    public ContactManager(World world) {
        rand = new Random();

        this.world = world;
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                int cDef = fixA.getFilterData().categoryBits;
                int cDef2 = fixB.getFilterData().categoryBits;

                if (cDef == GameSettings.PLAYER_BIT && cDef2 == GameSettings.ENEMY_BIT) {
                    enemy = (Enemy) fixB.getUserData();
                    enemy.hit(GameSettings.PLAYER_BIT);

                } else if (cDef2 == GameSettings.PLAYER_BIT && cDef == GameSettings.ENEMY_BIT) {
                    enemy = (Enemy) fixA.getUserData();
                    enemy.hit(GameSettings.PLAYER_BIT);

                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }
}
