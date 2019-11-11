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

public Drivetrain(){
super();
diffDrive = new DifferentialDrive(RobotMap.motorFL, RobotMap.motorFR);

RobotMap.motorBL.follow(RobotMap.motorFL);
RobotMap.motorBR.follow(RobotMap.motorFR);

RobotMap.motorBL.setSafetyEnabled(false);
    RobotMap.motorFL.setSafetyEnabled(false);
    RobotMap.motorBR.setSafetyEnabled(false);
    RobotMap.motorFR.setSafetyEnabled(false);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
