package frc.robot.Utilities.Constants;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface Constants extends GamepadButtons {

    //Hardware Ports   (CAN)
    int leftDrivetrainMasterID = 1;
    int leftDrivetrainSlave1ID = 4;
    int rightDrivetrainMasterID = 2;
    int rightDrivetrainSlave1ID = 3;
    int leftDrivetrainEncoder = 5;
    int rightDrivetrainEncoder = 6;
    int pigeonIMUID = 99;
    int intakaurMotorID = 22;
    int intakaurTiltMotorID = 21;
    int intakaurTiltEncodorID = 23;

    // PWM Ports
    int ledID = 2;
}
