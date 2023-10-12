// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


  public class SwoopAuto extends CommandBase {
  private Drivetrain m_drivetrain;
  private double leftVelocity;
  private double rightVelocity;
  private double totalRunTime;
  private double startTime;

  int counter;
  /** Creates a new TeleopDriveCommand. */
  public SwoopAuto(Drivetrain drivetrain, double leftVelocity, double rightVelocity, double totalRunTime) {
    // Initializes drivetrain and OI (they're set to the inputs in the parameters)
    m_drivetrain = drivetrain;
    this.leftVelocity = leftVelocity;
    this.rightVelocity = rightVelocity;
    this.totalRunTime = totalRunTime;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain); //This makes sure that two commands can't try to move the robot in two different directions at once!
  }

  /* Called when the command is initially scheduled. */
  @Override
  public void initialize() {
    counter = 0;
  }

  /* Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() 
  {
    if (counter == 0){
      startTime = Timer.getFPGATimestamp();
      counter ++;
    }
    
    // Uses the setPower method from the Drivetrain subsystem
    // Uses percent input from the joysticks which sets the speed [between -1 to 1] to the motors
    m_drivetrain.setPower(leftVelocity, rightVelocity);
    SmartDashboard.putNumber("Run Time", Timer.getFPGATimestamp());
    SmartDashboard.putNumber("Left Velocity", leftVelocity);
    SmartDashboard.putNumber("Right Velocity", rightVelocity);
    SmartDashboard.putNumber("Total Time", totalRunTime);
  }

  /* Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    // Sets motor speeds to 0, so it stops when the command ends
   m_drivetrain.setPower(0, 0);
  }

  /* Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() - startTime >= totalRunTime) {
    return true;
    } else { 
    return false;
    }
  
  }}
