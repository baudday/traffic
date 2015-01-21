package com.willem.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.willem.tHelpers.AssetLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by wellis on 12/8/2014.
 */
public class Car {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private float start;
    private int width, height;
    private Rectangle boundingBox;

    private boolean isChangingLanes;
    private boolean isActive = false;

    private Lane lane;

    private List<Integer> LANES = Arrays.asList(9, 43, 77, 111);

    private int color;
    private float currentLane;

    public Car(float y, int width, int height, int color) {
        start = y + 10;
        this.width = width;
        this.height = height;
        this.color = color;
        position = new Vector2(0, start);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        boundingBox = new Rectangle();
        currentLane = 0;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        boundingBox.set(position.x, position.y, width, height);
        if (isChangingLanes && closestLane() != -1 && LANES.get(closestLane()) != currentLane) {
            isChangingLanes = false;
            velocity.x = 0;
            position.x = currentLane = LANES.get(closestLane());
        }
        if (position.y < -24) {
            position.y = start;
            lane.removeCar(this);
            velocity = new Vector2(0, 0);
            color = new Random().nextInt(AssetLoader.textures.length);
        }
    }

    public void changeLanes(String direction) {
        if (direction == "left") {
            isChangingLanes = true;
            velocity.x = -80;
        }
        if (direction == "right") {
            isChangingLanes = true;
            velocity.x = 80;
        }
    }

    public boolean contains(float x, float y) {
        int buffer = 10;
        return ((x > position.x - buffer) && (x < (position.x + width + buffer))) && ((y > position.y) && (y < (position.y + height + buffer)));
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getVelocityY() {
        return velocity.y;
    }


    public float getVelocityX() {
        return velocity.x;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean getActive() {
        return isActive;
    }

    public static int getSpeed() {
        return new Random().nextInt((-40 - -140) + 1) + -140;
    }

    public Lane getLane() {
        return lane;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int getColor() {
        return color;
    }

    public void setX(int x) {
        position.x = x;
    }

    public void setVelocityY(float v) {
        velocity.y = v;
    }

    public void setLane(Lane lane) {
        if (lane != null)
            currentLane = lane.getPosition();
        this.lane = lane;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void stop() {
        setVelocityY(0);
    }

    public boolean collides(Car car) {
        return position.y > -12 && position.y < start - 50 &&
                Intersector.overlaps(boundingBox, car.getBoundingBox());
    }

    public void onRestart() {
        position = new Vector2(0, start);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
    }

    private int closestLane() {
        for (int i = 0; i < LANES.size(); i++) {
            if (Math.abs(LANES.get(i) - position.x) < 2)
                return i;
        }
        return -1;
    }
}
