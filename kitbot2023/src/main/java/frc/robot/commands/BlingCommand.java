// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bling;
import frc.robot.subsystems.OI;

public class BlingCommand extends CommandBase {
  Bling bling;
  OI oi;

  /** Creates a new BlingSetCommand. */
  public BlingCommand(Bling bling_, OI oi_) {
    // Use addRequirements() here to declare subsystem dependencies.
    bling = bling_;
    oi = oi_;
    addRequirements(bling);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // for(int i = 0; i < 8; i++){
    //   bling.setRGB(i, 177, 204, 157);
    // }
    
  }
 /*  Red: 15, 0, 0
  * Orange: 24, 10, 0
  * Yellow: 20, 20, 1
  * Green: 0, 15, 15
  * Blue: 0, 0, 5
  * Purple: 9, 2, 6*/
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //bling.setRGBAll(25, 25, 25);
    //SET COLOR BASED ON BUTTON
    int trigger = (int)(oi.getDriverLeftTrigger());

    if(oi.getDriverRawButton(1)){
      for(int i = 0; i < 8; i++){
        bling.m_LedBuffer.setHSV(i, 100, 100, trigger);
      }
    }
     else if(oi.getDriverRawButton(2)){
       bling.setRGBAll(24, 10, 0);
     }
     else if(oi.getDriverRawButton(3)){
       bling.setRGBAll(20, 20, 1);
     }
     else if(oi.getDriverRawButton(4)){
       bling.setRGBAll(0, 15, 0);
     }
     else if(oi.getDriverRawButton(5)){
       bling.setRGBAll(0, 0, 5);
     }
     else if(oi.getDriverRawButton(6)){
       bling.setRGBAll(9, 2, 6);
    }
  }

  // private void setRainbowALL() {
  // }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    bling.setRGBAll(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}