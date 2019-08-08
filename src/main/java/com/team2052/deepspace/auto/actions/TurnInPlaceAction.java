package com.team2052.deepspace.auto.actions;


import com.team2052.deepspace.Controls;
import com.team2052.deepspace.RobotState;
import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.lib.DriveSignal;

public class TurnInPlaceAction implements Action {

    private DriveTrainController driveTrainController;
    private Controls controls = Controls.getInstance();
    private RobotState robotState;
    private double baseSpeedConstant = .2;
    private double turnAngle;
    private double baseSpeed; //holds the current base speed
    private double error; //the angle the robot is off
    private double angle; //the angle the robot is currently
    private double target; //the angle we want to go to
    private double output; //the output to the wheels
    private double P = (1.0/150); //SpeedCurveMultiplier. this will increase the speed that we start at and increase the decceleration
    private TurnMode mode;

    private boolean isFinished = false;
    /**
     *
     * @param turnDegrees the change in rotation in degrees
     */

    public TurnInPlaceAction(TurnMode mode, double turnDegrees){
        this.mode = mode;
        turnAngle = turnDegrees;
        driveTrainController = DriveTrainController.getInstance();
        robotState = RobotState.getInstance();
    }
    public TurnInPlaceAction(TurnMode mode, double turnDegrees, double speed){
        this.mode = mode;
        turnAngle = turnDegrees;
        driveTrainController = DriveTrainController.getInstance();
        robotState = RobotState.getInstance();
        baseSpeedConstant = speed;
    }

    @Override
    public void done() {
        driveTrainController.stop();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void start() {
        if (mode == TurnMode.ROBOTCENTRIC){
            this.target = (robotState.getLatestPosition().getHeading() * 57.2958) + turnAngle; //turns a displacemen value to a set degree value
        }else {
            this.target = turnAngle;
        }
    }

    @Override
    public void update() {

        if(Math.abs(controls.getDriveTank()) > 0 || Math.abs(controls.getDriveTurn()) > 0){
            isFinished = true;
        }
        if(mode == TurnMode.FIELDNONTURNSPECIFIC){
            angle = Math.IEEEremainder((robotState.getLatestPosition().getHeading() * 57.2958), 360); //angle communicates with the gyro to find the current angle
        }else {
            angle = robotState.getLatestPosition().getHeading() * 57.2958; //angle communicates with the gyro to find the current angle
        }
        error = target - angle; //setting the error angle to equal the difference of the target angle and current angle

        if (Math.abs(error) < 4){ //if the robot is 2 degrees or less from the target position then finish, if not keep turning
            isFinished= true;
            driveTrainController.stop();
        }
        else {
            if (error < 0) { //if we are to the right of the target make the base speed negativve
                baseSpeed = -baseSpeedConstant;
            } else {
                baseSpeed = baseSpeedConstant;
            }

            double proportional = Math.abs(error);

            if (error < 0){
                proportional = -proportional;
            }
            output = baseSpeed + P * proportional; //(P * (error / 360)); //base speed is constant and error slowly goes down so the robot will slow down as it gets closer


            if (output > .8){
                output = .8;
            }else if (output < -.8) {
                output = -.8;
            }

            driveTrainController.drive(new DriveSignal(output, -output)); //setting the speeds for the left and right wheels for turning

            System.out.println("Current Angle: " + angle + "  Target: " + target + "   prop: " + proportional + "   Error: " + error + "   Output: " + output);
        }
    }

    public enum TurnMode{
        ROBOTCENTRIC,
        FIELDCENTRIC,
        FIELDNONTURNSPECIFIC
    }
}
