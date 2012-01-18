/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package team3329.control;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author boys
 */
public class CustomJoystick extends Joystick{

    private boolean thrStatus = false;
    private double min = 0;
    private double max = 0;

    public CustomJoystick(int port, boolean status, double min, double max)
    {
        super(port);
        this.thrStatus = status;
    }

    public void setThreshold(int min, int max)
    {
        this.min = normalizeValue(min);
        this.max = normalizeValue(max);
    }

    public double getJoystickValue(int axis)
    {
        if(thrStatus == true)
        {
           if( getRawAxis(axis) < this.min) return min;
           if( getRawAxis(axis) > this.max) return max;
        }

        return getRawAxis(axis);
    }

    public double getMin(){return min;}
    public double getMax(){return max;}
    public boolean getStatus(){return thrStatus;}
    
    private double normalizeValue(double in)
    {
        if(in > 1.0) return 1;
        if(in < 0.0) return 0;

        return in;
    }

}
