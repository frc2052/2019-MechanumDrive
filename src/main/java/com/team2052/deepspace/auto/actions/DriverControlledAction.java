package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Controls;
import com.team2052.deepspace.RobotState;
import com.team2052.deepspace.subsystems.*;
import com.team2052.lib.DriveHelper;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriverControlledAction implements Action{

    private DriveTrainController driveTrain;
    private Controls controls;
    private VisionController visionController;
    private DriveHelper driveHelper;
    private RobotState robotstate;
    private IntakeController intake;
    private GroundIntakeController groundIntake;

    private boolean intakeToggle;

    public DriverControlledAction(boolean startToggle){
        driveTrain = DriveTrainController.getInstance();
        controls = Controls.getInstance();
        visionController = VisionController.getInstance();
        driveHelper = new DriveHelper();
        robotstate = RobotState.getInstance();
        intake = IntakeController.getInstance();
        groundIntake = GroundIntakeController.getInstance();
        intakeToggle = startToggle;
    }


    @Override
    public void done() {

    }

    @Override
    //TODO: Make a button and ask Wat wat he likes
    public boolean isFinished() {
        return controls.getHatchOuttake();
    }

    @Override
    public void start() {

    }

    @Override
    public void update() { driverControlled(); }

    private void driverControlled(){
        if (controls.getLightFollow()) {
            if(visionController.getIsTarget()){
                driveTrain.drive(visionController.getMotorOutput(controls.getDriveTank()));
            } else {
                driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
            }
        } else {
            driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
        }
        robotstate.outputToSmartDashboard();
        driveTrain.setHighGear(controls.getShift());
        //legClimberController.printEncoder();

        visionController.showBackPiCamera(controls.getShowBackCamera());
        visionController.getValues();

        if(intake != null && groundIntake != null) {
            //System.out.println("INTAKES ARE NOT NULL");
            intake.setCargoIntake(controls.getCargoIntake());
            intake.toggleArmPosition(controls.getIntakeArmToggle());

            //shooting cargo
            if (controls.getCargoShoot() && controls.getRocket1Shoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.ROCKET1);
            } else if (controls.getCargoShoot() && controls.getRocket2Shoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.ROCKET2);
            }else if(controls.getCargoShoot() && controls.getIsShooterAgainstWall()){
                intake.setShootCargo(IntakeController.ShootSpeed.AGAINSTWALL);
            } else if (controls.getCargoShoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.CARGOSHIP);
            } else if(!controls.getCargoIntake()){
                //only stop motors if we're not doing cargo intake
                intake.setShootCargo(IntakeController.ShootSpeed.NONE);
            }

            if (controls.getRocket1Shoot()) {
                SmartDashboard.putString("LedStatus", "rocket1");
            } else if (controls.getRocket2Shoot()) {
                SmartDashboard.putString("LedStatus", "rocket2");
            } else if (controls.getClimberUp()) {
                SmartDashboard.putString("LedStatus", "climber");
            } else if (visionController.getIsTarget()){
                SmartDashboard.putString("LedStatus", "vision");
            } else {
                SmartDashboard.putString("LedStatus", "");
            }
        }
    }
}
