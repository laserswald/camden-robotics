/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.SimpleRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMainTankDrive extends SimpleRobot
{

    //Create GeneralHumanInterfaceDevices
    //create Joystick with X,Y axis
    Joystick joystick1 = new Joystick(1);
    Joystick joystick2 = new Joystick(2);

    //Create Robot Device Objects
    //---Create motor drive--
    //Motor ControlerType: Victor
    //PWM: Left: 1, Right: 6
    Victor speedControllerLeft = new Victor(1);
    Victor speedControllerRight = new Victor(6);

    //DriveMotorType: TwoMotorDrive
    RobotDrive driveMotors = new RobotDrive(speedControllerLeft,
            speedControllerRight);
    
    //---Create RotaryDigital Encoders---
    //DIO: A: 1, B: 2
    Encoder encoder1 = new Encoder(1, 2);    //create encoder for
    Encoder encoderRightMotor = new Encoder(3, 4);   //create encdoers for
    Encoder encoderLeftMotor = new Encoder(5, 6);    //the drive motors

    /**
     * Sets Start up options
     */
    public RobotMainTankDrive()
    {
        //Remove watchdog timer
        getWatchdog().setEnabled(false);
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous()
    {
        //use a for loop to create a state machine
        //use i as the control variable for each state and manually switch between
        //states or autoswitch
        for (int i = 0; i < 4; i++)
        {
           switch(i)
           {
               case 0:
                   driveMotors.drive(.5, 0);
                   break;
               case 1:
                   driveMotors.drive(0, .5);
                   break;
               case 2:
                   driveMotors.drive(0, -.5);
                   break;
               case 3:
                   driveMotors.drive(-.5, 0);
                   break;
               default:
                   break;
           }
           Timer.delay(.5);
        }

    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        while (isOperatorControl() && isEnabled())
        {
            //driveMotors.arcadeDrive(joystick1);
            driveMotors.tankDrive(joystick1, joystick1);

        }

    }
}
