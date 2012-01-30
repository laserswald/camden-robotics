package team3329.drive;

import java.util.Vector;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Noah Harvey
 */
public class DriveMotorPID implements PIDOutput, PIDSource
{
    private PIDController pidController;
    private double targetValue = 0;
    

    private DriveMotorPIDListener drivePIDListener = null;

    public DriveMotorPID(double Kp, double Ki, double kd)
    {
        pidController = new PIDController(Kp, Ki, kd,
            this, this);
        pidController.setOutputRange(-1.0, 1.0);
        pidController.setTolerance(.05);
        pidController.enable();
    }

    public void disablePID()
    {
        pidController.disable();
    }

    public void enablePID()
    {
        pidController.enable();
    }

    public void setTargetValue(double value)
    {
        System.out.println(value);
        pidController.setSetpoint(value);
    }

    public void pidWrite(double value)
    {
        drivePIDListener.getMotorValue(value);
    }

    public double pidGet()
    {
        pidController.onTarget();
        return drivePIDListener.getCurrentDistance();
    }

    public boolean isOnTarget()
    {
        return pidController.onTarget();
    }

    public void addDriveMotorListener(DriveMotorPIDListener ls)
    {
        this.drivePIDListener = ls;
    }
}
