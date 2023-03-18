package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.Intakaur;
import frc.robot.commands.SpinIntakaur;
// import static frc.robot.Utilities.Constants.Constants.*;
import frc.robot.commands.Auto;
import frc.robot.subsystems.Arm;
import frc.robot.commands.ArmMotion;

import java.util.function.DoubleSupplier;

public class RobotContainer {
    CommandXboxController DriverController = XboxController.getDriverController();

    // Declare Drivetrain 

    private final DriveTrain drivetrain = new DriveTrain();

    private DoubleSupplier speed = () -> DriverController.getRightTriggerAxis() - DriverController.getLeftTriggerAxis();
    private DoubleSupplier rotation = () -> DriverController.getLeftX();

    private ArcadeDrive ArcadeDrive = new ArcadeDrive(drivetrain, speed, rotation);

    // Declare Arm

    private final Arm arm = new Arm();

    private DoubleSupplier armSpeed = () -> DriverController.getRightY();

    private ArmMotion ArmMotion = new ArmMotion(new Arm(), armSpeed);

    // Declare Intakaur

    private final Intakaur intakaur = new Intakaur();
    private SpinIntakaur SpinIntakaur = new SpinIntakaur(new Intakaur());

    // Declare Auto

    private final Auto auto = new Auto(drivetrain);
    
       
    public RobotContainer() {
        configureButtonBindings();
        drivetrain.setDefaultCommand(ArcadeDrive);
        arm.setDefaultCommand(ArmMotion);
        intakaur.setDefaultCommand(new RunCommand(() -> SpinIntakaur.execute(), intakaur));
    }

    private void configureButtonBindings() {

        // DriverController.leftTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));
        // DriverController.rightTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));
        
        DriverController.a().whileTrue(Commands.run(() -> intakaur.spinMotor(Constants.intakaurInSpeed)));
        DriverController.a().whileFalse(Commands.run(() -> intakaur.spinMotor(0)));
        DriverController.b().whileTrue(Commands.run(() -> intakaur.spinMotor(Constants.intakaurOutSpeed)));
        DriverController.b().whileFalse(Commands.run(() -> intakaur.spinMotor(0)));
    }

    public Command getAutonomousCommand() {
        return auto;
    }
}
