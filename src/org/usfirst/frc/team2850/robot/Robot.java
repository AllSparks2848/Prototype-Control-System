
package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static Joystick xbox1;
	
	public static RobotDrive drivetrain;
	public static Spark leftDrive1;
	public static Spark rightDrive1;
	public static Spark leftDrive2;
	public static Spark rightDrive2;
	public static Compressor compressor;
	public static Solenoid driveshifter;
    public static boolean high;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	xbox1=new Joystick(0);
    	leftDrive1=new Spark(0);
    	leftDrive2=new Spark(1);
    	rightDrive1=new Spark(2);
    	rightDrive2=new Spark(3);
    	
    	drivetrain= new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2 );
    	
    	compressor = new Compressor();
    	driveshifter=new Solenoid(0);
       
        
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
    	drivetrain.arcadeDrive(-xbox1.getRawAxis(1), -xbox1.getRawAxis(4));
    	if(xbox1.getRawButton(5)){
    		high=false;
    	}
    	
    	if(xbox1.getRawButton(6)){
    		high=true;
    	}
    	
    	driveshifter.set(high);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}