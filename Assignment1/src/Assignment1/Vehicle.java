/**
 * This is part of Assignment 1: Environment Setup and Review of Java for PDP, Fall 2017.
 */
package Assignment1;

/**
 * Vehicle is a simple object that has a velocity and a direction.
 */
// You may not make Ball implement the Comparable interface.
public class Vehicle {

    private double velocity;
    private int direction;

    /**
     * Constructor that creates a new vehicle object with the specified velocity and direction.
     * @param velocity Velocity of the new object.
     * @param direction Direction of the new object, where 1 represents eastbound direction, and 2 westbound direction.
     */
    public Vehicle(double velocity, int direction) {//add this to distinguish the global and local varieties
        this.velocity = velocity;
        this.direction = direction;
    }

    /**
     * Returns the velocity of the Vehicle.
     * @return the velocity of the Vehicle.
     */
    public double getVelocity() { // change return 0 to return velocity
        return velocity;
    }

    /**
     * Returns the direction of the Vehicle.
     * @return the direction of the Vehicle.
     */
    public int getDirection() { // change return 0 to return direction and public double to int
        return direction;
    }
}