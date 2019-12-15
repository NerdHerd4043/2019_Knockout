/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class Yoinker extends Subsystem {
  private boolean yeetControl = false;
  private boolean yoinkControl = false;
 
  public void toggleYeet(){
    yeetControl = !yeetControl;
  }

  public void toggleYoink(){
    yoinkControl = !yoinkControl;

  }

  
  public void setYoinkerAngle() {
    RobotMap.motorFmwiabL.follow(RobotMap.motorFmwiabR);
    double rightTrigger = Robot.m_oi.getDrivestick().getRawAxis(3);
    double leftTrigger = Robot.m_oi.getDrivestick().getRawAxis(2);

    RobotMap.motorFmwiabR.set(leftTrigger - rightTrigger);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
   // setDefaultCommand;
  }
}
