// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private CommandXboxController xbox;
  private DifferentialDrive drive;

  public WPI_VictorSPX[] leftMotors;
  public WPI_VictorSPX[] rightMotors;

  @Override
  public void robotInit() {

    xbox = new CommandXboxController(0);

    try {
      leftMotors = new WPI_VictorSPX[leftMotorCount];
      leftMotors[0] = new WPI_VictorSPX(leftMasterID);
      leftMotors[1] = new WPI_VictorSPX(leftSlaveID);

      rightMotors = new WPI_VictorSPX[rightMotorCount];
      rightMotors[0] = new WPI_VictorSPX(rightMasterID);
      rightMotors[1] = new WPI_VictorSPX(rightSlaveID);
    } catch(Exception err) {
      System.out.println("Something went wrong" + err);
    }

    for (int i = 0; i < 2; i++) {
      leftMotors[i].setInverted(false);
      leftMotors[i].setNeutralMode(NeutralMode.Brake);
      leftMotors[i].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
      leftMotors[i].setSelectedSensorPosition(0);
      leftMotors[i].configVelocityMeasurementWindow(1);
      leftMotors[i].configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);

      rightMotors[i].setInverted(false);
      rightMotors[i].setNeutralMode(NeutralMode.Brake);
      rightMotors[i].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
      rightMotors[i].setSelectedSensorPosition(0);
      rightMotors[i].configVelocityMeasurementWindow(1);
      rightMotors[i].configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);

      if (i != 0) {
          leftMotors[i].follow(leftMotors[0]);
          rightMotors[i].follow(rightMotors[0]);
      }
    }

    drive = new DifferentialDrive(
      new MotorControllerGroup(leftMotors[0], leftMotors[1]),
      new MotorControllerGroup(rightMotors[0], rightMotors[1])
    );
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    System.out.println("Asterion is live!");
  }

  @Override
  public void teleopPeriodic() {
    drive.tankDrive(50, 0);
  }

  @Override
  public void disabledInit() {
    System.out.println("Asterion is disabled");
    drive.tankDrive(0, 0);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    if(xbox.getRightTriggerAxis() > kTriggerAxisThreshold) {
      drive.tankDrive(xbox.getRightTriggerAxis(), xbox.getLeftTriggerAxis());
    } else if(xbox.getLeftTriggerAxis() > kTriggerAxisThreshold) {
      drive.tankDrive(xbox.getLeftTriggerAxis(), xbox.getLeftX());
    } else if(xbox.getRightTriggerAxis() < kTriggerAxisThreshold) {
      drive.tankDrive(0, 0);
    } else if(xbox.getLeftTriggerAxis() < kTriggerAxisThreshold) {
      drive.tankDrive(0, 0);
    }
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
