package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.Utilities.Constants.Constants;
import frc.robot.Utilities.Drivers.XboxController;
import frc.robot.Utilities.Section;

public class Vision {
    private NetworkTable table;

    public Vision() {
        table = NetworkTableInstance.getDefault().getTable("microvision");
    }

    public double getXPosition() {
        NetworkTableEntry tx = table.getEntry("tx");
        return tx.getDouble(0.0);
    }

    public double getYPosition() {
        NetworkTableEntry ta = table.getEntry("ta");
        return ta.getDouble(0.0);
    }

    public boolean isTarget() {
        NetworkTableEntry tv = table.getEntry("tv");
        return (tv.getNumber(0).intValue() == 1) ? true: false;
    }
}
