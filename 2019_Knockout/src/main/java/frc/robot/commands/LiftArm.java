/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftArm extends Command {
  public LiftArm() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.fmwiab);
  }

  double startTime;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = 0;
    startTime = Timer.getFPGATimestamp();
    System.out.println(startTime);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.fmwiab.setFmwiabAngle(-1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println(Timer.getFPGATimestamp());
    return Timer.getFPGATimestamp() > (startTime + 1.5);

  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
