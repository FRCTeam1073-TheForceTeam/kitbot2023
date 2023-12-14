// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bling;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OI;


public class TeleopDriveCommand extends CommandBase {
  private Drivetrain m_drivetrain;
  private OI m_OI;
  private Bling m_bling;
  int ledR = 0;
  int ledG = 0;
  int ledB = 0;
  /** Creates a new TeleopDriveCommand. */
  public TeleopDriveCommand(Drivetrain drivetrain, OI m_oi, Bling m_bling) {
    // Initializes drivetrain and OI (they're set to the inputs in the parameters)
    m_drivetrain = drivetrain;
    this.m_OI = m_oi;
    this.m_bling = m_bling;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain); //This makes sure that two commands can't try to move the robot in two different directions at once!
  }

  /* Called when the command is initially scheduled. */
  @Override
  public void initialize() {}

  /* Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    // Uses the setPower method from the Drivetrain subsystem
    // Uses percent input from the joysticks which sets the speed [between -1 to 1] to the motors
    m_drivetrain.setPower(m_OI.getDriverLeftY(), m_OI.getDriverRightY());
    if(OI.driverController.getRawButtonPressed(1)) {
      System.out.println("help");
      m_bling.setRGBAll(0, 30, 0);
    }
    if(OI.driverController.getRawButtonPressed(2)) {
      System.out.println("green");
      m_bling.setRGBAll(50, 0, 0);
    }
    if(OI.driverController.getRawButtonPressed(3)) {
      System.out.println("cry");
      m_bling.setRGBAll(0, 0, 25);
    
    }
    if(OI.driverController.getRawButtonPressed(4)) {
      m_bling.setRGBAll(0, 0, 0);
    }

    m_bling.setRGBAll(ledR, ledG, ledB);
  }

  /* Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    // Sets motor speeds to 0, so it stops when the command ends
    m_drivetrain.setPower(0, 0);
  //  m_bling.setRGBAll(0, 0, 0);
  }

  /* Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}
