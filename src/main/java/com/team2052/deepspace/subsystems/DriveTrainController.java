package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.team2052.deepspace.Constants;
import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainController {

    // Instance of DriveTrainController class to be created in Robot.java class by running get instance
    private static DriveTrainController singleDriveTrainControllerInstance = new DriveTrainController();
    public static DriveTrainController getInstance() { return singleDriveTrainControllerInstance; }

    AHRS navXGyro = null;

    public final TalonSRX frontRight;
    public final TalonSRX frontLeft;
    public final TalonSRX backRight;
    public final TalonSRX backLeft;


    private Solenoid shifterIn;
    private Solenoid shifterOut;

    DriveTrainController(){
        frontRight = new TalonSRX(Constants.DriveTrain.kDriveFrontRightId);
        frontLeft = new TalonSRX(Constants.DriveTrain.kDriveFrontLeftId);
        backRight = new TalonSRX(Constants.DriveTrain.kDriveBackRightId);
        backLeft = new TalonSRX(Constants.DriveTrain.kDriveBackLeftId);


        frontRight.configFactoryDefault();
        frontLeft.configFactoryDefault();
        backRight.configFactoryDefault();
        backLeft.configFactoryDefault();


        frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);

        frontRight.setInverted(false);
        frontLeft.setInverted(true);
        backRight.setInverted(false);
        backLeft.setInverted(true);

        frontRight.setSensorPhase(true);
        frontLeft.setSensorPhase(true);

        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);



        try {
            /***********************************************************************
             * navX-MXP:
             * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.
             * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
             *
             * navX-Micro:
             * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
             * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
             *
             * Multiple navX-model devices on a single robot are supported.
             ************************************************************************/
            navXGyro = new AHRS(SPI.Port.kMXP);
            //ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte)50);
            navXGyro.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
            System.out.println("Error instantiating navX MXP:  " + ex.getMessage());
        }
    }

    public void stop(){
        drive(new DriveSignal(0,0 ,0 ,0, 0));
    }


    public void drive(DriveSignal driveSignal) {
        //System.out.println("Left Speed = " + driveSignal.frontLeftMotorSpeedPercent + " rightSpeed = " + driveSignal.frontRightMotorSpeedPercent);

        frontLeft.set(ControlMode.PercentOutput, driveSignal.frontLeftMotorSpeedPercent);
        backLeft.set(ControlMode.PercentOutput, driveSignal.backLeftMotorSpeedPercent);
        frontRight.set(ControlMode.PercentOutput, driveSignal.frontRightMotorSpeedPercent);
        backRight.set(ControlMode.PercentOutput, driveSignal.backRightMotorSpeedPercent);

    }

    public void driveAutoVelocityControl(double leftVel, double rightVel){
        //in/sec * rot/in * ticks/rot * .1 to get ticks/100ms
        System.out.println("Left Vel = " + leftVel + " right Vel = " + rightVel);
        frontLeft.set(ControlMode.Velocity, ((leftVel * Constants.DriveTrain.kTicksPerRot)/Constants.DriveTrain.kDriveWheelCircumferenceInches)/3);
        frontRight.set(ControlMode.Velocity, ((rightVel * Constants.DriveTrain.kTicksPerRot)/Constants.DriveTrain.kDriveWheelCircumferenceInches)/3);

        System.out.println("SENSOR VEL:" + frontLeft.getSelectedSensorVelocity() * (1.0/Constants.DriveTrain.kTicksPerRot) * Constants.DriveTrain.kDriveWheelCircumferenceInches * 10);


    }

    public void driveAutoMotionProfileControl(){

    }

    private double checkbounds(double Speed){ //this checks to make sure the speed is between 1 & -1
        if (Speed > 1){
            return 1.0;
        }else if(Speed < -1){
            return -1.0;
        }else{
            return Speed;
        }

    }

    public double getLeftEncoder(){
        SmartDashboard.putNumber("Left Encoder", frontLeft.getSelectedSensorPosition(0));

        return frontLeft.getSelectedSensorPosition(0);
    }
    public double getRightEncoder(){
        SmartDashboard.putNumber("Right Encoder", frontRight.getSelectedSensorPosition(0));
        return frontRight.getSelectedSensorPosition(0);
    }

    public void resetEncoders(){
        frontLeft.setSelectedSensorPosition(0, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        frontRight.setSelectedSensorPosition(0, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
    }
    
    public void zeroGyro() {
        if (navXGyro != null) {
            System.out.println("Reseting Gyro");
            try {
                navXGyro.reset();
            } catch  (Exception exc) {
                System.out.println("DANGER: Failed to reset Gyro" + exc.getMessage() + " ---- ");
                exc.printStackTrace();
            }
            if (navXGyro.isCalibrating())
            {
                System.out.println("Gyro still calibrating");
            }
            System.out.println("Gyro reset");
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
        }

    }

    /**
     * @return gyro angle in degrees
     */
    public double getGyroAngleDegrees() {
        if (navXGyro != null)
        {
            return navXGyro.getAngle(); //NOTE: getAngle tracks all rotations from init, so it can go beyond 360 and -360
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
            return 0;
        }
    }

    public double getGyroAngleRadians() {
        if (navXGyro != null)
        {
            return navXGyro.getAngle() * 0.017453; //NOTE: getAngle tracks all rotations from init, so it can go beyond 2PI and -2PI
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
            return 0;
        }
    }
    /*
    // old gyro code
     public double getOldGyroAngleDegrees() {
        // It just so happens that the gyro outputs 4x the amount that it actually turned
        return -gyro.getAngleZ() / 4.0;

    }
    public double getOldGyroAngleRadians(){
        return getOldGyroAngleDegrees() * 0.017453;
    }
    */
}

