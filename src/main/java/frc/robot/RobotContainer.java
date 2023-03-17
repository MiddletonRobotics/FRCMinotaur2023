package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.Intakaur;
import frc.robot.commands.SpinIntakaur;
import static frc.robot.Utilities.Constants.Constants.*;

import java.lang.constant.Constable;

public class RobotContainer {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final DriveTrain drivetrain = new DriveTrain();
    private ArcadeDrive ArcadeDrive = new ArcadeDrive(drivetrain);
    
    private final Intakaur intakaur = new Intakaur();
    private SpinIntakaur SpinIntakaur = new SpinIntakaur(new Intakaur());
       
    public RobotContainer() {
        configureButtonBindings();
        drivetrain.setDefaultCommand(ArcadeDrive);
        intakaur.setDefaultCommand(new RunCommand(() -> SpinIntakaur.execute(), intakaur));
    }

    private void configureButtonBindings() {
        DriverController.leftTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));
        DriverController.rightTrigger().whileTrue(Commands.run(() -> ArcadeDrive.execute()));
        
        DriverController.a().whileTrue(Commands.run(() -> intakaur.spinMotor(Constants.intakaurInSpeed)));
        DriverController.a().whileFalse(Commands.run(() -> intakaur.spinMotor(0)));
        DriverController.b().whileTrue(Commands.run(() -> intakaur.spinMotor(Constants.intakaurInSpeed)));
        DriverController.b().whileFalse(Commands.run(() -> intakaur.spinMotor(0)));
    }
}
