package frc.robot.Utilities;

import frc.robot.Utilities.Drivers.XboxController;

public interface Section {
	void teleop(XboxController DriverController);
	void reset();
}