package team3329.util;

/**
 * This class allows all classes to access the driver screen and print as needed
 * @author Noah Harvey
 */

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Dashboard;

public class DriverScreen {

    public static DriverStationLCD driverLCD = DriverStationLCD.getInstance();
    

    private DriverScreen()
    {
        //later --> add other utilities to this class
    }

    //for now this class only prints to the driverLCD
    public static void printLog(String log)
    {
        //driverLCD.println(DriverStationLCD.Line.kMain6, 1, log);
        System.out.println(log);
    }

}
