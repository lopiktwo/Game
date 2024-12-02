package ru.leonkik.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import ru.leonkik.game.GameSettings;

public class Player {
    PhysicalObject physicalObject;
    Texture texture;
    public Player( World world,String pathToTexture) {
        texture = new Texture(pathToTexture);
        //phy
        physicalObject = new  PhysicalObject.PhysicalObjectBuilder(world, BodyDef.BodyType.DynamicBody)
                .addCircularFixture(50, GameSettings.PLAYER_BIT).build(null);

    }
    public void moveUp(){
        physicalObject.getBody().applyForceToCenter(0,GameSettings.PLAYER_SPEED,false);
    }
    public void moveLeft(){
        physicalObject.getBody().applyForceToCenter(GameSettings.PLAYER_SPEED,0,true);
    }
    public void moveRight(){
        physicalObject.getBody().applyForceToCenter(-GameSettings.PLAYER_SPEED,0,true);
    }
    public void moveDown(){
        physicalObject.getBody().applyForceToCenter(0,-GameSettings.PLAYER_SPEED,true);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture,physicalObject.getBody().getPosition().x,physicalObject.getBody().getPosition().y);
    }


}
