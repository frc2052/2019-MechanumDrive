package com.team2052.lib;

import com.team2052.deepspace.Constants;

public class DriveHelper {
    double mQuickStopAccumulator;
    public static final double kThrottleDeadband = 0.1;
    private static final double kWheelDeadband = 0.1;
    private static final double kTurnSensitivity = 1.25;
    private DriveSignal mSignal = new DriveSignal(0, 0,0,0, 0);

    /**
     * Helper for driving
     * the "turning" stick controls the curvature of the robot's path rather than
     * its rate of heading change. This helps make the robot more controllable at
     * high speeds. Also handles the robot's quick turn functionality - "quick turn"
     * overrides constant-curvature turning for turn-in-place maneuvers.
     */
    public DriveSignal drive(double throttle, double wheel, double strafe, boolean isQuickTurn) {
        //the turn and tank is itself unless it is less than the deadband(deadzone), then it is zero
        wheel = handleDeadband(wheel, kWheelDeadband);
        throttle = handleDeadband(throttle, kThrottleDeadband);

        double overPower;

        //the value for the turning power
        double angularPower;

        //if the turn in place button is held
        if (isQuickTurn) {
            // if tank is less than 20%,
            if (Math.abs(throttle) < 0.2) {
                double alpha = 0.1;
                //the quick stop accumulator value is set to: (1-0.1)* quick stop accumulator + 0.1 * the turn speed {using the limit method} * 2
                mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * limit(wheel, 1.0) * 2;
            }

            overPower = 1.0;
            //turning power = turn * turn in place speed
            angularPower = wheel * Constants.DriveTrain.kTurnInPlaceSpeed;
        } else {
            overPower = 0.0;
            //turning power = tank * turn * turn sensitivity - quick stop accumulator
            //makes the robot turn differently based on the tank and the turn sensitivity, not just the turn
            angularPower = Math.abs(throttle) * wheel * kTurnSensitivity - mQuickStopAccumulator;

            //keeps the quick stop accumulator between -1 and 1
            if (mQuickStopAccumulator > 1) {
                mQuickStopAccumulator -= 1;
            } else if (mQuickStopAccumulator < -1) {
                mQuickStopAccumulator += 1;
            } else {
                mQuickStopAccumulator = 0.0;
            }
        }

        //sets the right and left speeds based the angular power value and the tank
        double rightPwm = throttle - angularPower;
        double leftPwm = throttle + angularPower;
        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }

        //sets the power of the motors to the left and right power values
        mSignal.frontRightMotorSpeedPercent = rightPwm;
        mSignal.backRightMotorSpeedPercent = rightPwm;
        mSignal.frontLeftMotorSpeedPercent = leftPwm;
        mSignal.backLeftMotorSpeedPercent = leftPwm;
        mSignal.strafe = strafe;

        return mSignal;
    }

    //if the value is less than the deadband it is zero
    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }

    //if the value is greater or less than the limit it is set to the limit
    private static double limit(double value, double limit) {
        if (Math.abs(value) > limit) {
            if (value < 0) {
                return -limit;
            } else {
                return limit;
            }
        }
        return value;
    }
}
