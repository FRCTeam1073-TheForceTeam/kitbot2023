// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase 
{
  TalonFX topMotor = new TalonFX(0); // TODO: set device id
  TalonFX bottomMotor = new TalonFX(0); // same thing
  private double speed;
  private double offset;

  /** Creates a new Shooter. */
  public Shooter() 
  {
    speed = 0;
    offset = 0;
  }

  public void setTopMotorSpeed(double speed)
  {
    topMotor.setControl(new VelocityVoltage(speed));
  }

  public void setBottomMotorSpeed(double speed)
  {
    bottomMotor.setControl(new VelocityVoltage(speed));
  }

  public void setMotorSpeeds(double speed, double offset)
  {
    setTopMotorSpeed(speed);
    setBottomMotorSpeed(speed - offset);
  }

  public void setSpeed(double speed)
  {
    this.speed = speed;
  }

  public void setOffset(double offset)
  {
    this.offset = offset;
  }
  
  public double getSpeed()
  {
    return speed;
  }

  public double getOffset()
  {
    return offset;
  }

  @Override
  public void initSendable(SendableBuilder builder)
  {
    builder.setSmartDashboardType("Shooter");
    builder.addDoubleProperty("Speed", this::getSpeed, this::setSpeed);
    builder.addDoubleProperty("Offset", this::getOffset, this::setOffset);
  }

  @Override
  public void periodic() 
  {
    setMotorSpeeds(speed, offset);
  }
}
