package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Utilities.Constants.Constants;
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
        if(DriverController.getRightTriggerAxis() > Constants.kTriggerAxisThreshold) {
            double speed = DriverController.getRightTriggerAxis();
            double rotation = DriverController.getLeftX();

            drivetrain.Drive(speed, rotation);
        }else if(DriverController.getLeftTriggerAxis() > Constants.kTriggerAxisThreshold) {
            double speed = DriverController.getLeftTriggerAxis();
            double rotation = DriverController.getLeftX();

            drivetrain.Drive(-speed, rotation);
        } else {
            drivetrain.stop();
        }
    }
}
