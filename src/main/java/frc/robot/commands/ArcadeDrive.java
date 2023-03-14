package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final DriveTrain drivetrain;

    public ArcadeDrive(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double leftTriggerAxis = DriverController.getLeftTriggerAxis();
        double leftXAxis = DriverController.getLeftX();

        double rightDrive = leftTriggerAxis + leftXAxis;
        double leftDrive = leftTriggerAxis - leftXAxis;

        drivetrain.leftMotors[0].set(ControlMode.PercentOutput, leftDrive);
        drivetrain.leftMotors[1].set(ControlMode.PercentOutput, leftDrive);
        drivetrain.rightMotors[0].set(ControlMode.PercentOutput, rightDrive);
        drivetrain.rightMotors[1].set(ControlMode.PercentOutput, rightDrive);
    }
}
