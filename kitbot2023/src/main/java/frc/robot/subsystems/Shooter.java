// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase 
{
  TalonFX topMotor;
  TalonFX bottomMotor;
  private double speed;
  private double offset;

  TalonFXConfiguration configs = new TalonFXConfiguration();

  private double p = 0.11;
  private double i = 0.5;
  private double d = 0.0001;

  /** Creates a new Shooter. */
  public Shooter() 
  {
    topMotor = new TalonFX(16);
    bottomMotor = new TalonFX(18);

    speed = 0;
    offset = 0;
    setConfigs();
  }

  public void setConfigs(){
    configs.Slot0.kP = p;
    configs.Slot0.kI = i;
    configs.Slot0.kD = d;
    configs.Slot0.kV = 0.12;
    configs.Voltage.PeakForwardVoltage = 8;
    configs.Voltage.PeakReverseVoltage = -8;

    configs.Slot1.kP = 5;
    configs.Slot1.kI = 0.1;
    configs.Slot1.kD = 0.001;

    configs.TorqueCurrent.PeakForwardTorqueCurrent = 40;
    configs.TorqueCurrent.PeakReverseTorqueCurrent = -40;

    topMotor.getConfigurator().apply(configs);
    topMotor.setNeutralMode(NeutralModeValue.Coast);
    bottomMotor.getConfigurator().apply(configs);
    bottomMotor.setNeutralMode(NeutralModeValue.Coast);
  }

  public void setTopMotorSpeed(double speed)
  {
    topMotor.setControl(new VelocityVoltage(speed));
    // topMotor.set(speed);
    // topMotor.setControl(new VelocityVoltage(speed).withSlot(0).withFeedForward(0));
  }

  public void setBottomMotorSpeed(double speed)
  {
    bottomMotor.setControl(new VelocityVoltage(speed));
    // VelocityVoltage velvolt = new VelocityVoltage(speed);
    // velvolt = velvolt.withAcceleration(1.0);
    // bottomMotor.setControl(velvolt);
  }

  public void setMotorSpeeds(double speed, double offset)
  {
    setTopMotorSpeed(-speed);
    setBottomMotorSpeed(speed + offset);
  }

  public void setSpeed(double speed)
  {
    this.speed = speed;
  }

  public void setOffset(double offset)
  {
    this.offset = offset;
  }
  
  public double getTopSpeed()
  {
    return topMotor.getRotorVelocity().getValueAsDouble();
  }

  public double getBottomSpeed()
  {
    return bottomMotor.getRotorVelocity().getValueAsDouble();
  }

  public double getSpeed()
  {
    return speed;
  }

  public double getOffset()
  {
    return offset;
  }

  public double getP(){
    return p;
  }

  public void setP(double p){
    this.p = p;
  }

  public double getI(){
    return i;
  }

  public void setI(double i){
    this.i = i;
  }

  public double getD(){
    return d;
  }

  public void setD(double d){
    this.d = d;
  }

  @Override
  public void initSendable(SendableBuilder builder)
  {
    builder.setSmartDashboardType("Shooter");
    builder.addDoubleProperty("Speed", this::getSpeed, this::setSpeed);
    builder.addDoubleProperty("Top Speed", this::getTopSpeed, null);
    builder.addDoubleProperty("Bottom Speed", this::getBottomSpeed, null);
    builder.addDoubleProperty("Offset", this::getOffset, this::setOffset);
    builder.addDoubleProperty("p", this::getP, this::setP);
    builder.addDoubleProperty("i", this::getI, this::setI);
    builder.addDoubleProperty("d", this::getD, this::setD);
  }

  @Override
  public void periodic() 
  {
    setMotorSpeeds(-speed, offset);
  }
}
