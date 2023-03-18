package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Utilities.Constants.Constants;

public class Arm extends SubsystemBase {
    public WPI_VictorSPX[] armMotors;

    public Arm() {
        armMotors = new WPI_VictorSPX[Constants.armMotorCount];
        armMotors[0] = new WPI_VictorSPX(Constants.armMotorID);

        armMotors[0].setInverted(false);   
        armMotors[0].setNeutralMode(NeutralMode.Brake);
        armMotors[0].configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        armMotors[0].setSelectedSensorPosition(0);
        armMotors[0].configVelocityMeasurementWindow(1);
        armMotors[0].configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms); 
        
    }

    /* 

    public void initDefaultCommand() {
        setDefaultCommand(new SpinIntakaur(this));
    }

    */

    public void spinMotor(double speed) {
        armMotors[0].set(ControlMode.PercentOutput, speed); // replace with Constants.ArmSpeed if dynamic speed isnt working
    }

    public void stop() {
        armMotors[0].set(ControlMode.PercentOutput, 0);
    }
}