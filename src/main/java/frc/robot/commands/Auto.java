package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;

public class Auto extends CommandBase {

  DriveTrain drivetrain;
  Arm arm;
  private double startTime;

  public Auto(DriveTrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    double time = Timer.getFPGATimestamp();

    if(time - startTime < 0.3) {
      drivetrain.driveStraight(0.5, 0);
    } else if(time - startTime > 0.3 && time - startTime < 0.7) {
      drivetrain.driveStraight(-0.7, 0);
    } else if(time - startTime > 0.7 && time - startTime < 5.43) {
      drivetrain.driveStraight(0.5, 0);
      arm.spinMotor(0.35);
    } else {
      drivetrain.driveStraight(0, 0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
