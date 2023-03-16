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
        if(DriverController.getRightTriggerAxis() > kJoystickAxisThreshold) {
            double speed = DriverController.getRightTriggerAxis();
            double rotation = DriverController.getLeftX();

            drivetrain.ArcadeDrive(speed, rotation);
        }else if(DriverController.getLeftTriggerAxis() > kJoystickAxisThreshold) {
            double speed = DriverController.getLeftTriggerAxis();
            double rotation = DriverController.getLeftX();

            drivetrain.ArcadeDrive(-speed, rotation);
        } else {
            drivetrain.ArcadeDrive(0, 0);
        }
    }
}
