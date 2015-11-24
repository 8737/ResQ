package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by tdoylend on 2015-11-20.
 */
public class PacmanBotHookMonitor extends PacmanBotHardwareBase {
    @Override
    public void init() {
        telemetry.addData("Program", "PacmanBotHookMonitor");

        setupHardware();

        hook.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop() {
        hook.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        telemetry.addData("Hook Reading",hook.getCurrentPosition());
    }
}
