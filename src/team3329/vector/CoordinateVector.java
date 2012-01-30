/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3329.vector;

/**
 * Coordinate vector interface.
 * @author Ben Davenport-Ray
 */
public interface CoordinateVector
{
    public double getDistance();
    public void setDistance(double dist);
    public double getDirection();
    public void setDirection(double heading);
    public void add(CoordinateVector v);
    public double dotProduct(CoordinateVector v);   
}
