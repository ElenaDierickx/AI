package be.odisee.search;

import be.odisee.search.framework.RandomGenerator;

import java.util.Random;

public class City {
    private int x;
    private int y;

    public City(){
        x = RandomGenerator.random.nextInt();
        y = RandomGenerator.random.nextInt();
    }

    public int getDistance(City city){
        int distanceX = Math.abs(city.getX() - this.getX());
        int distanceY = Math.abs(city.getY() - this.getY());
        int distance = (int)Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
        return distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
