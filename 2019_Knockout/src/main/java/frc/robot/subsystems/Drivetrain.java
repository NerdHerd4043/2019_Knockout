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
// import frc.robot.Robot;
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

  double initInputSpeed;
  double initInputStrafe;
  double sn;
  double cs;
  double radians;


public Drivetrain(){
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
  if (!Robot.fieldOriented.getBoolean(false)){
    inputSpeed = joy.getRawAxis(1);
    inputStrafe = joy.getRawAxis(0);

    inputTurn = -joy.getRawAxis(4);
  } else {
    initInputSpeed = joy.getRawAxis(1);
    initInputStrafe = joy.getRawAxis(0);
    
    radians = Math.toRadians(Robot.ahrs.getAngle());
    cs = Math.cos(radians);
    sn = Math.sin(radians);

    inputSpeed = initInputSpeed * cs - initInputStrafe * sn;
    inputStrafe = initInputSpeed * sn + initInputStrafe * cs;

    inputTurn = -joy.getRawAxis(4);
  }

  drive(inputSpeed, inputTurn, inputStrafe);
}
  
public void drive(double speed, double turn) {
  diffDrive.arcadeDrive(speed, turn, true);
}

public void drive(double speed, double turn, double strafe) {
    diffDrive.arcadeDrive(speed, turn, true);
    strafe(inputStrafe);
}

public void strafe(double inputStrafe) {
  // System.out.println(inputStrafe);
  if(Math.abs(inputStrafe) > 0.2) {
    extendWheel();
  } else {
    retractWheel();
  }

  if(-inputStrafe < 0) {
    RobotMap.motorMR.set(-(inputStrafe * inputStrafe));
  } else {
    RobotMap.motorMR.set(inputStrafe * inputStrafe);
  }
  
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
