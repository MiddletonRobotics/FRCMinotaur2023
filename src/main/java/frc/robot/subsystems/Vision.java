package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Utilities.Constants.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
    private NetworkTable table;

    public Vision() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public double getXPos() {
        NetworkTableEntry tx = table.getEntry("tx");
        return tx.getDouble(0.0);
    }

    public double getArea() {
        NetworkTableEntry ta = table.getEntry("ta");
        return ta.getDouble(0.0);
    }

    public boolean isTarget() {
        NetworkTableEntry tv = table.getEntry("tv");
        return (tv.getNumber(0).intValue() == 1)? true: false;
    }

    public void disableLight() {
        NetworkTableEntry light = table.getEntry("ledMode");
        light.setNumber(1);
    }

    public void enableLight() {
        NetworkTableEntry light = table.getEntry("ledMode");
        light.setNumber(3);
    }
}