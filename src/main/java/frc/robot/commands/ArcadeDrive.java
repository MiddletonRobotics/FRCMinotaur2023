package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// import static frc.robot.Utilities.Constants.Constants.*;

import java.util.function.DoubleSupplier;

import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final DriveTrain drivetrain;
    private final DoubleSupplier speed;
    private final DoubleSupplier rotation;

    public ArcadeDrive(DriveTrain drivetrain, DoubleSupplier speed, DoubleSupplier rotation) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    
    public void execute() {
        drivetrain.driveStraight(speed.getAsDouble(), rotation.getAsDouble());
        // if(DriverController.getRightTriggerAxis() > kJoystickAxisThreshold) {
        //     double speed = DriverController.getRightTriggerAxis();
        //     double rotation = DriverController.getLeftX();

        //     drivetrain.Drive(speed, rotation);
        // }else if(DriverController.getLeftTriggerAxis() > kJoystickAxisThreshold) {
        //     double speed = DriverController.getLeftTriggerAxis();
        //     double rotation = DriverController.getLeftX();

        //     drivetrain.Drive(-speed, rotation);
        // } else {
        //     drivetrain.Drive(0, 0);
        // }
    }
}
