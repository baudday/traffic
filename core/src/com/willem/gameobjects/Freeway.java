package com.willem.gameobjects;

import com.willem.tHelpers.AssetLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by wellis on 12/16/2014.
 */
public class Freeway {

    private ArrayList<Car> cars;
    private ArrayList<Lane> lanes;

    private int CAR_COUNT = 7;
    private int LANE_COUNT = 4;

    private int gameHeight;

    public Freeway(int gameHeight) {
        this.gameHeight = gameHeight;

        cars = new ArrayList<Car>(CAR_COUNT);
        lanes = new ArrayList(Arrays.asList(
                new Lane(9, gameHeight / 2),
                new Lane(43, gameHeight / 2),
                new Lane(77, gameHeight / 2),
                new Lane(111, gameHeight / 2))
        );


        for (int i = 0; i < CAR_COUNT; i++) {
            cars.add(new Car(gameHeight - 5 , 15, 24, new Random().nextInt(AssetLoader.textures.length)));
        }
        for (int i = 0; i < cars.size(); i++) {
            if (i < LANE_COUNT) {
                setLane(cars.get(i), lanes.get(i));
            }
            else {
                setLane(cars.get(i), lanes.get(new Random().nextInt(lanes.size())));
            }
        }
        for (int i = 0; i < lanes.size(); i++) {
            lanes.get(i).getCar(0).setVelocityY(Car.getSpeed());
        }
    }

    public void update(float delta) {
        checkLanes();
        updateLanes(delta);
        updateCars(delta);
    }

    public boolean collision() {
        for (int i = 0; i < cars.size(); i++) {
            for (int j = 0; j < cars.size(); j++) {
                if (i == j) continue;
                if (cars.get(i).collides(cars.get(j)))
                    return true;
            }
        }
        return false;
    }

    public void stop() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).stop();
        }
    }

    public void onRestart() {
        for (int i = 0; i < lanes.size(); i++) {
            for (int j = 0; j < cars.size(); j++) {
                lanes.get(i).removeCar(cars.get(j));
                cars.get(j).onRestart();
            }
        }
        for (int i = 0; i < cars.size(); i++) {
            if (i < LANE_COUNT) {
                setLane(cars.get(i), lanes.get(i));
            }
            else {
                setLane(cars.get(i), lanes.get(new Random().nextInt(lanes.size())));
            }
        }
        for (int i = 0; i < lanes.size(); i++) {
            lanes.get(i).getCar(0).setVelocityY(Car.getSpeed());
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    private void setLane(Car car, Lane l) {
        if (!l.isFull() && !l.isOccupied()) {
            car.setLane(l);
            car.setX(l.getPosition());
            l.addCar(car);
        }
    }

    private void updateCars(float delta) {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).update(delta);
        }
    }

    private void updateLanes(float delta) {
        for (int i = 0; i < lanes.size(); i++) {
            lanes.get(i).update(delta);
        }
    }

    private void checkLanes() {
        ArrayList<Integer> used = new ArrayList<Integer>(4);
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getLane() == null) { // Car doesn't have a lane assignment
                int idx = new Random().nextInt(lanes.size());
                if (!used.contains(idx)) { // Haven't placed a car in this lane this go around
                    used.add(idx);
                    Lane l = lanes.get(idx);
                    setLane(cars.get(i), l);
                }
            }
        }
    }
}
