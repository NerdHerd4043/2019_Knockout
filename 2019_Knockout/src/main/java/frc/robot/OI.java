/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Joystick driveStick = new Joystick(0);

  POVButton AntiShiftBtn = new POVButton(driveStick, 180);
  POVButton ShiftBtn = new POVButton(driveStick, 0);

  POVButton ExtendWheelBtn = new POVButton(driveStick, 90);
  POVButton RetractWheelBtn = new POVButton(driveStick, 270);

  Button ThumperResetBtn = new JoystickButton(driveStick, 2);
  Button YoinkBtn  = new JoystickButton(driveStick, 6);
  Button YeetBtn = new JoystickButton(driveStick, 5);

  
  public OI() {
    ShiftBtn.whenPressed(new Shift());
    AntiShiftBtn.whenPressed(new AntiShift());
    ExtendWheelBtn.whenPressed(new ExtendWheel());
    RetractWheelBtn.whenPressed(new RetractWheel());
    // YeetBtn.whenPressed(new TriggerYeet());
    // YoinkBtn.whenPressed(new TriggerYoink());
    
  }


  public Joystick getDrivestick(){
    return driveStick;
  }
}
