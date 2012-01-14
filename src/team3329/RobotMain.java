/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package team3329;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

import team3329.util.DriverScreen;
import team3329.drive.*;
import team3329.drive.device.*;
import team3329.vector.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot
{

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    //config vars
    public final double wheelContactWidth = 36;
    public boolean doneConfig = false;
    public final double kdpc1 = 2*Math.PI*3/100;
    public final double kdpc2 = 2*Math.PI*3/360;
    
    //non-device variables
    public Navigation robotNav;
    public CustomDrive customDrive;

    //device variables
    //drive sensors
    public DriveSensors driveSensors;
    public DriveEncoder l_driveEncoder;
    public DriveEncoder r_driveEncoder;

    //drive speedControllers
    public Victor lSpeedController;
    public Victor rSpeedController;

    //human interface devices (HID)
    public Joystick joystick1;

    public void robotInit()
    {
        DriverScreen.printLog("---INIT ROBOT DEVICES---");

        //init devices
        this.l_driveEncoder = new DriveEncoder(5,6,false,kdpc1);
        this.r_driveEncoder = new DriveEncoder(7,8,false,kdpc1);
        
        DriverScreen.printLog("---Configuring Drive Encoders---");
        
        this.driveSensors = new DriveSensors(l_driveEncoder, r_driveEncoder);
        this.lSpeedController = new Victor(1);
        this.rSpeedController = new Victor(2);
        
        //init HID
        this.joystick1 = new Joystick(1);  //later the HID will be a custom joystick
                                      //that will work with the customDrive
        
        //init program controllers
        this.robotNav = new Navigation(wheelContactWidth,driveSensors);
        this.customDrive = new CustomDrive(lSpeedController, rSpeedController);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        //for now don't use the joystick as this is unstable physically till
        //further testing
        //double x = this.joystick1.getX();  //use the joystick to tell the robot
        //double y = this.joystick1.getY();  //where to move

        double x = 36;
        double y = 36;

        //testing the new Drive code
        robotNav.updateHeading(new CartesianVector(x,y));
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    }
    
    //configure the driveEncoders
    public void configureEncoders()
    {       
       ;//later config drive encoders
    }
}
