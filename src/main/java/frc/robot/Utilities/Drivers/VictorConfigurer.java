package frc.robot.Utilities.Drivers;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import frc.robot.Utilities.Constants.Constants;

public class VictorConfigurer {
    public static WPI_VictorSPX[] leftMotors;
    public static WPI_VictorSPX[] rightMotors;

    public DifferentialDrive drivetrain;

    public void setupVictorSPX() {
        leftMotors = new WPI_VictorSPX[Constants.leftMotorCount];
        leftMotors[0] = new WPI_VictorSPX(Constants.leftMasterID);
        leftMotors[1] = new WPI_VictorSPX(Constants.leftSlaveID);

        rightMotors = new WPI_VictorSPX[Constants.rightMotorCount];
        rightMotors[0] = new WPI_VictorSPX(Constants.rightMasterID);
        rightMotors[1] = new WPI_VictorSPX(Constants.rightSlaveID);

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
    }

    public void setNeturalMode(String mode) {
        if(mode != "Brake" || mode != "Coast" || mode != "EEPROMSetting") {
            System.out.println("Invalid Neutral Mode");
            return;
        } else {
            for (int i = 0; i < 2; i++) {
                if(mode == "Brake") {
                    leftMotors[i].setNeutralMode(NeutralMode.Brake);
                    rightMotors[i].setNeutralMode(NeutralMode.Brake);
                } else if(mode == "Coast") {
                    leftMotors[i].setNeutralMode(NeutralMode.Coast);
                    rightMotors[i].setNeutralMode(NeutralMode.Coast);
                } else if(mode == "EEPROMSetting") {
                    leftMotors[i].setNeutralMode(NeutralMode.EEPROMSetting);
                    rightMotors[i].setNeutralMode(NeutralMode.EEPROMSetting);
                }
            }
        }
    }

    public void switchInverted() {
        System.out.println("Switching Inverted");
        
        if(leftMotors[0].getInverted() == true) {
            for (int i = 0; i < 2; i++) {
                leftMotors[i].setInverted(false);
                rightMotors[i].setInverted(true);
            }
        } else {
            for (int i = 0; i < 2; i++) {
                leftMotors[i].setInverted(true);
                rightMotors[i].setInverted(false);
            }
        }
    }

    public void setMaxOutput(double maxOutput) {
        drivetrain.setMaxOutput(maxOutput);
    }

    public void setSensorPhase(boolean phase) {
        for (int i = 0; i < 2; i++) {
            leftMotors[i].setSensorPhase(phase);
            rightMotors[i].setSensorPhase(phase);
        }
    }

    public void setVoltageCompensation(boolean toggle) {
        if(toggle != true || toggle != false) {
            System.out.println("Invalid Voltage Compensation Toggle. Must be a boolean");
            return;
        } else {
            for (int i = 0; i < 2; i++) {
                leftMotors[i].enableVoltageCompensation(toggle);
                rightMotors[i].enableVoltageCompensation(toggle);
            }
        }
    }

    public void setVoltageCompensationRampRate(double rampRate) {
        for (int i = 0; i < 2; i++) {
            leftMotors[i].configVoltageCompSaturation(rampRate);
            rightMotors[i].configVoltageCompSaturation(rampRate);
        }
    }

    public void enableMotorSafety(boolean toggle) {
        if(toggle != true || toggle != false) {
            System.out.println("Invalid Motor Safety Toggle. Must be a boolean");
            return;
        } else {
            for (int i = 0; i < 2; i++) {
                leftMotors[i].setSafetyEnabled(toggle);
                rightMotors[i].setSafetyEnabled(toggle);
            }
        }
    }
}
