/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package team3329.control.drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
/**
 *
 * @author boys
 */
public class CustomDrive extends RobotDrive{

    

    public CustomDrive(SpeedController lport, SpeedController rport)
    {
        super(lport,rport);
    }

    public CustomDrive(SpeedController flport, SpeedController rlport,
            SpeedController frport, SpeedController rrport)
    {
        super(flport,rlport,frport,rrport);
    }



}
