package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;

import frc.robot.Utilities.Constants.Constants;
import frc.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  public static WPI_TalonSRX[] leftMotors;
  public static WPI_TalonSRX[] rightMotors;

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

      public DifferentialDrive drivetrain;
    }
  }

  drivetrain = new DifferentialDrive(
      new MotorControllerGroup(leftMotors[0], leftMotors[1]), 
      new MotorControllerGroup(rightMotors[0], rightMotors[1])
  );

  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive(this));
  }
  
  @Override
  public void periodic() {}
  
  public void stop() {
    drivetrain.arcadeDrive(0, 0);
  }

  public void Drive(double speed, double rotation) {

    if(speed < Constants.kPositiveJoystickAxisThreshold && speed > Constants.kNegativeJoystickAxisThreshold) {
      speed = 0;
    } else if(rotation < Constants.kPositiveJoystickAxisThreshold && rotation > Constants.kNegativeJoystickAxisThreshold) {
      rotation = 0;
    } else {
      speed = speed * motorReductionSpeed;
      rotation = rotation * motorReductionTurn;
    }

    drivetrain.arcadeDrive(speed, rotation);
  }
}