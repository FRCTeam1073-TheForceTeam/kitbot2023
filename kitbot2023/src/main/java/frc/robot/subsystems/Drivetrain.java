// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/*to get import for Rev hardware use this json file: 
https://software-metadata.revrobotics.com/REVLib-2023.json
WPI button -> manage vendor libraries -> install new library (online) */

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Drivetrain extends SubsystemBase {
  
  NetworkTable drivetrainTable;
  NetworkTableEntry pEntry;
  NetworkTableEntry iEntry;
  NetworkTableEntry dEntry;
  NetworkTableEntry fEntry;
  NetworkTableEntry updateButton;
 
  private Spark leftMotorLeader;
  private Spark leftMotorFollower;
  private Spark rightMotorLeader;
  private Spark rightMotorFollower;
  
  private double kP = 0.15 * 0.75;
  private double kI = 0.002 * 0;
  private double kD = 0.0;
  private double kF = 0.052;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    drivetrainTable = NetworkTableInstance.getDefault().getTable("Drivetrain");
    pEntry = drivetrainTable.getEntry("kP");
    iEntry = drivetrainTable.getEntry("kI");
    dEntry = drivetrainTable.getEntry("kD");
    fEntry = drivetrainTable.getEntry("kF");

    updateButton = drivetrainTable.getEntry("update constants");

    updateButton.setBoolean(true);
    pEntry.setDouble(kP);
    iEntry.setDouble(kI);
    dEntry.setDouble(kD);
    fEntry.setDouble(kF);


    leftMotorLeader = new Spark(4);
    leftMotorFollower = new Spark(1);
    rightMotorLeader = new Spark(2);
    rightMotorFollower = new Spark(3);
    // leftMotorFollower.follow(leftMotorLeader);
    // rightMotorFollower.follow(rightMotorLeader);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  public void setPower(double leftPower, double rightPower)
  {
    leftMotorLeader.set(leftPower);
    leftMotorFollower.set(leftPower);
    rightMotorLeader.set(rightPower);
    rightMotorFollower.set(rightPower);
    SmartDashboard.putNumber("Right Power", rightPower);
    SmartDashboard.putNumber("Left Power", leftPower);
  }

  // public void drive(double left, double right){
  //   drivetrain.tankDrive(left, right);
  // }
}