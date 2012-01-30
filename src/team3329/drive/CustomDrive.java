package team3329.drive;

/**
 * Handles the drive of the robot by pulling in the desired coordinates to
 * PID motor handlers
 * @author Noah Harvey
 */

import team3329.util.*;
import team3329.vector.*;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.Timer;
import java.util.TimerTask;

public class CustomDrive extends RobotDrive{
    
    
    private Timer driveTimer;
    private DriveTask driveTask;
    private boolean override = false; //describes whether the drive interface 
                                          //is in auto or direct drive

    private DriveMotorPID leftMotorPID = new DriveMotorPID(.01,0.01,0);
    private DriveMotorPID rightMotorPID = new DriveMotorPID(.01,0.01,0);

    private double leftMotorValue;
    private double rightMotorValue;

    public CustomDrive(SpeedController fl, SpeedController rl,
            SpeedController fr, SpeedController rr)
    {
        super(fl,rl,fr,rr);
        init();
    }

    public CustomDrive(SpeedController l, SpeedController r)
    {
        super(l,r);
        init();
    }

    //init the left and right motor pid controllers
    //and the timer for the drive controllers
    private void init()
    {
        //init motorPID Controllers
        leftMotorPID.addDriveMotorListener(new DriveMotorPIDListener()
        {
            public void getMotorValue(double v)
            {
                leftMotorValue = v;
            }

            public double getCurrentDistance()
            {
                return Navigation.getInstance().getCurrentLeftDistance();
            }
        });

        rightMotorPID.addDriveMotorListener(new DriveMotorPIDListener()
        {
            public void getMotorValue(double v)
            {
                rightMotorValue = v;
            }

            public double getCurrentDistance()
            {
                return Navigation.getInstance().getCurrentRightDistance();
            }
        });

        leftMotorPID.setTargetValue(0);
        rightMotorPID.setTargetValue(0);

        leftMotorPID.enablePID();
        rightMotorPID.enablePID();

		//init drive timer
        driveTimer = new Timer();
        driveTask = new DriveTask(this);

		//start the drive timer
        driveTimer.scheduleAtFixedRate(driveTask, 1, 2);
    }

    

    //pulls the next coordinate and sets the left and right motor values
    //if the current left and right values sare within the correct tolerance
    public void driveNext()
    {
        System.out.println("Drive NExt");
        PolarVector c = Navigation.getInstance().getNextCoordinate();
        double a = c.getDirection();
        double d = c.getDistance();
        double w = Navigation.getInstance().getContactWidth();

        //get left and right distances
        double rD = ((w*a) + (2*d))/2; //What are these variables? maybe better names are appropriate. -bdr
        double lD = ((2*d) - (w*a))/2;

        leftMotorPID.setTargetValue(lD);  //send distances to PID 
        rightMotorPID.setTargetValue(rD); //loops that control the motor ouput
        
        System.out.println(rD+" "+lD);
    }

    //drives the robot according to the current drive values
    public void drive()
    {
        if(!override)
        {
            System.out.println(leftMotorPID.isOnTarget());
            this.tankDrive(leftMotorValue,rightMotorValue);
        }
    }

    public void logMotorValue()
    {
        String lv = String.valueOf(this.leftMotorValue);
        String rv = String.valueOf(this.rightMotorValue);

        DriverScreen.printLog("\nCurrent Drive Motor Values:: Left: "+lv+"Right:  "+rv);
    }

    public boolean onTarget()
    {
        if( leftMotorPID.isOnTarget() && rightMotorPID.isOnTarget() ) return true;

        return false;
    }

    //safety code that stops the robot immediately and clears the
    //drive coordinates
    public void stopNow()
    {
        driveTask.Stop();
        leftMotorPID.disablePID();
        rightMotorPID.disablePID();

        leftMotorValue = 0; //stop motors
        rightMotorValue = 0;

        leftMotorPID.setTargetValue(0); //set pid target values to 0
        rightMotorPID.setTargetValue(0);

        Navigation.getInstance().reset();
    }

    //compliment of stopNow()
    //starts the drive again
    public void startNow()
    {
        leftMotorPID.enablePID();
        rightMotorPID.enablePID();

        driveTask.Go();
    }
    
    //allows usr to change the drive between automode (uses drive PID and coordinates)
    // or override mode(allows usr to directly specify motor output)
    public void setOverride(boolean setting)
    {
        override = setting;
    }
    
}

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
            if(driveCtrl.onTarget())
            {
                System.out.println("ON TARGET");
                driveCtrl.driveNext();
            }
            
            driveCtrl.drive();System.out.println();
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


