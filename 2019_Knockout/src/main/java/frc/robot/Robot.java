/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableEntry;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Drivetrain drivetrain;
  public static Yoinker yoinker;
  public static Fmwiab fmwiab;

  public static AHRS ahrs;

  private static final double cpr = 360; //if am-3132
  private static final double whd = 6; // for 6 inch wheel

  public static Command m_autonomousCommand;

  public static NetworkTableEntry fieldOriented;

//always add last
  public static OI m_oi;  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    yoinker = new Yoinker();
    fmwiab = new Fmwiab();

    ahrs = new AHRS(SPI.Port.kMXP); 


    RobotMap.motorRB.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 10);
    RobotMap.motorLB.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 10);
    RobotMap.motorRB.setSelectedSensorPosition(0, 0, 10);
		RobotMap.motorLB.setSelectedSensorPosition(0, 0, 10);
    // rightEncoder = new Encoder(0, 1);
    // rightEncoder.setDistancePerPulse(Math.PI*whd/cpr); //distance per pulse is pi* (wheel diameter / counts per revolution)

    ShuffleboardTab shuffTab = Shuffleboard.getTab("Drive");

    fieldOriented = shuffTab
      .add("Field Oriented", false) 
      .withWidget(BuiltInWidgets.kToggleButton)
      .withPosition(2, 0)
      .withSize(1, 3)
      .getEntry();
    
    shuffTab
      .add("Gyro", ahrs)
      .withWidget(BuiltInWidgets.kGyro)
      .withPosition(0, 0)
      .withSize(2, 3);

    m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // navxAngle.setDouble(ahrs.getAngle());
    // System.out.println(m_oi.getDrivestick().getRawButton(5));
    // System.out.println(m_oi.getDrivestick().getPOV());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    // Robot.drivetrain.retractWheel();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, rem ove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = new Auto();
    
    System.out.println("auto");
    if (m_autonomousCommand != null) {
      System.out.println("auto again");
			m_autonomousCommand.start();
		}
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Robot.yoinker.stopYeet();
    Scheduler.getInstance().run();
  }

  @Override 
  public void teleopInit() {
    if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
    // Robot.drivetrain.extendWheel();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    // System.out.println(ahrs.getAngle());
    System.out.print("R: " + RobotMap.motorRB.getSelectedSensorPosition(0) + "\t");
    System.out.println("L: " + RobotMap.motorLB.getSelectedSensorPosition(0) + "\t");

  }
}
