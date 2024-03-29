package frc.robot.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.DriveTrain;

public class Auto extends CommandBase {

  private SendableChooser<Integer> autoChooser = new SendableChooser<Integer>();
  private double startTime;
  private int selectedAuto;

  DriveTrain drivetrain;

  public Auto(DriveTrain drivetrain) {
    this.drivetrain = drivetrain;

    autoChooser.setDefaultOption("Do Nothing", 0);
    autoChooser.addOption("Grid Position 3", 1);
    autoChooser.addOption("Placeholder", 2);
    autoChooser.addOption("Placeholder 2", 3);

    SmartDashboard.putData("Autonomous Chooser", autoChooser);

    getSelectedAutoRoutine();
    runSelectedAutoRoutine();

    addRequirements(drivetrain);
  }

  public void getSelectedAutoRoutine() {
    selectedAuto = autoChooser.getSelected();
  }

  public void runSelectedAutoRoutine() {
    if (selectedAuto == 1) {
      auto1();
    } else if (selectedAuto == 2) {
      auto2();
    } else if (selectedAuto == 3) {
      auto3();
    }
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  public void auto1() {
    double time = Timer.getFPGATimestamp();

    if(time - startTime < 0.3) {
      drivetrain.driveStraight(0.5, 0);
    } else if(time - startTime > 0.3 && time - startTime < 0.7) {
      drivetrain.driveStraight(-0.7, 0);
    } else if(time - startTime > 0.7 && time - startTime < 5.43) {
      drivetrain.driveStraight(0.5, 0);
    } else {
      drivetrain.driveStraight(0, 0);
    }
  }
  
  public void auto2() {
    
  }
  
  public void auto3() {
    
  }
}
