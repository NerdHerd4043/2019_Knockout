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

  Button navxresetButton = new JoystickButton(driveStick, 8);

  Button AntiShiftBtn = new JoystickButton(driveStick, 3);    // X maybe?
  Button ShiftBtn = new JoystickButton(driveStick, 1);        // A maybe?

  POVButton ExtendWheelBtn = new POVButton(driveStick, 270);  // DPAD left
  POVButton RetractWheelBtn = new POVButton(driveStick, 90);  // DPAD RIght

  Button YoinkBtn = new JoystickButton(driveStick, 6);        // Right bumper toggle
  Button YeetBtn = new JoystickButton(driveStick, 5);         // Left bumber toggle

  public OI() {
    navxresetButton.whenPressed(new ResetNavx());
    ShiftBtn.whenPressed(new Shift());
    AntiShiftBtn.whenPressed(new AntiShift());
    ExtendWheelBtn.whenPressed(new ExtendWheel());
    RetractWheelBtn.whenPressed(new RetractWheel());
    YeetBtn.toggleWhenPressed(new TriggerYeet());
    YoinkBtn.toggleWhenPressed(new TriggerYoink());
  }

  public Joystick getDrivestick() {
    return driveStick;
  }
}
