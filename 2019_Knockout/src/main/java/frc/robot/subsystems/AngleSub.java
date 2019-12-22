/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class AngleSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double turnToAngle(double wantedAngle){ //Takes in a wanted angle and returns the turnSpeed to get there
		double currentAngle = Robot.ahrs.getAngle(); //In order to determine where we are, take in the current gyro value from the navx
		double rotateSpeed;
		
		if (currentAngle > wantedAngle + 2) { 					//If we are too far to the right of where we want to be...
			rotateSpeed = -.7d;	//turn left (negative number)
		} else if (currentAngle < wantedAngle - 2) {			//Otherwise, if we are too far left ...
			rotateSpeed = .7d;	//turn right (positive number)
		} else {												//If we are right on track ...
			rotateSpeed = 0d;									//don't rotate
		}
		
		return rotateSpeed;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
