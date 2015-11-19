package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-11-19.
 * This is the final manual drive for PacmanBot.
 *
 * Change log:
 * 1.0.0 - First version.
 */
public class PacmanBotManualFinal extends PacmanBotHardwareBase {

    VersionNumber version = new VersionNumber(1,0,0);

    double sweeperSide=1;

    @Override
    public void init() {
        telemetry.addData("Program","Ultimate Manual Drive");
        telemetry.addData("Version",version.string());
        telemetry.addData("HWB Version",hwbVersion.string());

        setupHardware();

        //setDriveExponent(1.35);
        //setTurnExponent(1.35);
    }

    @Override
    public void loop() {
        double drive_rate,turn_rate;

        checkUsers();

        setFinalRateMultiplier(gamepad.left_stick_button ? 1.0 : 0.5 );

        drive_rate = -gamepad.left_stick_y;
        turn_rate  = gamepad.right_stick_x;

        drive(drive_rate,turn_rate);

        setHookPower(threeWay(gamepad.left_trigger>0.5,gamepad.left_bumper));
        setWinchPower(threeWay(gamepad.right_trigger>0.5,gamepad.right_bumper));

        setBrushPower(threeWay(gamepad.b,gamepad.a));

        setThrower(gamepad.x);

        if (gamepad.dpad_left) sweeperSide=-1;
        if (gamepad.dpad_right) sweeperSide=1;

        setSweeperPosition(sweeperSide);
    }
}
