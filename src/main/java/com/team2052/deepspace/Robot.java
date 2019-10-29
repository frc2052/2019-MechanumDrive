package com.team2052.deepspace;

import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.lib.ControlLoop;
import com.team2052.lib.DriveHelper;
import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private DriveHelper driveHelper = null;
    private Controls controls = null;
    private DriveTrainController driveTrain = null;
    private RobotState robotstate = RobotState.getInstance();
    private RobotStateCalculator robotStateCalculator = RobotStateCalculator.getInstance();
    private ControlLoop controlLoop = new ControlLoop(Constants.Autonomous.kloopPeriodSec);
    private Compressor compressor = null;

    @Override
    public void robotInit() {
        driveHelper = new DriveHelper();
        controls = Controls.getInstance();
        driveTrain = DriveTrainController.getInstance();
        controlLoop.addLoopable(robotStateCalculator);

        try {
            compressor = new Compressor();
            compressor.setClosedLoopControl(true);
        } catch (Exception exc) {
            System.out.println("DANGER: No compressor!");
        }
        //AutoModeSelector.putToShuffleBoard();

        //AutoModeSelector.nullSelectedAutoMode();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {

    }

    /**
        This function called once when autonomous starts
     */
    @Override
    public void autonomousInit() {
        controlLoop.start();
        driveTrain.zeroGyro();

       /*// AutoMode currentMode = AutoModeSelector.getSelectedAutoMode();
       // System.out.println("selected :" + currentMode.getClass().getName());
        //use the instance to get direction and position
        //todo: make one direction enum
        robotStateCalculator.setStartDirection(currentMode.getStartDirection().isForward);
        robotStateCalculator.resetRobotState(AutoModeSelector.getStartingPos());
        System.out.println("starting x: " + robotstate.getLatestPosition().getLateral() + " y: "+ robotstate.getLatestPosition().getForward());
        //start running the auto mode
        autoModeRunner.start(currentMode);*/
    }

    /**
     * This function is called periodically during autonomous.
     */

    @Override
    public void autonomousPeriodic() {
        /*robotstate.outputToSmartDashboard();
        if(controls.getAutoOverride()){
            driveTrain.stop();
        }
        System.out.println("is auto done: " + autoModeRunner.isFinished());

        if(autoModeRunner.isFinished()){
            driverControlled();
            }*/

    }

    /**
     This function called once when teleoperated starts
     */
    @Override
    public void teleopInit(){
        //AutoModeSelector.nullSelectedAutoMode();
        robotStateCalculator.resetRobotState();
        controlLoop.start();
        driveTrain.zeroGyro();

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        driverControlled();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledPeriodic(){
        //autoModeRunner.stop();
        controlLoop.stop();
        driveTrain.stop();
        //AutoModeSelector.getSelectedAutoMode();
        //PurePursuitPathFollower.getInstance().resetPathFollower();
    }

    private void driverControlled(){

        double fl, fr, br, bl;

        fl = controls.getDriveTank() + controls.getDriveTurn() + controls.getStrafe();
        fr = controls.getDriveTank() - controls.getDriveTurn() - controls.getStrafe();
        bl = controls.getDriveTank() + controls.getDriveTurn() - controls.getStrafe();
        br = controls.getDriveTank() - controls.getDriveTurn() + controls.getStrafe();

        double max= Math.abs(fl);
        if (Math.abs(fr) > max) {
            max = Math.abs(fr);
        }
        if (Math.abs(br) > max) {
            max = Math.abs(br);
        }
        if (Math.abs(bl) > max) {
            max = Math.abs(bl);
        }

        if (max > 1) {
            fr = fr/max;
            fl = fl/max;
            br = br/max;
            bl = bl/max;
        }

        DriveSignal sig = new DriveSignal(fl, fr, bl, br, 0);

//        driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getStrafe(), controls.getQuickTurn()));
        driveTrain.drive(sig);
        robotstate.outputToSmartDashboard();
        //legClimberController.printEncoder();
            System.out.println("FL =  " + fl);
            }
        }


//        if(false && controls.getAutoInterrupt()){
//            AutoMode currentMode;
//            if(robotstate.getLatestPosition().getLateral() < 0) {
//                currentMode = new TeleopLeftTest(robotstate.getLatestPosition());
//            }else{
//                currentMode = new TeleopRightTest(robotstate.getLatestPosition());
//            }
//            System.out.println("Teleop return to hatch");
//            System.out.println("starting x: " + robotstate.getLatestPosition().getLateral() + " y: "+ robotstate.getLatestPosition().getForward());
//            //start running the auto mode
//            autoModeRunner.start(currentMode);
//        }else if(!autoModeRunner.isFinished()){
//            autoModeRunner.stop();
//        }

