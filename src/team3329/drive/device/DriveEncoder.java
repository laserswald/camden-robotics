package team3329.drive.device;

/**
 *
 * @author Noah Harvey
 */

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

public class DriveEncoder extends Encoder implements DriveSensor{

    public DriveEncoder(DigitalInput aSlot, DigitalInput bSlot, boolean reverseDirection,
            double distancePerPulse)
    {
        super(aSlot,bSlot,reverseDirection);
        //set the distance perPulse
        setDistancePerPulse(distancePerPulse);
        this.Start();
    }

    //return the distance driven
    public double getDriveDistance()
    {
        return getDistance();
    }

    public double getDriveSpeed()
    {
        return getRate();
    }

    public void Reset()
    {
        reset();
    }

    public void Start()
    {
        start();
    }

    public void Stop()
    {
        stop();
    }

   public boolean Stopped()
   {
       return getStopped();
   }

}
