/// A custom drive configuration class.
package edu.team3329;

/**
 *This class is the drive configuration class to create a better drive system
 * @author Noah Harvey
 */
public class RobotKinematics extends MotionProfile
{
    /*
     * set up motion profile for drive
     */
    public double LeftMotorDistance = 0;
    public double RightMotorDistance = 0;

    public RobotKinematics(MotionProfile.ProfileType profile)
    {
        this.setProfile_type(profile);
    }

    public RobotKinematics(double distanceLeft, double distanceRight)
    {
        this.LeftMotorDistance = distanceLeft;
        this.RightMotorDistance = distanceRight;
    }

    public RobotKinematics(ProfileType profile, double distanceLeft, double distanceRight)
    {
        this.setProfile_type(profile);
        this.LeftMotorDistance = distanceLeft;
        this.RightMotorDistance = distanceRight;
    }

    /*
     * update the robots current heading
     * based off of a change in any motor distance
     */
    public void updateHeading()
    {
        this.getHeading().setMagnitude(this.getDistance());
        this.getHeading().setAngle(this.getAngle());
    }

    /*
     * reset the robot heading to (0,0)
     * this makes the current robot position set to (0,0)
     */
    public void resetHeading()
    {
        this.setHeading(new Vector2());
    }

    /*
     * return the translational distance the robot has travelled from the origin
     */
    public double getDistance()
    {
        double calcDistance = (this.getLeftMotorDistance() + this.getRightMotorDistance()) / 2;
        return calcDistance;
    }

    /*
     * return the heading angle the robot as turned from origin
     */
    public double getAngle()
    {
        double calcAngle = (this.getRightMotorDistance() - this.getLeftMotorDistance());
        return calcAngle;
    }

    /**
     * @return the LeftMotorDistance
     */
    public double getLeftMotorDistance()
    {
        return LeftMotorDistance;
    }

    /**
     * @param LeftMotorDistance the LeftMotorDistance to set
     */
    public void setLeftMotorDistance(double LeftMotorDistance)
    {
        this.LeftMotorDistance = LeftMotorDistance;
        this.updateHeading();
    }

    /**
     * @return the RightMotorDistance
     */
    public double getRightMotorDistance()
    {
        return RightMotorDistance;
    }

    /**
     * @param RightMotorDistance the RightMotorDistance to set
     */
    public void setRightMotorDistance(double RightMotorDistance)
    {
        this.RightMotorDistance = RightMotorDistance;
        this.updateHeading();
    }
    
}
