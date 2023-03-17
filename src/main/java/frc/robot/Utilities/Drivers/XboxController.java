package frc.robot.Utilities.Drivers;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Utilities.Constants.Constants.*;

public class XboxController {
    private static final CommandXboxController DriverController = new CommandXboxController(DriverControllerPort);
}
