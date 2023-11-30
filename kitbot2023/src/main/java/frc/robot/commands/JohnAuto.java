// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class JohnAuto extends CommandBase {
  int counter;
  Drivetrain m_drivetrain;
  double leftPower;
  double rightPower;

  /** Creates a new JohnAuto. */
  public JohnAuto(Drivetrain drivetrain, double left, double right) {
    // Use addRequirements(m_drivetrain); here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    leftPower = left;
    rightPower = right;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter = counter + 1;
    // Uses set power method from the Drivetrain subsystem
    // Uses percent input from the joysticks which sets the speed [between -1 to 1] to the motors
    m_drivetrain.setPower( leftPower,rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  // Sets motor speeds to 0, so it stops when the command ends
  m_drivetrain.setPower(0, 0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   if (counter >= 100) 
   {
    return true;
   }
    {
    return false;
  }
  } 
}