// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
/*to get import for Rev hardware use this json file: 
https://software-metadata.revrobotics.com/REVLib-2023.json
WPI button -> manage vendor libraries -> install new library (online) */
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Drivetrain extends SubsystemBase {
  
  NetworkTable drivetrainTable;
  NetworkTableEntry pEntry;
  NetworkTableEntry iEntry;
  NetworkTableEntry dEntry;
  NetworkTableEntry fEntry;
  NetworkTableEntry updateButton;
 
  private CANSparkMax leftMotorLeader;
  private CANSparkMax leftMotorFollower;
  private CANSparkMax rightMotorLeader;
  private CANSparkMax rightMotorFollower;

  private DifferentialDriveOdometry odometry;
  private DifferentialDriveKinematics kinematics;
  private DifferentialDriveWheelSpeeds wheelSpeeds;
  private DifferentialDrive drivetrain;
  
  
  private final boolean isPowerMode = false;
  
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

    leftMotorLeader = new CANSparkMax(30, MotorType.kBrushed);
    leftMotorFollower = new CANSparkMax(50, MotorType.kBrushed);
    rightMotorLeader = new CANSparkMax(31, MotorType.kBrushed);
    rightMotorFollower = new CANSparkMax(27, MotorType.kBrushed);
    leftMotorLeader.restoreFactoryDefaults();
    rightMotorLeader.restoreFactoryDefaults();
    leftMotorFollower.follow(leftMotorLeader);
    rightMotorFollower.follow(rightMotorLeader);

    drivetrain = new DifferentialDrive(leftMotorLeader, rightMotorLeader);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  public void setPower(double leftPower, double rightPower)
  {
    if(!isPowerMode){
      leftMotorLeader.set(leftPower);
      rightMotorLeader.set(rightPower);
    }
  }

  public void drive(double x, double y){
    drivetrain.tankDrive(x, y);
  }
}

