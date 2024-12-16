package ru.leonkik.game.objects;

public interface Hittable {
     void hit(short hitObjectsBits);
     void release(short hitObjectsBits);
}
