// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArjunDrivesCommand;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.Bling;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OI;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are declared and initialized here.
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final OI m_OI = new OI();
  private final Bling m_bling = new Bling();
  private final TeleopDriveCommand teleopDriveCommand = new TeleopDriveCommand(m_drivetrain, m_OI, m_bling);
  private final AutoDriveCommand autoDriveCommand = new AutoDriveCommand(m_drivetrain);
  private final ArjunDrivesCommand arjunDrivesCommand = new ArjunDrivesCommand(m_drivetrain);
  // Add new subsystems and commands here, then set their dependencies and add triggers/return commands in autonomous when applicable

  /** Creates the container for the robot, which contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Sets default commands for each subsystem when they exist
    m_drivetrain.setDefaultCommand(teleopDriveCommand);
    configureBindings();
  }

  /** Assigns commands to specific triggers */
  private void configureBindings() {
    // To learn more about trigger bindings, visit https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html#trigger-bindings
  }
  
  /** Returns the command to run in autonomous */
  public Command getAutonomousCommand() {
    // return Autos.exampleAuto(m_exampleSubsystem);
    return parCommand();
  }

  public Command seqCommand() {
    return new SequentialCommandGroup(
      new ArjunDrivesCommand(m_drivetrain),
      new ArjunDrivesCommand(m_drivetrain),
      new ArjunDrivesCommand(m_drivetrain)
    );
  }
 public Command parCommand() {
  return new ParallelCommandGroup(new ArjunDrivesCommand(m_drivetrain), new WaitCommand(7.5)); 
 }


  public void diagnostics() {
  }
}