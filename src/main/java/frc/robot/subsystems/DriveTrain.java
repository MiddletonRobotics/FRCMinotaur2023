/*

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  public WPI_TalonSRX[] leftMotors;
  public WPI_TalonSRX[] rightMotors;

  private DifferentialDrive drive;
  private CommandXboxController xbox;

  private static DriveTrain instance = null;

  private DriveTrain() {
    leftMotors = new WPI_TalonSRX[leftMotorCount];
    leftMotors[0] = new WPI_TalonSRX(leftMasterID);
    leftMotors[1] = new WPI_TalonSRX(leftSlaveID);

    rightMotors = new WPI_TalonSRX[rightMotorCount];
    rightMotors[0] = new WPI_TalonSRX(rightMasterID);
    rightMotors[1] = new WPI_TalonSRX(rightSlaveID);

    for (int i = 0; i < 2; i++) {
      leftMotors[i].setInverted(true);
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

  public static DriveTrain getInstance() {
    if (instance == null) {
      instance = new DriveTrain();
    }
    return instance;
  }

  private static TestingPeriodic instance = null;

  private TestingPeriodic() {

    private DriveTrain drivetrain;
    private CommandXboxController xbox;

    drivetrain = DriveTrain.getInstance();
    xbox = new CommandXboxController(0);

    if(xbox.getRightTriggerAxis() > kTriggerAxisThreshold) {
      double controllerSpeedData = xbox.getRightTriggerAxis();
      double speed = controllerSpeedData * motorReductionSpeed;

      double turningData = xbox.getLeftX();
      double turn = turningData * motorReductionTurn;

      double driveRight = speed + turn * motorReductionSpeed;
      double driveLeft = speed - turn;

      drivetrain.leftMotors[0].set(driveLeft);
      drivetrain.leftMotors[1].set(driveLeft);
      drivetrain.rightMotors[0].set(driveRight);
      drivetrain.rightMotors[1].set(driveRight);

    } else if(xbox.getLeftTriggerAxis() > kTriggerAxisThreshold) {
      double controllerSpeedData = xbox.getLeftTriggerAxis();
      double speed = controllerSpeedData * motorReductionSpeed;

      double turningData = xbox.getLeftX();
      double turn = turningData * motorReductionTurn;

      double driveRight = -speed + turn * motorReductionSpeed;
      double driveLeft = -speed - turn;

      drivetrain.leftMotors[0].set(driveLeft);
      drivetrain.leftMotors[1].set(driveLeft);
      drivetrain.rightMotors[0].set(driveRight);
      drivetrain.rightMotors[1].set(driveRight);

    } else if(xbox.getRightTriggerAxis() < kTriggerAxisThreshold) {
      drivetrain.leftMotors[0].set(0);
      drivetrain.leftMotors[1].set(0);
      drivetrain.rightMotors[0].set(0);
      drivetrain.rightMotors[1].set(0);
    }
  }

  public static TestingPeriodic getInstance() {
    if (instance == null) {
      instance = new TestingPeriodic();
    }
    return instance;
  }
}

*/