// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OI;

public class TeleopDriveCommand extends CommandBase {
  private Drivetrain m_drivetrain;
  private OI m_OI;

  /** Creates a new TeleopDriveCommand. */
  public TeleopDriveCommand(Drivetrain drivetrain, OI m_oi) {

    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_OI = m_oi;
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(m_OI.getDriverLeftY(), m_OI.getDriverRightY());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
