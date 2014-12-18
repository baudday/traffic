package com.willem.gameobjects;


import java.util.ArrayList;

/**
 * Created by wellis on 12/9/2014.
 */
public class Lane {

    private int position;
    private boolean occupied = false;
    private ArrayList<Car> cars;
    private float threshold;

    public Lane(int position, float threshold) {
        cars = new ArrayList<Car>(2);
        this.position = position;
        this.threshold = threshold;
    }

    public void update(float delta) {
        for (int i = 0; i < cars.size(); i++) {
            // Send a car because the lane isn't occupied and there's one waiting
            if (cars.get(i).getVelocityY() == 0 && !isOccupied()) {
                cars.get(i).setVelocityY(Car.getSpeed());
                occupied = true;
            }
        }
    }

    public boolean isOccupied() {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getY() > threshold && cars.get(i).getVelocityY() != 0) {
                return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        return cars.size() >= 2;
    }

    public int getPosition() {
        return position;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Car getCar(int idx) {
        return cars.get(idx);
    }

    public void removeCar(Car car) {
        car.setLane(null);
        cars.remove(car);
    }
}
