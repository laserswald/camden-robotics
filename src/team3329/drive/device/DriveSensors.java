package team3329.drive.device;

/**
 * the drive sensors as a single object
 * @author Noah Harvey
 */

public class DriveSensors
{
    DriveSensor lSensor = null;
    DriveSensor rSensor = null;

    public DriveSensors(DriveSensor l, DriveSensor r)
    {
        lSensor = l;
        rSensor = r;
    }

    public double getLeftDistance()
    {
        return lSensor.getDriveDistance();
    }

    public double getRightDistance()
    {
        return rSensor.getDriveDistance();
    }

    public double getLeftSpeed()
    {
        return lSensor.getDriveSpeed();
    }

    public double getRightSpeed()
    {
        return rSensor.getDriveSpeed();
    }

    public void resetDriveSensors()
    {
        lSensor.Reset();
        rSensor.Reset();
    }

    public void startDriveSensors()
    {
        lSensor.Start();
        rSensor.Start();
    }

    public void stopDriveSensors()
    {
        lSensor.Stop();
        rSensor.Stop();
    }

    public boolean SensorsStopped()
    {
        if(lSensor.Stopped() && rSensor.Stopped()) return true;

        return false;
    }
}
