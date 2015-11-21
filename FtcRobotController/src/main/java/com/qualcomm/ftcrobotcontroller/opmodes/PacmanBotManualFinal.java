package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
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

    final static double AUTO_DEPLOY_UNWIND = 3; //Time to unwind winch
    final static double AUTO_DEPLOY_FRONT = 1; //Time to swing forward
    final static double AUTO_DEPLOY_REAR = 0;
    final static double AUTO_DEPLOY_FINAL = 0;

    int autoDeployStage = -1; //Inactive

    double sweeperSide=1;

    @Override
    public void init() {
        telemetry.addData("Program","Ultimate Manual Drive");
        telemetry.addData("Version",version.string());
        telemetry.addData("HWB Version",hwbVersion.string());

        setupHardware();
        autoDeployTimer.reset();
        hook.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        //setDriveExponent(1.35);
        //setTurnExponent(1.35);
    }

    @Override
    public void loop() {
        double drive_rate,turn_rate;

        hook.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        checkUsers();

        setFinalRateMultiplier(gamepad.left_stick_button ? 1.0 : 0.5 );

        drive_rate = -gamepad.left_stick_y;
        turn_rate  = gamepad.right_stick_x;

        drive(drive_rate,turn_rate);

        setHookPower(threeWay(gamepad.left_trigger>0.5,gamepad.left_bumper));
        setWinchPower(threeWay(gamepad.right_trigger>0.5,gamepad.right_bumper));

        if (autoDeployStage!=-1) {
            switch (autoDeployStage) {
                case 0:
                    setWinchPower(-1);
                    if (autoDeployTimer.time()>AUTO_DEPLOY_UNWIND) autoDeployStage=1;
                    break;
                case 1:
                    setHookPower(-1);
                    if (autoDeployTimer.time()>AUTO_DEPLOY_FRONT+AUTO_DEPLOY_UNWIND) autoDeployStage=2;
                    break;
                case 2:
                    setHookPower(1);
                    if (hook.getCurrentPosition()>=AUTO_DEPLOY_REAR) autoDeployStage=-1;
                    break;

            }
        }

        setBrushPower(threeWay(gamepad.b,gamepad.a));

        //Later for zipline toggle.
        setThrower(gamepad.x);

        if (gamepad.dpad_left) sweeperSide=-1;
        if (gamepad.dpad_right) sweeperSide=1;

        setSweeperPosition(sweeperSide);

        if (gamepad.y && (autoDeployTimer.time()>AUTO_DEPLOY_TIMEOUT)) { //Auto-deploy
            autoDeployTimer.reset();
            autoDeployStage = 0; //Stage: unwind winch
        }
    }
}
