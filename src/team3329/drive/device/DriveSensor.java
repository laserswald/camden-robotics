package team3329.drive.device;

/**
 *
 * @author Noah Harvey
 */

public interface DriveSensor {

    public double getDriveDistance();
    public double getDriveSpeed();
    public void Reset();
    public void Start();
    public void Stop();
    public boolean Stopped();
}
