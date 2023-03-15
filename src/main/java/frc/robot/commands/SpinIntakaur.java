/*

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Utilities.Constants.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.Intakaur;

public class SpinIntakaur extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final Intakaur intakaur;

    public SpinIntakaur(Intakaur intakaur) {
        this.intakaur = intakaur;
        addRequirements(intakaur);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double intakaurSpeed;
        intakaur.intakaurMotors[0].set(ControlMode.PercentOutput, intakaurSpeed);
        intakaur.intakaurMotors[1].set(ControlMode.PercentOutput, intakaurSpeed);
    }
}

*/
