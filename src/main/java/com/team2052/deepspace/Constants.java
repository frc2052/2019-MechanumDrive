package com.team2052.deepspace;


import com.team2052.deepspace.auto.AutoMode;

public class Constants {
    //All constant values for the robot code will go in this class.



    public class DriveTrain{
        public static final int kDriveFrontRightId = 1;
        public static final int kDriveFrontLeftId = 4;
        public static final int kDriveBackRightId = 5;
        public static final int kDriveBackLeftId = 6;


        public static final int kVelocityControlSlot = 0;
        public static final int kCANBusConfigTimeoutMS = 10;
        public static final int kTicksPerRot = 1024;
        public static final double kEncoderGearRatio = (1.0/3)*(20.0/64);
        public static final double kDriveWheelCircumferenceInches = 6.0 * Math.PI;

        public static final int kShiftInSolenoidID = 0;
        public static final int kShiftOutSolenoidID = 1;

        public static final double kTurnInPlaceSpeed = .75;
    }


    public static class Autonomous{ //all units for distances, velocity, and acceleration are in inches

        public static final double kturnSpeed = 7.0; //constant from 1-5     higher = faster
        public static final double kMaxAccel = 120; //how fast the robot accelerates and decelerates
        public static final double kLookaheadDistance = 30; //12-25 // changes how smooth it follows path. lower = curves back and forth/fishtail, higher = less accurate
        public static final double kA = 0.0 ; //0-.1 todo: test and see how robot responds
        public static final double kP = 0.0; //0-.1
        public static final double kAutoVelocity = 7 * 12 * 1.0;

        public static final double kHighGearturnSpeed = 2.5; //constant from 1-5     higher = faster
        public static final double kHighGearMaxAccel = 120; //how fast the robot accelerates and decelerates
        public static final double kHighGearLookaheadDistance = 30; //12-25 // changes how smooth it follows path. lower = curves back and forth/fishtail, higher = less accurate
        public static final double kHighGearAutoVelocity = 13 * 12 * 1.0;

        //the physical max velocity the robot can drive
        public static final double kMaxAutoVelocity = 7 * 12.0; //13 ft/s is high, 7 ft/s is low
        public static final double kMaxHighGearAutoVelocity = 13 * 12.0; //13 ft/s is high, 7 ft/s is low

        public static final long kloopPeriodMs = 50;
        public static final double kloopPeriodSec = kloopPeriodMs/1000.0; //int devision


        public static final int kNumOfFakePts = (int)((Constants.Autonomous.kLookaheadDistance * 1.5)/Constants.Autonomous.kMinPointSpacing); //how many extra point have we added after the last one?
        public static final double kTrackWidth = 28.0;
        public static final double kRequiredDistanceFromEnd = 3;
        public static final double kV = 1/(kMaxAutoVelocity);
        public static final double kHighGearkV = 1/(kMaxHighGearAutoVelocity);
        //todo: put ka and kp back here
        //pidf copied from 2017 needs testing

        public static final double kStartLeftInchOffset = -47;
        public static final double kStartRightInchOffset = 47;
        public static final double kStartHab2Offset = -48;

        public static final double kMinVelocity = 0.05; //range 0-1: minimum amount of power to overcome static friction

        public static final double kMinPointSpacing = 6;
        public static final AutoMode.StartDirection defaultStartDirection = AutoMode.StartDirection.BACKWARD;
    }


}
