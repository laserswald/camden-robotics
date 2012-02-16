package team3329.util;

/**
 * This class allows all classes to access the driver screen and print as needed
 * @author Noah Harvey
 */

import edu.wpi.first.wpilibj.DriverStationLCD;

public class DriverScreen {

    private DriverScreen()
    {
        
    }

    //for now this class only prints to the driverLCD
    public static void printLog(String log)
    {
        //driverLCD.println(DriverStationLCD.Line.kMain6, 1, log);
        System.out.println(log);
    }
    
    //call this method periodically to update the driver station
    public static void updateDriverStation()
    {
        DriverStationLCD.getInstance().updateLCD();
    }

}
