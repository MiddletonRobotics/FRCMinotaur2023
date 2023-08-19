package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import frc.robot.Utilities.Constants.Constants;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  public WPI_TalonSRX[] leftMotors;
  public WPI_TalonSRX[] rightMotors;

  public DifferentialDrive drivetrain;

  public DriveTrain() {
    leftMotors = new WPI_TalonSRX[Constants.leftMotorCount];
    leftMotors[0] = new WPI_TalonSRX(Constants.leftMasterID);
    leftMotors[1] = new WPI_TalonSRX(Constants.leftSlaveID);

    rightMotors = new WPI_TalonSRX[Constants.rightMotorCount];
    rightMotors[0] = new WPI_TalonSRX(Constants.rightMasterID);
    rightMotors[1] = new WPI_TalonSRX(Constants.rightSlaveID);

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

    drivetrain = new DifferentialDrive(
      new MotorControllerGroup(leftMotors[0], leftMotors[1]), 
      new MotorControllerGroup(rightMotors[0], rightMotors[1])
    );

    drivetrain.setSafetyEnabled(false);
  }

  // public void initDefaultCommand() {
  //   setDefaultCommand(new ArcadeDrive(this));
  // }
  
  @Override
  public void periodic() {}

  public void driveStraight(double speed, double rotation) {
    if(speed < 0.1 && speed > -0.1) {
      speed = 0;
    } else if(rotation < 0.1 && rotation > -0.1) {
      rotation = 0;
    } else {
      speed = speed;
      rotation = rotation;
    }

    drivetrain.arcadeDrive(speed * Constants.motorReductionSpeed, rotation * Constants.motorReductionTurn);
  }

  public void stopDrive() {
    drivetrain.arcadeDrive(0, 0);
  }

  public WPI_TalonSRX getLeftLeader() {
    return this.leftMotors[0];
  }

  public WPI_TalonSRX getLeftFollower() {
    return this.leftMotors[1];
  }

  public WPI_TalonSRX getRightLeader() {
    return this.rightMotors[0];
  }

  public WPI_TalonSRX getRightFollower() {
    return this.rightMotors[1];
  }
}