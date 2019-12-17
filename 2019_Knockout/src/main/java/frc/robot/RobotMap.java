/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {
    public static WPI_TalonSRX motorRF = new WPI_TalonSRX(6);
    public static WPI_TalonSRX motorRB = new WPI_TalonSRX(5);
    public static WPI_TalonSRX motorLF = new WPI_TalonSRX(2);
    public static WPI_TalonSRX motorLB = new WPI_TalonSRX(1);
    public static WPI_TalonSRX motorMR = new WPI_TalonSRX(7);
    public static WPI_TalonSRX motorML = new WPI_TalonSRX(8);

    public static WPI_TalonSRX motorYoink = new WPI_TalonSRX(100);
    public static WPI_TalonSRX motorFmwiabR = new WPI_TalonSRX(4);
    public static WPI_TalonSRX motorFmwiabL = new WPI_TalonSRX(3);
                             //4 men walk into a bar

    public static Solenoid wheelPusher = new Solenoid(1, 1);
    public static Solenoid shifter = new Solenoid(1, 0);
}
