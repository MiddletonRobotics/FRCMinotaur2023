// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.subsystems.*;

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
  private DriveTrain drivetrain;

  @Override
  public void robotInit() {
    xbox = new CommandXboxController(0);
    drivetrain = DriveTrain.getInstance();
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
    drive.tankDrive(0.5, 0.5);
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
      double controllerSpeedData = xbox.getRightTriggerAxis();
      double speed = controllerSpeedData * motorReductionSpeed;

      double turningData = xbox.getLeftX();
      double turn = turningData * motorReductionTurn;

      double driveRight = speed + turn * motorReductionSpeed;
      double driveLeft = speed - turn;

      leftMotors[0].set(driveLeft);
      leftMotors[1].set(driveLeft);
      rightMotors[0].set(driveRight);
      rightMotors[1].set(driveRight);

    } else if(xbox.getLeftTriggerAxis() > kTriggerAxisThreshold) {
      double controllerSpeedData = xbox.getLeftTriggerAxis();
      double speed = controllerSpeedData * motorReductionSpeed;

      double turningData = xbox.getLeftX();
      double turn = turningData * motorReductionTurn;

      double driveRight = -speed + turn * motorReductionSpeed;
      double driveLeft = -speed - turn;

      leftMotors[0].set(driveLeft);
      leftMotors[1].set(driveLeft);
      rightMotors[0].set(driveRight);
      rightMotors[1].set(driveRight);

    } else if(xbox.getRightTriggerAxis() < kTriggerAxisThreshold || xbox.getRightTriggerAxis() < kTriggerAxisThreshold) {
      drive.arcadeDrive(0, 0);
    }
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
