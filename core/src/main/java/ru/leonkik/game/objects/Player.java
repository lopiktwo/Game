package ru.leonkik.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import ru.leonkik.game.GameSettings;

public class Player implements Hittable {
    PhysicalObject physicalObject;
    Texture texture;
    int width;
    int height;
    int Heal = 10;
    public Player( World world,String pathToTexture,int x,int y) {
        texture = new Texture(pathToTexture);
        //phy
        physicalObject = new  PhysicalObject.PhysicalObjectBuilder(world, BodyDef.BodyType.DynamicBody)
                .addCircularFixture(50, GameSettings.PLAYER_BIT).setInitialPosition(x,y).build(this);

    }
    public Player( World world,String pathToTexture,int x,int y,int width,int height) {
        texture = new Texture(pathToTexture);
        this.width = width;
        this.height = height;
        //phy
        physicalObject = new  PhysicalObject.PhysicalObjectBuilder(world, BodyDef.BodyType.DynamicBody)
            .addCircularFixture((float) width /2, GameSettings.PLAYER_BIT,10).setInitialPosition(x,y).build(this);

    }
    public void moveUp(){
        physicalObject.getBody().applyForceToCenter(0,GameSettings.PLAYER_SPEED,true);
    }
    public void moveLeft(){
        physicalObject.getBody().applyForceToCenter(-GameSettings.PLAYER_SPEED,0,true);
    }
    public void moveRight(){
        physicalObject.getBody().applyForceToCenter(GameSettings.PLAYER_SPEED,0,true);
    }
    public void moveDown(){
        physicalObject.getBody().applyForceToCenter(0,-GameSettings.PLAYER_SPEED,true);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, physicalObject.getX(), physicalObject.getY(),width,height);
    }


    @Override
    public void hit(short hitObjectsBits) {
        Heal -= 1;
    }

    @Override
    public void release(short hitObjectsBits) {

    }
}
