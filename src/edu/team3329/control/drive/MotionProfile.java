/// Motion profiling code.

package team3329.control.drive;

import edu.wpi.first.wpilibj.templates.MotionProfile.ProfileType;

/**
 *This is a motion profiling class that uses point to 
 * @author boys
 */
public class MotionProfile {

    //contain both polar and cartesian coordinates of profile in a vector
    private CoordinateVector heading;    //contains the loads current heading
    private ProfileType profile_type;
    
    //contain the profiles currentValue and desiredValue (desired value)
    //the value unit (radians, feet, seconds etc...) are determined by accessing
    //classes
    private double desiredValue;
    private double currentValue;

    /*
     * @return init MotionProfile with different settings options
     */
    public MotionProfile()
    {
        profile_type = ProfileType.Default;
    }

    public MotionProfile(ProfileType profile)
    {
        profile_type = profile;
    }

    /**
     * @return the desiredValue
     */
    public double getSetValue()
    {
        return desiredValue;
    }

    /**
     * @param desiredValue the desiredValue to set
     */
    public void setSetValue(double setValue)
    {
        this.desiredValue = setValue;
    }

    /**
     * @return the currentValue
     */
    public double getCurrentValue()
    {
        return currentValue;
    }

    /**
     * @param currentValue the currentValue to set
     */
    public void setCurrentValue(double currentValue)
    {
        this.currentValue = currentValue;
    }

    /**
     * @return the heading
     */
    public CoordinateVector getHeading()
    {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(CoordinateVector heading)
    {
        this.heading = heading;
    }

    /**
     * @return the profile_type
     */
    public ProfileType getProfile_type()
    {
        return profile_type;
    }

    /**
     * @param profile_type the profile_type to set
     */
    public void setProfile_type(ProfileType profile_type)
    {
        this.profile_type = profile_type;
    }

    
	//Hey, 1.3 doesn't allow enums? I beg to differ, i saw some in some crazy folder.
    /*
     * different profile types
     * Follows enum type since the FRC code is lame and doesn't support past v1.3
     */
    public static class ProfileType
    {
        public final int value;

        public static final ProfileType Default = new ProfileType(0);
        public static final ProfileType Trapezoidal = new ProfileType(1);
        public static final ProfileType S_Curve = new ProfileType(2);

        private ProfileType(int arg)
        {
            this.value = arg;
        }
    }

}
