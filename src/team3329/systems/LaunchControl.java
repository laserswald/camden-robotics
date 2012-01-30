/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team3329.systems;

/**
 * ============SINGLETON========================
 * @author Noah Harvey
 */

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;

public class LaunchControl {
    
    //hold singleton instance
    private static LaunchControl instance = null;
    
    //device variables
    private Relay r_launchRelay;
    private Relay l_launchRelay;
    private Relay loadRelay;
    private DigitalInput loadSwitch;
    
    //control variables
    private int triggerButton = 1; //which button number the shoot button is.
								   //default is 1
    private boolean launchState; //whethere the launch wheels are on or not
    private boolean loadState;  //whether the window motor is spinning or not
    private Joystick controller;
    
    //=============INIT======================================|
    public static void init(Relay rightMotor, Relay leftMotor, Joystick controller,
            DigitalInput limitswitch, Relay loadRelay)
    {   
        if(instance == null)
        {            
            instance = new LaunchControl(rightMotor, leftMotor, controller, 
                    limitswitch, loadRelay);
        }
    }
    
    protected LaunchControl(Relay rightMotor, Relay leftMotor, Joystick ctrl,
            DigitalInput limitswitch, Relay ldRelay)
    {
        r_launchRelay = rightMotor; //init devices
        l_launchRelay = leftMotor;
        loadSwitch = limitswitch;
        loadRelay = ldRelay;
        
        controller = ctrl; //init control variables
        launchState = false;
        loadState = false;
    }
    
    public static LaunchControl getInstance()
    {
        return instance;
    }
    //=======================================================|
    
    public void shootBall() 
    {
        //if the relay switch is pressed then either stop the loading
        //or continue loading depending on the value of the trigger
        if(loadSwitch.get())
        {
            if(controller.getRawButton(triggerButton))
            {
                loadState = true;
                launchState = true;
            }
            else
            {
                loadState = false;
                launchState = false;
            }
            
            loadAndShoot();
        }
    }
    
    public void setTriggerButton(int w)
    {
        triggerButton = w;
    }
    
    private void loadAndShoot()
    {
        if(launchState)
        {
            l_launchRelay.set(Relay.Value.kOn);
            r_launchRelay.set(Relay.Value.kOn);
        }
        else
        {
            l_launchRelay.set(Relay.Value.kOff);
            r_launchRelay.set(Relay.Value.kOff);
        }
        if(loadState)
        {
               loadRelay.set(Relay.Value.kOn);      
        }
        else 
        { 
            loadRelay.set(Relay.Value.kOff);
        }
    
    }
}
    
