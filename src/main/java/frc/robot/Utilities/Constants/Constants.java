package frc.robot.Utilities.Constants;

public interface Constants {

    int leftMotorCount = 2;
    int rightMotorCount = 2;
    int intakaurMotorCount = 2;
    int armMotorCount = 1;
    
    int leftMasterID = 1;
    int leftSlaveID = 2;
    int rightMasterID = 3;
    int rightSlaveID = 4;

    int intakaurMotor1ID = 5;
    int intakaurMotor2ID = 6;

    int armMotorID = 7;

    double intakaurOutSpeed = -0.25;
    double intakaurOutSpeedX2 = 0.5;
    double intakaurInSpeed = 0.4;

    int ArmSpeed = 1;

    int DriverControllerPort = 0;

    double kTriggerAxisThreshold = 0.1;
    double kJoystickAxisThreshold = 0.1;

    int motorReductionSpeed = 1; 
    double motorReductionTurn = 0.4;

    double rightSpeedOverCorrection = 0.9;
}
