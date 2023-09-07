// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/* To get import for Rev hardware use this json file: 
https://software-metadata.revrobotics.com/REVLib-2023.json
WPI button -> manage vendor libraries -> install new library (online) */

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  // Declares the motors' variables
  private Spark leftMotorLeader;
  private Spark leftMotorFollower;
  private Spark rightMotorLeader;
  private Spark rightMotorFollower;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Initializes the motors to Spark motor objects with pwm channels corresponding to where they're plugged in on the robot
    rightMotorLeader = new Spark(0);
    rightMotorFollower = new Spark(1);
    leftMotorLeader = new Spark(2);
    leftMotorFollower = new Spark(3);
  }

  /* Called once per scheduler run */
  @Override
  public void periodic() {
    // We're not doing anything here so that it doesn't conflict with our commands!
  }

  /* Sets the motors to given speeds for the left and right sides of the robot */
  public void setPower(double leftPower, double rightPower)
  {
    // The leader and follower *must* be set to the same value because they're physically connected on the robot
    // (We do not want to tear the robot apart by making them go in different directions)
    leftMotorLeader.set(-leftPower*.5); // Because the motors are facing in different directions, we reverse the input on one side
    leftMotorFollower.set(-leftPower*.5); // We're also setting them to half speed (comp robots should go fast but test robots don't need to)
    rightMotorLeader.set(rightPower*.5);
    rightMotorFollower.set(rightPower*5);
    SmartDashboard.putNumber("Right Power", rightPower); // Prints the values to shuffleboard for debugging
    SmartDashboard.putNumber("Left Power", leftPower);
  }
}