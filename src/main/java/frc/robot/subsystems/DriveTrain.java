/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


//TankDrive with two traditional joysticks 
public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private final TalonSRX left = new TalonSRX(2);
  private final TalonSRX right = new TalonSRX(3);
  private static DriveTrain drive;
  private Joystick leftJoy;
  private Joystick rightJoy;
  
  public DriveTrain() {
    //bug 1
    //missing instantiation of left and rightJoy.
    //this is important because they are right now just variables that can not be used
  }
  //grabs instance of DriveTrain to be used in commands 
  public static DriveTrain getDrive() {
    if (drive == null) {
      drive = new DriveTrain();
    }
    return drive;
  }
  public void tankDrive(double l, double r) {
    left.set(ControlMode.PercentOutput, -l); 
    //bug 2
    //by setting the power to -l, you essentially inverted the wheels
    //this can be both beneficial and problematic as the chassis might not move in a stright line
    right.set(ControlMode.PercentOutput, r);
  }
  @Override
  public void periodic() {
    tankDrive(leftJoy.getY(), rightJoy.getY());
    // This method will be called once per scheduler run
  }
}