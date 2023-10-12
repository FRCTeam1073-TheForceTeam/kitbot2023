// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveCommand extends CommandBase {
  private Drivetrain m_drivetrain;
  private int counter;

  /** Creates a new TeleopDriveCommand. */
  public AutoDriveCommand(Drivetrain drivetrain) {
    // Initializes drivetrain and OI (they're set to the inputs in the parameters)
    m_drivetrain = drivetrain;

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
  public void execute() {
    // Uses the setPower method from the Drivetrain subsystem
    // Uses percent input from the joysticks which sets the speed [between -1 to 1] to the motors
    
    m_drivetrain.setPower(0.2, 0.5);
    counter++;   
    SmartDashboard.putNumber("Counter", counter);
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
    if (counter >= 100){
        return true;
    }
    return false;
  }
}
