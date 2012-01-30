package team3329.drive.controller;

/**
 *
 * @author Noah Harvey
 */

import edu.wpi.first.wpilibj.Joystick;

import team3329.vector.*;
import team3329.drive.Navigation;

import java.util.Timer;
import java.util.TimerTask;

public class CustomJoystick extends Joystick
{
    private long interval;
    public static Timer timer;  //timer task controller for joysticks
    
    public CustomJoystick(int i, long interval)
    {
        super(i);
        setTimeInterval(interval);
        timer.scheduleAtFixedRate(new JoystickTask(this), this.interval,2);
    }
    
    //return next coordinate to drive to
    public synchronized PolarVector getCoordinate()
    {
        return new PolarVector();//later return the new drive coordinate
                                 //the angle of the coordinate coorisponds to 
                                 //the robot rotation
    }
    
    public synchronized void setTimeInterval(long time)
    {
        this.interval = time;
    }

    public synchronized void run()
    {
        double x = this.getX();
        double y = this.getY();
        
        
    }
}

class JoystickTask extends TimerTask
{
    private CustomJoystick jStick;
    public JoystickTask(CustomJoystick j)
    {
        this.jStick = j;
    }
    
    public void run()
    {
        jStick.run();
    }
}
