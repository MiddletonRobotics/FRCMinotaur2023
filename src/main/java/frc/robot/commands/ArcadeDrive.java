package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Utilities.Constants.Constants.*;

import java.lang.module.ModuleDescriptor.Requires;

import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final DriveTrain drivetrain;

    public ArcadeDrive(Object object) {
        requires(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(DriverController.getRightTriggerAxis() > kJoystickAxisThreshold) {
            double speed = DriverController.getRightTriggerAxis();
            double rotation = DriverController.getLeftX();
        }
    }
}
