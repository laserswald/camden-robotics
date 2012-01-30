package team3329.drive;

/**
 * ----------------SINGLETON---------------------------------
 * Manages coordinates that the robot is to use to drive with
 * Uses drive sensors to get current pos
 * Holds target coordinates and updates them
 * @author Noah Harvey
 */
import team3329.vector.*;
import team3329.util.Queue;
import team3329.drive.device.DriveSensors;
import team3329.util.DriverScreen;

import java.util.Vector;

public class Navigation
{
	//instance variable
	private static Navigation instance;

    private  double widthOfContact;  //the width between the contact points of
    private  PolarVector heading;    //drive wheels

    private  Queue posCoordinates;

    private  DriveSensors driveSensors = null;

    
    //============================SINGLETON PATTERN==========================================|
	 
    public static void init(double contactWidth, DriveSensors sensors)
    {
        instance = new Navigation(new CartesianVector(), contactWidth, sensors);
    }

    public static void init(CoordinateVector h, double cWidth, DriveSensors sensors)
    {
	instance = new Navigation(h, cWidth, sensors);
    }

    private Navigation(CoordinateVector h, double ctWidth, DriveSensors sns)
    {
        this.widthOfContact = ctWidth;
        this.heading = new PolarVector(h);
        this.driveSensors = sns;

        if(!driveSensors.SensorsStopped()) driveSensors.stopDriveSensors(); //if
            //drive sensors are not stopped then stop them
        driveSensors.resetDriveSensors(); //reset the sensors to 0
        driveSensors.startDriveSensors(); //start the sensors to work
        
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
        updateHeading(new PolarVector(0,0));
        posCoordinates = new Queue(new Vector());
    }

    //update current heading based off of sensor readings
    public  synchronized  void updateHeading()
    {
        double rD = driveSensors.getRightDistance();
        double lD = driveSensors.getLeftDistance();
        
        double h = (rD - lD)/widthOfContact;
        double d = (rD + lD)/2;

        heading.setDistance(d);
        heading.setDirection(h);

        logHeading();
    }

    

    //update current heading based on given coordinates
    public synchronized  void updateHeading(CoordinateVector h)
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

    public  synchronized double getCurrentLeftDistance()
    {
        return driveSensors.getLeftDistance();
    }

    public  synchronized double getCurrentRightDistance()
    {
        return driveSensors.getRightDistance();
    }

    //return contact width
    public  double getContactWidth()
    {
        return widthOfContact;
    }

    //add another coordinate to the back of the coordinate stack
    public  void addNextCoordinate(CartesianVector c)
    {
        posCoordinates.add(new PolarVector(c));
    }

    //pulls the next coordinate off of the coordinate stack
    public  PolarVector getNextCoordinate()
    {
        return (PolarVector)posCoordinates.pull();
    }

    //log the current heading
    public  void logHeading()
    {
        String d = String.valueOf(heading.getDirection());
        String di = String.valueOf(heading.getDistance());

        DriverScreen.printLog("\nCurrent Heading:: Direction: "+d+"\nDistance:  "+di);
    }
}
