package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Robot extends TimedRobot {

  // Configure Right Motors
  private final WPI_TalonFX rightMotor1 = new WPI_TalonFX(0);
  private final WPI_TalonFX rightMotor2 = new WPI_TalonFX(0);

  // Configure Left Motors
  private final WPI_TalonFX leftMotor1 = new WPI_TalonFX(0);
  private final WPI_TalonFX leftMotor2 = new WPI_TalonFX(0);

  // Motor Controller Groups
  private final MotorControllerGroup rightSlave = new MotorControllerGroup(MotorController[], rightMotor1, rightMotor2);
  private final MotorControllerGroup leftSlave = new MotorControllerGroup(MotorController[], rightMotor1, rightMotor2);

  DifferentialDrive drivetrain = new DifferentialDrive(rightSlave, leftSlave);

  // Joystick
  Joystick stick = new Joystick(0);

  @Override
  public void robotInit() {}


  @Override
  public void robotPeriodic() {}


  @Override
  public void autonomousInit() {}


  @Override
  public void autonomousPeriodic() {}


  @Override
  public void teleopInit() {}


  @Override
  public void teleopPeriodic() {
    drivetrain.arcadeDrive(stick.getY(), stick.getZ());
  }


  @Override
  public void disabledInit() {}


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {}


  @Override
  public void simulationInit() {}


  @Override
  public void simulationPeriodic() {}
}