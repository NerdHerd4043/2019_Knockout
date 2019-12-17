/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DifferentialDrive diffDrive;
  double inputSpeed;
  double inputTurn;
  double inputStrafe;

  public Drivetrain() {
    super();
    diffDrive = new DifferentialDrive(RobotMap.motorLF, RobotMap.motorRF);

    RobotMap.motorLB.follow(RobotMap.motorLF);
    RobotMap.motorRB.follow(RobotMap.motorRF);
    RobotMap.motorML.follow(RobotMap.motorMR);

    RobotMap.motorLB.setSafetyEnabled(false);
    RobotMap.motorLF.setSafetyEnabled(false);
    RobotMap.motorRB.setSafetyEnabled(false);
    RobotMap.motorRF.setSafetyEnabled(false);
    RobotMap.motorMR.setSafetyEnabled(false);
    RobotMap.motorML.setSafetyEnabled(false);
  }

  public void drive(Joystick joy) {
    if (Robot.arcadeDrive.getBoolean(true)) {
      inputSpeed = -joy.getRawAxis(1);
      inputTurn = joy.getRawAxis(4);
    } else {
      inputSpeed = -joy.getRawAxis(1);
      inputTurn = joy.getRawAxis(5);
    }
    drive(inputSpeed, inputTurn);
    inputStrafe = joy.getRawAxis(0);
    strafe(inputStrafe);
  }

  public void drive(double speed, double turn) {
    if (Robot.arcadeDrive.getBoolean(true)) {
      diffDrive.arcadeDrive(speed, turn, true);
    } else {
      diffDrive.tankDrive(speed, turn, true);
    }
  }

  public void strafe(double inputStrafe) {
    RobotMap.motorMR.set(inputStrafe);
  }

  public void shift() {
    RobotMap.shifter.set(true);
  }

  public void antiShift() {
    RobotMap.shifter.set(false);
  }

  public void extendWheel() {
    RobotMap.wheelPusher.set(true);
  }

  public void retractWheel() {
    RobotMap.wheelPusher.set(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }
}
