/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class uses a custom joystick operations for the LogiTech Atack Joysticks
 * Its main purpose is to add a small threshold to values determined by
 * this Joystick.getRawAxis() method
 * @author boys
 */
public class CustomJoystick extends Joystick{

    /*
     * Thresholds for scaling values
     */

    private double m_threshold = .025;

    public CustomJoystick(int port, double threshold)
    {
        super(port);
        this.m_threshold = threshold;
    }

    public CustomJoystick(int port, int numAxis, int numButtons)
    {
        super(port, numAxis, numButtons);
    }

    //return a thresholded value of the x and y values

    public double get_X()
    {
        return scale(getX());
    }

    public double get_Y()
    {
        return scale(getY());
    }

    public double get_Throttle()
    {
        return scale(getThrottle());
    }

    //return a vector2 form of the joystick x and y values

    public Vector2 getVector()
    {
        return new Vector2(this.get_X(), this.get_Y(), true);
    }

    /**
     * @return the m_threshMin
     */
    public double getThresholdValue()
    {
        return m_threshold;
    }

    /**
     * @param set the new threshold value
     */
    public void setThresholdValue(double threshold)
    {
        this.m_threshold = threshold;
    }

    
    /*
     * scales the given value between a given threshold
     */
    private double scale(double value)
    {
        if (value > -m_threshold && value < m_threshold)
        {
            return 0;
        }
        else
        {
            return value;
        }
    }

}
