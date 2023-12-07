// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Bling extends SubsystemBase {
  /** Creates a new Bling. */
  public AddressableLED m_LED;
  public AddressableLEDBuffer m_LedBuffer;

  public int ledR = 0;
  public int ledG = 0;
  public int ledB = 0;

  /*
  * COLORS FOR REFERENCE
  * Red: 15, 0, 0
  * Orange: 24, 10, 0
  * Yellow: 20, 20, 1
  * Green: 0, 15, 15
  * Blue: 0, 0, 5
  * Purple: 9, 2, 6
  */

  public Bling() {
    m_LED = new AddressableLED(9);
    m_LedBuffer = new AddressableLEDBuffer(8);
    m_LED.setLength(m_LedBuffer.getLength());
    m_LED.setData(m_LedBuffer);
    m_LED.start();
    SmartDashboard.putNumber("R Value", ledR);
    SmartDashboard.putNumber("G Value", ledG);
    SmartDashboard.putNumber("B Value", ledB);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_LED.setData(m_LedBuffer);
    ledR = (int)SmartDashboard.getNumber("R Value", ledR);
    ledG = (int)SmartDashboard.getNumber("G Value", ledG);
    ledB = (int)SmartDashboard.getNumber("B Value", ledB);
    //setRGBAll(ledR, ledG, ledB);
  }

  public void setRGB(int i, int r, int g, int b)
  {
    m_LedBuffer.setRGB(i, r, g, b);
  }

  public void setRGBAll(int r, int g, int b)
  {
    for(int i = 0; i < m_LedBuffer.getLength(); i++)
    {
      m_LedBuffer.setRGB(i, r, g, b);
    }
  }
  public void setRainbowALL ()
  {
    for(int i = 0; i < m_LedBuffer.getLength(); i++)
    {
      //purple to green gradient
      /*int RVal = i*(100/8);
      int GVal = 100-(i*(100/8));
      int BVal = 2+4*i;
      setRGB(i, RVal, GVal, BVal);
      */
      //rainbow, but copied off of internet
      int m_rainbowFirstPixelHue = 0;
      final int hue = (m_rainbowFirstPixelHue + (i*180/m_LedBuffer.getLength())) % 180;
      m_LedBuffer.setHSV(i, hue, 255, 128);

      m_rainbowFirstPixelHue +=3;
      m_rainbowFirstPixelHue %= 100;
      m_LED.setData(m_LedBuffer);
    }
  }
}