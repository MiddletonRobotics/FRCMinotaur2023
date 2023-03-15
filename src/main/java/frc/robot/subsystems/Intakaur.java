package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.Utilities.Constants.Constants.*;

public class Intakaur {
    public static WPI_VictorSPX[] intakaurMotors;

    public Intakaur() {
        intakaurMotors = new WPI_VictorSPX[intakaurMotorCount];
        intakaurMotors[0] = new WPI_VictorSPX(intakaurMotor1ID);
        intakaurMotors[1] = new WPI_VictorSPX(intakaurMotor2ID);

        for (int i = 0; i < 2; i++) {
            intakaurMotors[i].setInverted(true);
            intakaurMotors[i].setNeutralMode(NeutralMode.Brake);
            intakaurMotors[i].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
            intakaurMotors[i].setSelectedSensorPosition(0);
            intakaurMotors[i].configVelocityMeasurementWindow(1);
            intakaurMotors[i].configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);
        
            if (i != 0) {
                intakaurMotors[i].follow(intakaurMotors[0]);
            }
        }
    }

    public static CommandBase drive(double speed, double turn) {
        return new CommandBase() {
            @Override
            public void execute() {
                intakaurMotors[0].set(ControlMode.PercentOutput, speed * motorReductionSpeed);
                intakaurMotors[1].set(ControlMode.PercentOutput, speed * motorReductionSpeed);
            }
        };
    }

    public void stop() {
        intakaurMotors[0].set(ControlMode.PercentOutput, 0);
        intakaurMotors[1].set(ControlMode.PercentOutput, 0);
    }
}
