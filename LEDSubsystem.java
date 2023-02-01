// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {

  public AddressableLED m_addressableLED;
  public AddressableLEDBuffer m_LedBuffer;
  private boolean m_beamBreak;

  public void start() {}

  public void stop() {}

  /** define private counting, time interval, and color sequence*/
  private int itime=0, iinterval=1000; 
  /** rainbow color sequence https://www.freestylersupport.com/wiki/tutorial:sequences_ideas:rainbow_tutorial */
  private int[][] rgb_list = {
                             {255, 0, 0},    // red
                             {255, 255, 0},  // yellow
                             {0, 255, 0},    //green
                             {0, 255, 255},  // cyan
                             {0, 0, 255},    // blue
                             {255, 0, 255}   // magenta
                             }

  /** Creates a new LEDSubsystem. */
  public LEDSubsystem() {
    m_addressableLED = new AddressableLED(0);
    m_LedBuffer = new AddressableLEDBuffer(8);

    m_addressableLED.setLength(m_LedBuffer.getLength());
    m_addressableLED.setData(m_LedBuffer);
    m_addressableLED.start();
    // You could set on all the LEDs by calling for instance
    // setToColor(256, 0, 0);
    m_LedBuffer.setRGB(0, 256, 0, 0);
  }

  /** A bit code repitition for setTpPurple/Yellow/Orange, try avoid that.
   * I would do the following.*/
  // define a private function to set generic color. 
  private void setToColor(int r, int g, int b) {
    for (int i = 0; i < m_LedBuffer.getLength(); i++) {
      m_LedBuffer.setRGB(i, r, g, b);
    }
  }
  /** redefine your function if needed. */
  public void setToPurple() {
    setToColor(162, 25, 255);
  }

  public void setToYellow() {
    setToColor(255, 100, 0);
  }

  public void setToOrange() {
    setToColor(255, 15, 0);
  }
  


  // public void setToPurple() {
  //   for (int i = 0; i < m_LedBuffer.getLength(); i++) {
  //     m_LedBuffer.setRGB(i, 162, 25, 255);
  //   }
  // }

  // public void setToYellow() {
  //   for (int i = 0; i < m_LedBuffer.getLength(); i++) {
  //     m_LedBuffer.setRGB(i, 255, 100, 0);
  //   }
  // }

  // public void setToOrange() {
  //   for (int i = 0; i < m_LedBuffer.getLength(); i++) {
  //     m_LedBuffer.setRGB(i, 255, 15, 0);
  //   }
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // assume the each schedule run takes about 20ms, 
    // iinterval = 1000 implies change color every 20 seconds.
    // adjust iinterval as you prefer.
    // each call increase itime by 1.
    if (itime%iinterval == 0) {
      // which color to use
      int ic = itime/iinterval;
      setToColor(rgb_list[ic][0], rgb_list[ic][1], rgb_list[ic][2]);
      m_addressableLED.setData(m_LedBuffer);
    }
    itime++;
    // if itime reaches the maximal value defined by iinterval*rgb_list.length
    // reset to 0.
    if (itime == iinterval*rgb_list.length){
        itime = 0;
    }

    // I do not think one needs to call this every time,
    // call this only if m_LedBuffer gets modified.
    // m_addressableLED.setData(m_LedBuffer);
  }
}
