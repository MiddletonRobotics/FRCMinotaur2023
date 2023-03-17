package frc.robot.Utilities;

import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intakaur;

public class RetrieveSystems {
    public DriveTrain drivetrain;
    public Intakaur intakaur;
    public XboxController DriverController;

    public DriveTrain getDrivetrain() {
        return drivetrain;
    }

    public Intakaur getIntakaur() {
        return intakaur;
    }

    public XboxController getDriverController() {
        return DriverController;
    }
}
