// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Auto2 extends CommandBase {
  private Drivetrain m_drivetrain;
  int counter;
  public Auto2(Drivetrain drivetrain) {
     // Initializes drivetrain and OI (they're set to the inputs in the parameters)
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
    m_drivetrain.setPower(.9, .9);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setPower(0, 0);
  }

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
