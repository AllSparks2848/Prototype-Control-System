
package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PIDController;
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
	public static Spark leftDrive3;
	public static Spark rightDrive3;
	public static Compressor compressor;
	public static Solenoid driveshifter;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	
    public static boolean high;
    //how many pulses per rotation? how many feet per rotation? (gears, tires, CIM speed)
    public static double DIST_PER_PULSE = .2;
   	
    public static double Kp = .005;
    public static double Ki = .005;
    public static double Kd = .005;
    
   	public static PIDController linearDriveLeft = new PIDController(Kp, Ki, Kd, leftEncoder, leftDrive1);
   	public static PIDController linearDriveRight = new PIDController(Kp, Ki, Kd, rightEncoder, rightDrive1);
   	
   	public static double PID_SETPOINT = 10.0; //10 feet
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
       
    	leftEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
    	rightEncoder = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
    	
    	leftEncoder.setMaxPeriod(.1);
    	leftEncoder.setMinRate(10);
    	leftEncoder.setDistancePerPulse(DIST_PER_PULSE);
    	leftEncoder.setReverseDirection(false);
    	leftEncoder.setSamplesToAverage(7);
    	rightEncoder.setMaxPeriod(.1);
    	rightEncoder.setMinRate(10);
    	rightEncoder.setDistancePerPulse(DIST_PER_PULSE);
    	rightEncoder.setReverseDirection(true);
    	rightEncoder.setSamplesToAverage(7);
    	
    	linearDriveLeft.setAbsoluteTolerance(.5);
    	linearDriveRight.setAbsoluteTolerance(.5);
    	linearDriveLeft.setOutputRange(-1, 1);
    	linearDriveLeft.setSetpoint(PID_SETPOINT);
    	linearDriveRight.setOutputRange(-1, 1);
    	linearDriveRight.setSetpoint(PID_SETPOINT);
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
    	linearDriveLeft.enable();
    	linearDriveRight.enable();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//setting other drive motors to PID outputs
    	leftDrive2.set(linearDriveLeft.get());
    	rightDrive2.set(linearDriveRight.get());
    	leftDrive3.set(linearDriveLeft.get());
    	rightDrive3.set(linearDriveRight.get());
    	if(linearDriveRight.onTarget())
    		linearDriveRight.disable();
    	if(linearDriveLeft.onTarget())
    		linearDriveLeft.disable();
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