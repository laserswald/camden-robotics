/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team3329;

import com.sun.squawk.util.*;
/**
 * Contains basic vector -2D- details as well as operations
 * @author boys
 */
public interface CoordinateVector
{
    public double getDistance();
    public double getHeading();
    public void add(CoordinateVector v);
    public double dotProduct(CoordinateVector v);   
}
