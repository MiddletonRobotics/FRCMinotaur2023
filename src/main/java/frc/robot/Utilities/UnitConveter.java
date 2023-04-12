package frc.robot.Utilities;

public class UnitConveter {
    private static final double INCHES_TO_METERS_RATIO = 0.0254;

    public static double inchesToMeters(double inches) {
        return inches * INCHES_TO_METERS_RATIO;
    }
}
