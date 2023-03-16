package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  public static WPI_TalonSRX[] leftMotors;
  public static WPI_TalonSRX[] rightMotors;

  public static DifferentialDrive drivetrain;

  public DriveTrain() {
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

    final DifferentialDrive drivetrain = new DifferentialDrive(
      new MotorControllerGroup(leftMotors[0], leftMotors[1]), 
      new MotorControllerGroup(rightMotors[0], rightMotors[1])
    );

    drivetrain.setSafetyEnabled(false);
  }

  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive(this));
  }
  
  @Override
  public void periodic() {}
  
  public void stop(double speed, double rotation) {
    leftMotors[0].set(ControlMode.PercentOutput, 0);
    leftMotors[1].set(ControlMode.PercentOutput, 0);
    rightMotors[0].set(ControlMode.PercentOutput, 0);
    rightMotors[1].set(ControlMode.PercentOutput, 0);
  }

  public void ArcadeDrive(double speed, double rotation) {

    if(speed < 0.1 && speed > -0.1) {
      speed = 0;
    } else if(rotation < 0.1 && rotation > -0.1) {
      rotation = 0;
    } else {
      speed = speed * motorReductionSpeed;
      rotation = rotation * motorReductionTurn;
    }

    double leftDrive = speed - rotation;
    double rightDrive = speed + rotation;

    leftMotors[0].set(ControlMode.PercentOutput, leftDrive);
    leftMotors[1].set(ControlMode.PercentOutput, leftDrive);
    rightMotors[0].set(ControlMode.PercentOutput, rightDrive);
    rightMotors[1].set(ControlMode.PercentOutput, rightDrive);
  }
}