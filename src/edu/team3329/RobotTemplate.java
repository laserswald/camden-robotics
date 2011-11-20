/**author= "Ben Davenport-Ray"
 *
 */


package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.*; // import everything in wplibj. what the hell.

public class RobotTemplate extends SimpleRobot { 
	//initialization of robot parts and control.
	RobotDrive drive = new RobotDrive(1);
	Joystick mainstick = new Joystick(1);
	AxisCamera camera = AxisCamera.getInstance(); //the WPIlib apparently supports direct camera feed just by initializing the camera. Check this.
	camera.writeResolution(k160x120);


	public RobotTemplate(){
		//runs when robot is executed.
		getWatchdog().setenabled(false);
	}

	public void autonomous() {
		/* Code for the AI goes HERE. some kind of loop, with checking for the ellipse and decisions on what 
		 * to do with it. For now, here is the demo square thing from the tutorial.
		 */

       	} 

	public void operatorControl() {
		while (true && isOperatorControl() && isEnabled()){ //If the robot is on and in teleop...
			camera.getImage();              //Set up the camera...
			drive.arcadeDrive(mainstick);   //drive through using the joystick and...
			Timer.delay(0.005);             //wait a bit.
			}
       	}
}
