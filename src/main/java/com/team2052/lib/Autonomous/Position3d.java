package com.team2052.lib.Autonomous;

/**
 * Created by KnightKrawler on 6/27/2018.
 */
public class Position3d {

    private double x;
    private double y;
    private double z;


    /**
     * create a position2d with an x,y and theta
     * @param x y value
     * @param y x value
     * @param z z
     */
    public Position3d(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * create a position2d with an x and y only
     * @param x y value
     * @param y x value
     */

    /**
     * create a position2d with x, y and z equal to 0
     */
    public Position3d(){
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void reset(){
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * get the hypotenuse of a triangle with sides x and y
     * @return hypotenuse of the triangle
     */
    public double getHype(){
        return Math.sqrt(x * x + z * z);
    }

    /**
     * translate a position2d
     * @param other the amount to translate by
     * @return a translated position2d
     */
    public Position3d translateBy(Position3d other){
        return new Position3d(this.x + other.x, this.y + other.y, this.z + other.z);
    }


    public double getAngleFromTrig(){
        return Math.atan(z / x);
    }

    public static double distanceFormula(Position3d first, Position3d second){
        return Math.sqrt(Math.pow(second.y - first.y,2) + Math.pow(second.x - first.x,2) + Math.pow(second.z - first.z,2));
    }

    public static double distanceFormula2d(Position3d first, Position3d second){
        return Math.sqrt(Math.pow(second.x - first.x,2) + Math.pow(second.z - first.z,2));
    }

    @Override
    public String toString() {
        return "(f/y: " + x + " , l/x: " + y;
    }

    public Position3d getClone(){
        Position3d clone = new Position3d();
        clone.setX(this.getX());
        clone.setY(this.getY());
        clone.setZ(this.getZ());
        return clone;

    }

    public Position2d getPosition2d(){
        return new Position2d(z, x);
    }
}
