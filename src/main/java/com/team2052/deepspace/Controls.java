package com.team2052.deepspace;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    private static Controls instance = new Controls();

    public static Controls getInstance() {
        return instance;
    }

    private Controls() {
    }

    public static final int kTurnJoystickHatchOuttake = 1; //trigger
    public static final int kTurnJoystickAutoOverrideButton = 2;
    public static final int kTurnJoystickQuickTurn = 3;


    //Wyatt likes tank joystick on right
    private Joystick turnPrimaryStick = new Joystick(0); //left joystick
    private Joystick tankPrimaryStick = new Joystick(1);//right joystick
    private Joystick secondaryControlPanel = new Joystick(2);

    public double getDriveTank() {
        double val = -tankPrimaryStick.getY();
        if (val < .15 && val > -.15) { // dead zone
            val = 0;
        }
        return val;
    }

    public double getDriveTurn() {
        double val = turnPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0; // dead zone
        }
        return val;
    }

    public double getStrafe() {
        double val = tankPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0; // dead zone
        }
        return val;
    }

    public double getUnusedTank(){
        double val = -turnPrimaryStick.getY();
        if (val < .15 && val > -.15) { // dead zone
            val = 0;
        }
        return val;
    }


    public boolean getQuickTurn(){ return turnPrimaryStick.getRawButton(kTurnJoystickQuickTurn); }




}