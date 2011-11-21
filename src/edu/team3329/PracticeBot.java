/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.team3329;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PracticeBot extends SimpleRobot {
    //TODO: Check if we need to do this or not.
	RobotConfig config;
	AxisCamera camera;
    Joystick[] stick;
	PWM[] motor;
	RobotDrive drive;
    Encoder[] encoder;

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
        Encoder encoder1 = new Encoder(1,2);    //create encoder for
        Encoder encoderRightMotor = new Encoder(3,4);   //create encdoers for
        Encoder encoderLeftMotor = new Encoder(5,6);    //the drive motors
    
	public PracticeBot(){
		loadConfig();
		setupDrive();
		setupRotation();
		setupCamera();
	}

	private void loadConfig(){
		
	}
	
	private void setupDrive(){
		
	}
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        /* blarg blarg. I is editing this file */ 
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
           driveMotors.arcadeDrive(joystick1.getMagnitude(),joystick1.getDirectionDegrees());
        }
        
    }
}
