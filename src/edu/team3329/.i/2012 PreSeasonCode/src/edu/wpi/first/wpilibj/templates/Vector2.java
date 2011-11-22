/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.*;
/**
 * Contains basic vector -2D- details as well as operations
 * @author boys
 */
public class Vector2
{
    private double magnitude;
    private double angle;
    private double Xcoor;
    private double Ycoor;
    
    /*
     * Default settings for a vector are 0 instead of null
     */
    public Vector2()
    {
        this.Xcoor = 0;
        this.Ycoor = 0;
        this.magnitude = 0;
        this.angle = 0;
    }
    
    public Vector2(double mag, double angle, boolean startPolar)
    {
        if (startPolar)
        {
            this.magnitude = mag;
            this.angle = angle;
            this.update(false);
        }
        else
        {
            this.Xcoor = mag;
            this.Ycoor = angle;
            this.update(true);
        }
        
    }    
    
    /*
     * Convert between Polar and Cartesian using given coordinates
     */

    public void toCartesian()
    {
        double x = getMagnitude()*Math.cos(getAngle());
        double y = getMagnitude()*Math.sin(getAngle());
        this.setXcoor(x);
        this.setYcoor(y);
    }

    public void toCartesian(double mag, double angl)
    {
        double x = mag*Math.cos(angl);
        double y = mag*Math.sin(angl);
        this.setXcoor(x);
        this.setYcoor(y);
    }

    public void toPolar()
    {
        double mag = Math.sqrt(MathUtils.pow(this.getXcoor(),2)+MathUtils.pow(this.getYcoor(),2));
        double agle = MathUtils.atan(this.getYcoor()/this.getXcoor());
    }

    public void toPolar(double x, double y)
    {
        double mag = Math.sqrt(MathUtils.pow(x, 2) + MathUtils.pow(y, 2));
        double agle = MathUtils.atan(y/x);
    }

    /*
     * @param update coordinates after altering cartesian or polar
     * set toCart to "true" to convert to cartesian coordinates
     * set toCart to "false" to convert to polar
     */
    public void update(boolean toCart)
    {
        if(toCart)
        {
            this.toCartesian();
        }
        else
        {
            this.toPolar();
        }
    }

    /**
     * @return the magnitude
     */
    public double getMagnitude()
    {
        return magnitude;
    }

    /**
     * @param magnitude the magnitude to set
     */
    public void setMagnitude(double magnitude)
    {
        this.magnitude = magnitude;
        this.update(true);
    }

    /**
     * @return the angle
     */
    public double getAngle()
    {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle)
    {
        this.angle = angle;
        this.update(true);
    }

    /**
     * @return the Xcoor
     */
    public double getXcoor()
    {
        return Xcoor;
    }

    /**
     * @param Xcoor the Xcoor to set
     */
    public void setXcoor(double Xcoor)
    {
        this.Xcoor = Xcoor;
        this.update(false);
    }

    /**
     * @return the Ycoor
     */
    public double getYcoor()
    {
        return Ycoor;
    }

    /**
     * @param Ycoor the Ycoor to set
     */
    public void setYcoor(double Ycoor)
    {
        this.Ycoor = Ycoor;
        this.update(false);
    }
}
