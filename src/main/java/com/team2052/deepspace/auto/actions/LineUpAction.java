package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.RobotState;
import com.team2052.deepspace.subsystems.*;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.DriveSignal;

public class LineUpAction implements Action{
    private LineFollowerControllerBase lineFollower = null;
    private DriveTrainController driveTrain = null;
    private VisionController visionController = null;
    private RobotState robotState = null;
    private Position2d startPos;
    private boolean isForwards;
    private double targetDistance;
    private Position2d currentPos = new Position2d();


    public LineUpAction(double targetDistance, boolean isForwards) {
        this.targetDistance = targetDistance;
        this.isForwards = isForwards;
    }

    public LineUpAction(boolean isForwards) {
        targetDistance = 12;
        this.isForwards = isForwards;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
       return targetDistance < Math.sqrt(Math.pow(startPos.getForward() - currentPos.getForward(), 2) + Math.pow(startPos.getLateral() - currentPos.getLateral(), 2));
    }

    @Override
    public void start() {
        if (isForwards) {
            lineFollower = LineFollowerController.getInstance();
        } else {
            lineFollower = BackLineFollowerController.getInstance();
        }
        driveTrain = DriveTrainController.getInstance();
        visionController = VisionController.getInstance();
        robotState = RobotState.getInstance();
        startPos = robotState.getLatestPosition().getClone();
        currentPos = robotState.getLatestPosition();
    }

    @Override
    public void update() {
        currentPos = robotState.getLatestPosition();
        System.out.println("sPos: " + startPos + " cPos: " + currentPos);
        System.out.println("LineUp dis: " + Math.sqrt(Math.pow(startPos.getForward() - currentPos.getForward(), 2) + Math.pow(startPos.getLateral() - currentPos.getLateral(), 2)));
/*
        if (lineFollower.getLineSensed()) {
            if (isForwards) {
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(Constants.LineFollower.kLightSensorMotorSpeed));
            } else {
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(-Constants.LineFollower.kLightSensorMotorSpeed)); //Reverse Speed for backwards.
            }
        } else {
        */
        if(visionController.getIsTarget()) {
            driveTrain.drive(visionController.getMotorOutput(.5));
        }else{
            if(isForwards){
                driveTrain.drive(new DriveSignal(.15, .15));
            }else {
                //driveTrain.drive(visionController.getMotorOutput(.5));
                driveTrain.drive(new DriveSignal(-.15, -.15));
            }
        }

    }
}