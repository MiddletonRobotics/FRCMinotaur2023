package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Autonomous.Auto;
import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ArcadeDrive;

import java.util.function.DoubleSupplier;

public class RobotContainer {
    
    CommandXboxController DriverController = XboxController.getDriverController();

    // Declare Drivetrain 

    private final DriveTrain drivetrain = new DriveTrain();
    private DoubleSupplier speed = () -> DriverController.getRightTriggerAxis() - DriverController.getLeftTriggerAxis();
    private DoubleSupplier rotation = () -> DriverController.getLeftX();
    private ArcadeDrive ArcadeDrive = new ArcadeDrive(drivetrain, speed, rotation);

    // Declare Auto

    private final Auto auto = new Auto(drivetrain);
    
       
    public RobotContainer() {
        configureButtonBindings();
        drivetrain.setDefaultCommand(ArcadeDrive);
    }
    //test comment
    private void configureButtonBindings() {}

    public Command getAutonomousCommand() {
        return auto;
    }
}
