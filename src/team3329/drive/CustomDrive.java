package team3329.drive;

/**
 * Handles the drive of the robot by pulling in the desired coordinates to
 * PID motor handlers
 * @author Noah Harvey
 */

import team3329.util.*;
import team3329.vector.*;
import java.util.Timer;
import java.util.TimerTask;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class CustomDrive
{
    //Timer that runs the TimerTask
    private Timer driveTimer = new Timer();

    //create a drive task for access later
    private DriveTask driveTask;
    
    //describes whether the drive interface is in auto or direct drive
    private boolean m_override = false; 
    
    //PID controller constants
    private double Kp;
    private double Ki;
    private double Kd;

    //PID controllers for each motor
    //only two are needed incase of a 4 motor drive
    //because two motors are coupled
    private PIDController m_leftMotorPID;
    private PIDController m_rightMotorPID;

    //speed controllers for motor *USE THE DriveMotor CLASS*
    private DriveMotor m_leftMotor;
    private DriveMotor m_rightMotor;
    
    //allow for the use of a 4 motor drive or a two motor drive
    public CustomDrive(DriveMotor l_driveMotor, DriveMotor r_driveMotor)
    {
        m_leftMotor = l_driveMotor;
        m_rightMotor = r_driveMotor;
        init();
    }

    //init the left and right motor pid controllers
    //and the timer for the drive controllers
    private void init()
    {
        //the encoders are from the Navigation class
        Navigation.m_leftEncoder.start();
        Navigation.m_rightEncoder.start();

        //sets the encoders to act as a distance process variable
        //for the PID controllers
        Navigation.m_leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        Navigation.m_rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

        //create PID controllers
        m_leftMotorPID = new PIDController(Ki,Kp,Kd, Navigation.m_leftEncoder, m_leftMotor);
        m_rightMotorPID = new PIDController(Ki,Kp,Kd,Navigation.m_rightEncoder, m_rightMotor);

        m_leftMotorPID.enable();
        m_rightMotorPID.enable();

        //start the driving!
        driveTask = new DriveTask(this);
        driveTimer.scheduleAtFixedRate(driveTask, 0L, 50);
    }

    

    //pulls the next coordinate and sets the left and right motor values
    //if the current left and right values sare within the correct tolerance
    public void driveNext()
    {
        PolarVector c = Navigation.getInstance().getNextCoordinate();
        double angle = c.getDirection();
        double distance = c.getDistance();
        double wheelWidth = RobotProperties.wheelContactWidth;

        //get left and right distances
        double rightDistance = ((wheelWidth*angle) + (2*distance))/2;
        double leftDistance = ((2*distance) - (wheelWidth*angle))/2;

        m_leftMotorPID.setSetpoint(leftDistance);  //send distances to PID
        m_rightMotorPID.setSetpoint(rightDistance); //loops that control the motor ouput

    }

    //drives the robot according to tthe current drive values
    public void drive(double l, double r)
    {
        if(m_override)
        {
            m_leftMotor.set(l);
            m_rightMotor.set(r);
        }
    }

    //for debugging log the current motor values
    public void logMotorValue()
    {
        String lv = String.valueOf(m_leftMotor.getMotorValue());
        String rv = String.valueOf(m_rightMotor.getMotorValue());

        DriverScreen.printLog("\nCurrent Drive Motor Values:: Left: "+lv+"Right:  "+rv);
    }

    //returns whether the robot itself is at its current setPoints
    public boolean onTarget()
    {
        if(m_leftMotorPID.onTarget() && m_rightMotorPID.onTarget()) return true;
        return false;//return whether we are at the desired point
    }

    //safety code that stops the robot immediately and clears the
    //drive coordinates
    public void stopNow()
    {
        driveTask.Stop();

        m_leftMotor.set(0);
        m_rightMotor.set(0);

        m_leftMotorPID.disable();
        m_rightMotorPID.disable();

        //set the setpoints to 0 so the system doesn't go wacky when it is
        //started back up
        m_leftMotorPID.setSetpoint(0);
        m_rightMotorPID.setSetpoint(0);
        
        Navigation.getInstance().reset();
    }

    //disable the PID loop controller to allow external take over of the
    //drive motors
    public void disablePID()
    {
        //stop the drive task to
        driveTask.Stop();

        m_leftMotorPID.disable();
        m_rightMotorPID.disable();

        m_leftMotorPID.setSetpoint(0);
        m_rightMotorPID.setSetpoint(0);
    }

    //compliment of stopNow()
    //starts the drive again
    public void startNow()
    {
        m_leftMotorPID.enable();
        m_rightMotorPID.enable();

        driveTask.Go();
    }
    
    //allows usr to change the drive between automode (uses drive PID and coordinates)
    // or override mode(allows usr to directly specify motor output)
    public void setOverride(boolean setting)
    {
        //to avoid stopping the robot for no reason
        //check to see if we are already in the state we want
        if(m_override == setting) return;
        
        m_override = setting;

        if(m_override)
        {
            stopNow();
        }
        else
        {
            startNow();
        }
    }
    
}

//timertask to feed the PID loops the next value
//when needed
class DriveTask extends TimerTask
{
    CustomDrive driveCtrl;
    private boolean halt = false;
    
    public DriveTask(CustomDrive d)
    {
        driveCtrl = d;
    }

    public void run()
    {
        if(!halt) //if we do not want to halt then execute drive
        {
            //if the current drive motors are at the target value then read
            //the next coordinate and drive to it
            if(driveCtrl.onTarget()) driveCtrl.driveNext();
        }
    }

    public void Stop()
    {
        this.halt = true;
    }

    public void Go()
    {
        this.halt = false;
    }
}


