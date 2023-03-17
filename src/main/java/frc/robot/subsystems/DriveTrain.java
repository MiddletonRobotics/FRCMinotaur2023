package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DriveTrain extends SubsystemBase {

  public WPI_TalonSRX[] leftMotors;
  public WPI_TalonSRX[] rightMotors;

  public DifferentialDrive drivetrain;
  public CommandXboxController DriverController = XboxController.getDriverController();

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

      drivetrain = new DifferentialDrive(
        new MotorControllerGroup(leftMotors[0], leftMotors[1]), 
        new MotorControllerGroup(rightMotors[0], rightMotors[1])
      );
    }
  }
  
  public void stop() {
    drivetrain.arcadeDrive(0, 0);
  }

  public void driveStraight() {
    double forwards = DriverController.getLeftTriggerAxis();
    double backwards = DriverController.getRightTriggerAxis();
    double rotation = DriverController.getLeftX();

    if(rotation < Constants.kPositiveJoystickAxisThreshold || rotation > Constants.kNegativeJoystickAxisThreshold) {
      rotation = 0;
    } else if(forwards < Constants.kPositiveJoystickAxisThreshold || forwards > Constants.kNegativeJoystickAxisThreshold) {
      forwards = 0;
    } else if(backwards < Constants.kPositiveJoystickAxisThreshold || backwards > Constants.kNegativeJoystickAxisThreshold) {
      backwards = 0;
    } else if((backwards > Constants.kPositiveJoystickAxisThreshold || backwards < Constants.kNegativeJoystickAxisThreshold) && (forwards > Constants.kPositiveJoystickAxisThreshold || forwards < Constants.kNegativeJoystickAxisThreshold)) {
      forwards = 0;
      backwards = 0;
    }

    drivetrain.arcadeDrive(forwards - backwards, rotation);
  }
}