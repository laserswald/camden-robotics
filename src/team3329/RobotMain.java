package team3329;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.camera.AxisCamera;

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
    public final double wheelContactWidth = 20.5;
    public final double dpc = .038226705;
    public final int digitalChannel = 1;
    //non-device variables
    public CustomDrive customDrive;

    //device variables
    //drive sensors
    public DriveSensors driveSensors;
    public DriveEncoder l_driveEncoder;
    public DriveEncoder r_driveEncoder;
    public Gyro gyro;
    
    //drive speedControllers
    public Victor l_SpeedController;
    public Victor r_SpeedController;

    //human interface devices (HID)
    public Joystick joystick1;

    /*
     * Robot init code. Override from IterativeRobot
     */
    public void robotInit()
    {
        DriverScreen.printLog("---INIT ROBOT DEVICES---");
        DriverScreen.printLog("---Configuring Drive Devices---");
        //init devices
        this.l_driveEncoder = new DriveEncoder(new DigitalInput(digitalChannel,5), new DigitalInput(digitalChannel,6),false,dpc);
        this.r_driveEncoder = new DriveEncoder(new DigitalInput(digitalChannel,7), new DigitalInput(digitalChannel,8),false,dpc);
        
        this.driveSensors = new DriveSensors(l_driveEncoder, r_driveEncoder);
        
        
        l_SpeedController = new Victor(digitalChannel,2);
        r_SpeedController = new Victor(digitalChannel,1);
        
        //init HID
        this.joystick1 = new Joystick(1);  //later the HID will be a custom joystick
                                      	   //that will work with the customDrive
        
        //init program controllers
	Navigation.init(wheelContactWidth, this.driveSensors);
        this.customDrive = new CustomDrive(l_SpeedController, r_SpeedController);
        
    }

   /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        //customDrive.arcadeDrive(joystick1);  
        Navigation.getInstance().addNextCoordinate(new CartesianVector(80,80));
        //System.out.println();
        
    }
 	
	/*
	 * call just before running init 
	 */    
    public void teleopInit()
    {
        //customDrive.setOverride(true); //allow direct voltage control
        //Navigation.getInstance().addNextCoordinate(new CartesianVector(100,100));
        
        //l_driveEncoder.setDistancePerPulse(1);
        //r_driveEncoder.setDistancePerPulse(1);
    }

    public void robotConfig()
    {
        ;//later mess with robot config stuff
    }
}
