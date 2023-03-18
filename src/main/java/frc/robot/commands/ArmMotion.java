package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.subsystems.Arm;
import frc.robot.Utilities.Drivers.XboxController;

public class ArmMotion extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final Arm arm;
    private final DoubleSupplier speed;

    public ArmMotion(Arm arm, DoubleSupplier speed) {
        this.arm = arm;
        this.speed = speed;

        addRequirements(arm);
    }

    @Override
    public void initialize() {}

    
    public void execute() {
        arm.spinMotor(speed.getAsDouble());
    }

}
