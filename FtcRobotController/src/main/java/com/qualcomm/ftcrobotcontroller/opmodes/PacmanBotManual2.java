package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-10-31.
 *
 * Change log:
 * 1.0.0 - First version.
 */

public class PacmanBotManual2 extends PacmanBotHardwareBase {
    final static VersionNumber version = new VersionNumber(1,0,0);

    @Override
    public void init() {
        telemetry.addData("Program","Manual Drive");
        telemetry.addData("Version",version.string());
        telemetry.addData("Hardware Base Version", hwbVersion.string());

        setupHardware();

        setSweeperPosition(0);
    }

    @Override
    public void loop() {
        checkUsers(); //This function checks whether Driver2 has overridden Driver1.

        //Drive the robot
        double drive_rate = -gamepad.left_stick_y;
        double turn_rate  = gamepad.right_stick_x;
        drive(drive_rate,turn_rate);

        setHookPower(threeWay(gamepad.left_bumper,gamepad.left_trigger>.5));
        setWinchPower(threeWay(gamepad.right_bumper,gamepad.right_trigger>.5));

        //Adjust the eye LED. Remove this during tournament; for testing purposes only.
        setEyeLED(gamepad.start);

        if (gamepad.dpad_left) setSweeperPosition(0);
        if (gamepad.dpad_right) setSweeperPosition(1);

        //Now for telemetry

        telemetry.addData("Manual Drive","Standard");
        telemetry.addData("Eye Sees",getColorString(getEyeColor()));
        telemetry.addData("Left Trigger",gamepad.left_trigger);
    }
}
