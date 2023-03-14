package frc.robot.Utilities.Constants;

public interface Constants {

    int leftMotorCount = 2;
    int rightMotorCount = 2;
    
    int leftMasterID = 1;
    int leftSlaveID = 2;
    int rightMasterID = 3;
    int rightSlaveID = 4;

    int DriverControllerPort = 0;

    double kTriggerAxisThreshold = 0.1;
    double kJoystickAxisThreshold = 0.1;
    double motorReductionSpeed = 1; 
    double motorReductionTurn = 1;

    double rightSpeedOverCorrection = 0.9;
}
