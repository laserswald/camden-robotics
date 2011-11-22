/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team3329;

import com.sun.squawk.util.*;
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
