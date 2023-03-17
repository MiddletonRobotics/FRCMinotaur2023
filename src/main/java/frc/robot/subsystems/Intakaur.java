package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import static frc.robot.Utilities.Constants.Constants.*;

import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Intakaur extends SubsystemBase {

    public WPI_VictorSPX[] intakaurMotors;
    public CommandXboxController DriverController = XboxController.getDriverController();

    public Intakaur() {
        intakaurMotors = new WPI_VictorSPX[intakaurMotorCount];
        intakaurMotors[0] = new WPI_VictorSPX(intakaurMotor1ID);
        intakaurMotors[1] = new WPI_VictorSPX(intakaurMotor2ID);

        for (int i = 0; i < 2; i++) {
            intakaurMotors[i].setInverted(false);
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

    public void spinMotor(double speed){
        intakaurMotors[0].set(ControlMode.PercentOutput, speed);
        intakaurMotors[1].set(ControlMode.PercentOutput, speed);
    }

    /* 

    public void intake(){
        double speed = Constants.intakaurInSpeed;
    }

    */

    public void stop() {
        intakaurMotors[0].set(ControlMode.PercentOutput, 0);
        intakaurMotors[1].set(ControlMode.PercentOutput, 0);
    }
}
