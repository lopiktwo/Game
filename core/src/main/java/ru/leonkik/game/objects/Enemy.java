package ru.leonkik.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import ru.leonkik.game.GameSettings;

public class Enemy implements Hittable{
    PhysicalObject physicalObject;
    World world;
    Texture texture;
    int width;
    int height;
    private boolean isDead = false;
    private boolean contact = false;
    public Enemy(World world, String pathToTexture,int x,int y,int width,int height) {
        texture = new Texture(pathToTexture);
        this.width = width;
        this.height = height;
        this.world = world;
        //phy
        physicalObject = new  PhysicalObject.PhysicalObjectBuilder(world, BodyDef.BodyType.DynamicBody)
            .addRectangularFixture(width,height, GameSettings.ENEMY_BIT,8000)
            .setInitialPosition(x,y)
            .build(this);

    }
    public void draw(SpriteBatch batch) {
        if(!isDead) {
            batch.draw(texture, physicalObject.getX(), physicalObject.getY(), width, height);
        }

    }

    @Override
    public void hit(short hitObjectsBits) {
       setContact(true);
       setIsDead(true);
        System.out.println(this.hashCode());
    }



    @Override
    public void release(short hitObjectsBits) {

    }
    public void setPosition(float x, float y) {
        physicalObject.setPosition(x, y);
    }
    public void destroy() {

      world.destroyBody(physicalObject.getBody());

    }
    public  void setContact(boolean contact) {
        this.contact = contact;
        System.out.println("conctactIs:"+contact);
    }
    public  boolean  getContact(){
        return contact;
    }
    public  void setIsDead(boolean dead) {
        this.isDead = dead;
        System.out.println("deadIs:"+contact);
    }

    public  boolean  getIsDead() {
        return isDead;
    }
    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }


}
