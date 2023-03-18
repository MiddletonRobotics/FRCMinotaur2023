package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Auto extends CommandBase {

  DriveTrain drivetrain;
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

    if(time - startTime < 0.2) {
      drivetrain.driveStraight(0.5, 0);
    } else if(time - startTime > 0.2 && time - startTime < 0.55) {
      drivetrain.driveStraight(-0.7, 0);
    } else if(time - startTime > 0.55 && time - startTime < 1.33) {
      drivetrain.driveStraight(0.6, 0);
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
