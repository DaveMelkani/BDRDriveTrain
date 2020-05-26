/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import the ctre.phoenix for TalonSRX to allow creation 
//of the left and right TalonSRX variables
import edu.wpi.first.wpilibj.Joystick;
//import the wpi.first for Joystick to allow creation 
//of the leftJoy and rightJoy Joystick variables
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//TankDrive with two traditional joysticks  
public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private final TalonSRX left;
  private final TalonSRX right;
  //creating private TalonSRX variables for both left and right
  private static DriveTrain drive;
  //using the DriveTrain class to create an object called drive
  private Joystick leftJoy; 
  private Joystick rightJoy;
  //creating private Joystick variables for both leftJoy and rightJoy
  
  public DriveTrain() {
    left = new TalonSRX(2);
    right = new TalonSRX(3);
    //instantiating (constructing) the left and right TalonSRX variables
    //for the assigned motors-> left is TalonSRX(2) and right is TalonSRX(3)
    leftJoy = new Joystick(2);
    rightJoy = new Joystick(3);
    //instantiating (constructing) the leftJoy and rightJoy Joystick variables
    //for the assigned joystick-> leftJoy is joystick # 2 & rightJoy is joystick # 3
  }
  //grabs instance of DriveTrain to be used in commands 
  public static DriveTrain getDrive() {
    if (drive == null) {
      drive = new DriveTrain();
    //instantiating (constructing) the drive variables in DriveTrain
    }
    //if statement put there inorder to not get a null pointer exception
    return drive;
    //getter method returns the drive variable
  }

  public void tankDrive(double lVal, double rVal) {
    //To set a certain power:
    left.set(ControlMode.PercentOutput, lVal); 
    //lVal is of type double (power output for left wheel)
    right.set(ControlMode.PercentOutput, rVal);
    //rVal is of type double (power output for right wheel)

    //To invert a motor:
    left.setInverted(false);
    right.setInverted(true);
    //You can also add a negative to the power which is easier
    //ex: left.set(ControlMode.PercentOutput, lVal); 
    //    right.set(ControlMode.PercentOutput, -rVal); 
  }

  @Override
  public void periodic() {
    tankDrive(leftJoy.getY(), rightJoy.getY());
    //tankDrive method calls for leftJoy to move in the y direction 
    //and rightJoy to also move in the y direction
  }
  // This method will be called once per scheduler run
}