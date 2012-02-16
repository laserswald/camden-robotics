package team3329.drive;

/**
 * ----------------SINGLETON---------------------------------
 * Manages coordinates that the robot is to use to drive with
 * Uses drive sensors to get current pos
 * Holds target coordinates and updates them
 * @author Noah Harvey
 */
import java.util.Vector;
import edu.wpi.first.wpilibj.Encoder;
import team3329.util.*;
import team3329.util.Queue;

import team3329.vector.*;
public class Navigation
{
    //instance variable

    private static Navigation instance;

    //width between contact points on wheels
    private PolarVector heading;
    private Queue posCoordinates;
    
    //drive encoders
    public static Encoder m_leftEncoder;
    public static Encoder m_rightEncoder;

    //============================SINGLETON PATTERN==========================================|
    public static void init(Encoder left_encoder, Encoder right_encoder)
    {
        instance = new Navigation(new CartesianVector(), left_encoder, right_encoder);
    }

    public static void init(CoordinateVector h, double cWidth, Encoder left_encoder, Encoder right_encoder)
    {
        instance = new Navigation(h, left_encoder, right_encoder);
    }

    private Navigation(CoordinateVector h, Encoder lEncoder, Encoder rEncoder)
    {
        this.heading = new PolarVector(h);

        m_leftEncoder = lEncoder;//create encoders
        m_rightEncoder = rEncoder;

        posCoordinates = new Queue();
    }

    public static Navigation getInstance()
    {
        return instance;
    }

    //=================================================================================================|
    //reset the current heading to origin
    public void reset()
    {
        updateHeading(new PolarVector(0, 0));
        posCoordinates = new Queue();
        posCoordinates.add(new PolarVector(0,0));
    }

    //update current heading based off of sensor readings
    public synchronized void updateHeading()
    {
        double lD = m_leftEncoder.getDistance();//get distance from encoders
        double rD = m_rightEncoder.getDistance();

        double angle = (rD - lD) / RobotProperties.wheelContactWidth;
        double distance = (rD + lD) / 2;

        heading.setDistance(distance);
        heading.setDirection(angle);

        logHeading();
    }

    //update current heading based on given coordinates
    public synchronized void updateHeading(CoordinateVector h)
    {
        heading = new PolarVector(h);

        logHeading();
    }

    //return current heading angle
    public double getHeadingAngle()
    {
        updateHeading();
        return heading.getDirection();
    }

    //return current heading distance
    public double getHeadingDistance()
    {
        updateHeading();
        return heading.getDistance();
    }

    //return the current speed of the robot
    //that is moving in a straight line
    public double getHeadingSpeed()
    {
        double rSpeed = m_leftEncoder.getRate();
        double lSpeed = m_rightEncoder.getRate();

        return (lSpeed + rSpeed)/ 2;
    }

    //add another coordinate to the back of the coordinate stack
    public void addNextCoordinate(CartesianVector c)
    {
        posCoordinates.add(new PolarVector(c));
    }

    public void addNextCoordinate(PolarVector c)
    {
        posCoordinates.add(c);
    }

    //pulls the next coordinate off of the coordinate stack
    public PolarVector getNextCoordinate()
    {
        return (PolarVector) posCoordinates.pull();
    }

    //returns the next coordinate to be pulled
    public PolarVector previewNextCoordinate()
    {
        return (PolarVector) posCoordinates.search(posCoordinates.length()-1);
    }

    //log the current heading
    public void logHeading()
    {
        String d = String.valueOf(heading.getDirection());
        String di = String.valueOf(heading.getDistance());

        DriverScreen.printLog("\nCurrent Heading:: Direction: " + d + "\nDistance:  " + di);
    }
}
