/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package team3329.drive;

/**
 *
 * @author boys
 */
public interface DriveMotorPIDListener
{
    public void getMotorValue(double value);
    public double getCurrentDistance();
}
