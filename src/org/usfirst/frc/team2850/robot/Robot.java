
package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static Joystick xbox1;
    public static PowerDistributionPanel pdp;
	public static Spark motor1;
	
	public static double maxCurrent = 0;
	
	
	public static double avgCurrent = 0;
	public static double iterations = 0;
	public static double sumCurrent = 0;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	xbox1=new Joystick(0);
    	motor1=new Spark(0);
    	pdp = new PowerDistributionPanel();
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    if(xbox1.getRawButton(0))
    	motor1.set(1.0);
    if(maxCurrent<pdp.getCurrent(0))
    	maxCurrent=pdp.getCurrent(0);
    sumCurrent += pdp.getCurrent(0);
    avgCurrent = sumCurrent/iterations;
    iterations++;
    System.out.println("Max Current Draw: " + Double.toString(maxCurrent) + "/n Avg Current Draw: " + Double.toString(avgCurrent));
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}