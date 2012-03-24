package team3329;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.Relay;

import team3329.util.DriverScreen;
import team3329.drive.*;
import team3329.drive.controller.CustomJoystick;
import team3329.systems.firing.*;
import team3329.util.RobotProperties;
import team3329.vector.*;
import team3329.systems.vision.*;
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
    public final double distance_per_count = .038226705;
    public final int digitalChannel = 1;

    //non-device variables
    public CustomDrive customDrive;
    public Loader loader;
    public Shooter shooter;
    
    //device variables
    //sensors
    Encoder lEncoder;
    Encoder rEncoder;
    
    //drive speedControllers
    public DriveMotor lDriveMotor;
    public DriveMotor rDriveMotor;

    //human interface devices (HID)
    public CustomJoystick joystick;
    
    //vision 
    CameraServos camServos;

    /*
     * Robot init code. Override from IterativeRobot
     */
    public void robotInit()
    {
        DriverScreen.printLog("---INIT ROBOT DEVICES---");
        DriverScreen.printLog("---Configuring Drive Devices---");

        lDriveMotor = new DriveMotor(new Jaguar(1),true);
        rDriveMotor = new DriveMotor(new Jaguar(2),false);
        
        //init HID
        joystick = new CustomJoystick(1,1);  //later the HID will be a custom joystick
                                      	   //that will work with the customDrive
        joystick.setRightThreshold(.0078125);
        joystick.setLeftThreshold(.0078125);
        
        //init program controllers
        //custom drive code
        Navigation.init(new Encoder(5,6), new Encoder(7,8));
	customDrive = new CustomDrive(lDriveMotor, rDriveMotor);
        
       //firing and loading sytems 
       shooter = new Shooter(new Relay(1), new Relay(2));
       loader = new Loader(new Relay(3));


       //init servos for camera


       camServos = new CameraServos(new Servo(3), new Servo(4),90,90);
       //init Axis camera
       try{ AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k640x480); }
       catch(Exception e){ e.printStackTrace();}

       
    }

   /*
    * This function is called periodically during operator control
    */
    public void teleopPeriodic()
    {
        
        //because the controller only returns a delta vector
        //add that vector to the next coordinate to be read
        
        /*PolarVector nextCoordinate = Navigation.getInstance().previewNextCoordinate();
        nextCoordinate.add(joystick.getNextCoordinate());

        Navigation.getInstance().addNextCoordinate(nextCoordinate);
        
        //test shooting
        if(joystick.getRawButton(1)) { shooter.startFiring(); loader.startLoading(); }
                else { shooter.stopFiring(); loader.stopLoading(); }
        
        if(joystick.getRawButton(2)) loader.startLoading();
                else loader.stopLoading();


        //test setting the camera positions 
        if(joystick.getRawButton(3) && camServos.getPanAngle() < 180) camServos.setPanAngle(1+camServos.getPanAngle());
        if(joystick.getRawButton(4) && camServos.getPanAngle() > 0) camServos.setPanAngle(1-camServos.getPanAngle());
        if(joystick.getRawButton(5) && camServos.getTiltAngle() < 180) camServos.setTiltAngle(1+camServos.getTiltAngle());
        if(joystick.getRawButton(6) && camServos.getTiltAngle() > 0) camServos.setTiltAngle(1-camServos.getTiltAngle());
        
        */
        
        if(joystick.getRawButton(11)) System.out.println("Pressed 11");
        if(joystick.getRawButton(12)) System.out.println("Pressed 12");
        if(joystick.getRawButton(13)) System.out.println("Pressed 13");
        if(joystick.getRawButton(14)) System.out.println("Pressed 14");

        
        //DO NOT REMOVE THIS METHOD CALL
        //Update the driver station
       // DriverScreen.updateDriverStation();
    }
    
    public void teleopInit()
    {
        DriverScreen.printLog("-------TELEOP INIT------");
        Navigation.getInstance().reset();
       //ImageProcessor processor = new ImageProcessor();
       
       //processor.saveImage(processor.getCameraImage());
       
        //config the robot properties
       /* robotConfig(1);
        Timer.delay(2);
        robotConfig(2);
        customDrive.setOverride(false);*/
    }
 
    
    //use a statemachine to config the robot
    //  phase 0 is to be done manually!: config encoders
    //  1. enable teleop and push the robot a certain distance
    //  2. determine distance per count by dividing distance by encoder count
    //  REPEAT A NUMBER OF TIMES THEN AVERAGE RESULTS

    public void robotConfig(int phase)
    {
        customDrive.setOverride(true);
        if(isEnabled() && this.isAutonomous())
        {
            //phase one: determine the max speed of the robot
            if(phase == 1)
            {
                //run the motors for 20 seconds and get the speed from the encoders
                //average the speeds to get max speed

                double speedData = 0;

                for(int i=0;i<=400;i++)
                {
                    customDrive.drive(1, 1);
                    Timer.delay(.05);
                    speedData += Navigation.getInstance().getHeadingSpeed();
                }

                customDrive.drive(0, 0);

                RobotProperties.maxSpeed = speedData/401;
            }

            //phase 2: determine max accel
            else if(phase == 2)
            {
                //almost the same as phase one. Max accel is determined
                //through starting at 0 velocity and quickly going up to max speed
                //and measuring the time it takes to get there 
                double accelData = 0;

                for(int i = 0;i<=20;i++)
                {
                    //go from 0 to max and record the change in time
                    customDrive.drive(0, 0);
                    //time 1 in seconds
                    long t1 = System.currentTimeMillis()/1000;
                    customDrive.drive(1, 1);

                    double speed = Navigation.getInstance().getHeadingSpeed();
                    
                    long t2 = 0;

                    //only set time 2 if we're at max speed
                    if(speed >= RobotProperties.maxSpeed-.01 && speed <= RobotProperties.maxSpeed-.01)
                    {
                        t2 = System.currentTimeMillis()/1000;
                        accelData += RobotProperties.maxSpeed/(t2-t1);
                    }
                    
                    customDrive.drive(0, 0);
                    Timer.delay(1);
                }

                RobotProperties.maxAccel = accelData/21;
            }

        }
    }
}
