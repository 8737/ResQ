package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by tdoylend on 2015-11-19.
 * This is the final manual drive for PacmanBot.
 *
 * Change log:
 * 1.0.0 - First version.
 */
public class PacmanBotManualFinal extends PacmanBotHardwareBase {

    VersionNumber version = new VersionNumber(1,0,0);

    ElapsedTime autoDeployTimer = new ElapsedTime();
    final static double AUTO_DEPLOY_TIMEOUT = 10.0; //10 seconds before use of autoDeploy.

    final static double AUTO_DEPLOY_FRONT = -50;
    final static double AUTO_DEPLOY_REAR = 50;
    final static double AUTO_DEPLOY_FINAL = 0;

    double autoDeployStage = -1; //Inactive

    double sweeperSide=1;

    @Override
    public void init() {
        telemetry.addData("Program","Ultimate Manual Drive");
        telemetry.addData("Version",version.string());
        telemetry.addData("HWB Version",hwbVersion.string());

        setupHardware();
        autoDeployTimer.reset();

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

        //This code will later be replaced a the zipline toggle.
        setThrower(gamepad.x);

        if (gamepad.dpad_left) sweeperSide=-1;
        if (gamepad.dpad_right) sweeperSide=1;

        setSweeperPosition(sweeperSide);

        if (gamepad.y && (autoDeployTimer.time()>AUTO_DEPLOY_TIMEOUT)) { //Auto-deploy
            autoDeployTimer.reset();
            autoDeployStage = 0; //Stag
        }
    }
}
