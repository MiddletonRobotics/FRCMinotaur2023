package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Utilities.Constants.Constants.*;

import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.Intakaur;

public class SpinIntakaur extends CommandBase {
    CommandXboxController DriverController = XboxController.getDriverController();
    private final Intakaur intakaur;
    
    public SpinIntakaur(Intakaur intakaur) {
        this.intakaur = intakaur;
        addRequirements(intakaur);
    }

    public void initialize(){}

    public void execute(double value){
       intakaur.spinMotor(value);
    }

    public boolean isFinished(){
        return true;
    }

    public void end() {

    }

    public void interrupted(){
        end();
    }
}
