/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Yoinker extends Subsystem {
  public void yeet() {
    RobotMap.motorYoink.set(1);
  }

  public void yoink() {
    RobotMap.motorYoink.set(-.75);
  }

  public void yoink(double speed) {
    RobotMap.motorYoink.set(speed);
  }

  public void maintainYoink() {
    RobotMap.motorYoink.set(0.1);

  }

  public void stopYeet() {
    RobotMap.motorYoink.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
}
