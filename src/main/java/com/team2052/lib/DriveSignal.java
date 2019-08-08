package com.team2052.lib;

/**
 * Created by KnightKrawler on 1/19/2018.
 */
public class DriveSignal { //a drive signal is a motor speed for both motors. This allows us to send both variabes at once
    public double frontRightMotorSpeedPercent;
    public double backRightMotorSpeedPercent;
    public double frontLeftMotorSpeedPercent;
    public double backLeftMotorSpeedPercent;
    public double strafe;

    public
    DriveSignal(double frontLeft, double frontRight, double backLeft, double backRight, double strafe) {
        this.frontLeftMotorSpeedPercent = frontLeft - strafe;
        this.backLeftMotorSpeedPercent = backLeft + strafe;
        this.frontRightMotorSpeedPercent = frontRight - strafe;
        this.backRightMotorSpeedPercent = backRight + strafe;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0, 0, 0,  0);

    @Override
    public String toString() { //overrides the intrinsic tostring method to make debugging easier
        return "L: " + frontLeftMotorSpeedPercent + ", R: " + frontRightMotorSpeedPercent;
    }
}
