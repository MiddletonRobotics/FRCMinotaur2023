package frc.robot.Autonomous;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intakaur;
import frc.robot.subsystems.Arm;

public class Auto extends CommandBase {

  private SendableChooser<Integer> autoChooser = new SendableChooser<Integer>();
  private int selectedAuto;

  DriveTrain drivetrain;
  Intakaur intakaur;
  Arm arm;
  

  public Auto(DriveTrain drivetrain, Arm arm, Intakaur intakaur) {
    this.drivetrain = drivetrain;
    this.arm = arm;
    this.intakaur = intakaur;

    autoChooser.setDefaultOption("Do Nothing", 0);
    autoChooser.addOption("Drive Forward", 1);
    autoChooser.addOption("Drive Forward and Back", 2);
    autoChooser.addOption("Drive Forward, Back, and Shoot", 3);

    SmartDashboard.putData("Autonomous Chooser", autoChooser);

    addRequirements(drivetrain, arm, intakaur);
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

  public void auto1() {

  }
  
  public void auto2() {
    
  }
  
  public void auto3() {
    
  }
}
