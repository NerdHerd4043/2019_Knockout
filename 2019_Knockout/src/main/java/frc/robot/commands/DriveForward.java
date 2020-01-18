/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveForward extends Command {
  public DriveForward() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);

  }

  double startTime;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("ResettingEncs");
    RobotMap.motorRB.setSelectedSensorPosition(0, 0, 10);
    RobotMap.motorLB.setSelectedSensorPosition(0, 0, 10);
    Robot.ahrs.reset();
    // startTime = 0;
    // startTime = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("auto: " + -RobotMap.motorRB.getSelectedSensorPosition() + "\t" + "turn speed: " + Robot.turnSpeed);
    Robot.drivetrain.antiShift();
    Robot.drivetrain.drive(-0.9, -turnToAngle(0));
    Robot.yoinker.yoink(-0.8);
    Robot.fmwiab.setFmwiabAngle(0.2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !(RobotMap.motorRB.getSelectedSensorPosition() > -19500);
    // return Timer.getFPGATimestamp() > (startTime + 5);
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

  public double turnToAngle(double wantedAngle){ //Takes in a wanted angle and returns the turnSpeed to get there
		double currentAngle = Robot.ahrs.getAngle(); //In order to determine where we are, take in the current gyro value from the navx
		double rotateSpeed;
		
		if (currentAngle > wantedAngle + 1) { 				//If we are too far to the right of where we want to be...
			rotateSpeed = -.5d;													//turn left (negative number)
		} else if (currentAngle < wantedAngle - 1) {	//Otherwise, if we are too far left ...
			rotateSpeed = .5d;													//turn right (positive number)
		} else {																			//If we are right on track ...
			rotateSpeed = 0d;														//don't rotate
		}
		
		return rotateSpeed;
	}
}
