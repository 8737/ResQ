package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by tdoylend on 2015-10-24.
 *
 * Change log:
 * 1.0.0 - First Version.
 */
public class PacmanBotColorTester extends PacmanBotHardwareBase {
    final VersionNumber version = new VersionNumber(1,0,0);

    TouchSensor button;

    @Override
    public void init() {
        telemetry.addData("Program", "PacmanBotManual");
        telemetry.addData("Program Version", version.string());
        telemetry.addData("Hardware Base Version", hwbVersion.string());

        setupHardware();

        button = hardwareMap.touchSensor.get("button");
    }

    @Override
    public void loop() {
        eye.enableLed(button.isPressed());

        telemetry.addData("Red",eye.red());
        telemetry.addData("Green",eye.green());
        telemetry.addData("Blue",eye.blue());



        //telemetry.addData("Perceived Color",getColorString(getEyeColor()));
    }
}
