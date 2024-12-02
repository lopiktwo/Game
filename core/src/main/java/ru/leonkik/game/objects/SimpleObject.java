package ru.leonkik.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleObject {
    int x;
    int y;
    Texture texture;
    public SimpleObject(int x, int y, String path) {
        this.x = x;
        this.y = y;
        texture = new Texture(path);
    }
    public void draw(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}
