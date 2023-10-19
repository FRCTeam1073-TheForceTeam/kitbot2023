// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Saka extends CommandBase {
  private Drivetrain m_drivetrain;
  private final Timer m_timer = new Timer();

  int counter;
  /** Creates a new Saka. */
  public Saka(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain); //This makes sure that two commands can't try to move the robot in two different directions at once!
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter++;
    // Uses the setPower method from the Drivetrain subsystem
    // Uses percent input from the joysticks which sets the speed [between -1 to 1] to the motors
    if (m_timer.get() < 3.0) {
      m_drivetrain.setPower(0.6, 0.6);
    }
    
    //m_drivetrain.setPower(leftPower:0.6, rightPower:0.6);

  SmartDashboard.putNumber("Counter", counter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (counter >= 250) {
      return true;
      } else { 
      return false;
      }
  }
}
