package com.team2052.deepspace;


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


        public static final double kTurnInPlaceSpeed = .75;
    }


    public static class Autonomous{ //all units for distances, velocity, and acceleration are in inches

        public static final double kturnSpeed = 7.0; //constant from 1-5     higher = faster


        public static final long kloopPeriodMs = 50;
        public static final double kloopPeriodSec = kloopPeriodMs/1000.0; //int devision


    }


}
