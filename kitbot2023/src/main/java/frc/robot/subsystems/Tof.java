// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.ctre.phoenix6.signals.ControlModeValue;
// import com.ctre.phoenix6.controls.PositionVoltage;

import edu.wpi.first.wpilibj.DigitalInput; 
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DutyCycle;

/** Add your docs here. */
public class Tof extends SubsystemBase {
    private final DigitalInput tof1;
    private final DutyCycle dutyCycle;
    private double tof1DutyCycle;
    private double tof1Freq;
    private double tof1Range;
    private double tof1RangeInches;
    private final double tof1ScaleFactor = 3000000/4;// for 50cm (irs16a): 3/4 million || for 130cm (irs17a): 2 million || for 300cm (irs17a): 4 miillion;

public Tof() {
    tof1 = new DigitalInput(0);
    dutyCycle = new DutyCycle(tof1);

    tof1Freq = 0;
    tof1Range = 0;
}

public double getRange1(){
    return tof1Range;
}

public double getRange1Inches(){
    return tof1RangeInches;
}

public double getFreq1(){
    return tof1Freq;
}

public void periodic() {
    System.out.println("TOF running");
    tof1Freq = dutyCycle.getFrequency();
    System.out.println(tof1Freq);
    tof1DutyCycle = dutyCycle.getOutput();
    tof1Range = (tof1ScaleFactor * (tof1DutyCycle / tof1Freq - 0.001)) / 1000;
    tof1RangeInches = tof1Range * 39.3701;
    System.out.println(tof1Range);
}

@Override
public void initSendable(SendableBuilder builder){
    builder.setSmartDashboardType("Tof");
    builder.addDoubleProperty("Range", this::getRange1, null);
    builder.addDoubleProperty("Freq", this::getFreq1, null);
    builder.addDoubleProperty("Range (Inches)", this::getRange1Inches, null);
}
}