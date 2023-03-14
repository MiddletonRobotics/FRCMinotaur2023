package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ArcadeDrive;

import static frc.robot.Utilities.Constants.Constants.*;

public class RobotContainer {
    CommandXboxController DriverController = XboxController.getDriverController();

    private final DriveTrain drivetrain = new DriveTrain();
    private ArcadeDrive ArcadeDrive = new ArcadeDrive(drivetrain);   

    public RobotContainer() {
        configureButtonBindings();

        drivetrain.setDefaultCommand(new RunCommand(() -> ArcadeDrive.execute(), drivetrain));
    }

    private void configureButtonBindings() {
        DriverController.leftTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));
        DriverController.rightTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));  
    }
}
