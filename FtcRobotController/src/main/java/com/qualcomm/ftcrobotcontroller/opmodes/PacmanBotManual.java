package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-10-22.
 * This class implements the top-level PacmanBot driving mechanics.
 *
 * Change log:
 * 1.0.1 - Fixed brush power bug.
 * 1.0.0 - First version.
 */
public class PacmanBotManual extends PacmanBotHardwareBase {

    public final VersionNumber version = new VersionNumber(1,0,1);

    @Override
    public void init() {
        telemetry.addData("Program","PacmanBotManual");
        telemetry.addData("Program Version",version.string());
        telemetry.addData("Hardware Base Version",hwbVersion.string());

        setupHardware();
    }

    @Override
    public void loop() {
        double drive_rate = -gamepad1.left_stick_y;
        double turn_rate = gamepad1.right_stick_x;

        drive(drive_rate,turn_rate);

        telemetry.addData("Drive Rate",drive_rate);
        telemetry.addData("Turn Rate",turn_rate);
        telemetry.addData("Perceived Color",getColorString(getEyeColor()));

        if (gamepad1.dpad_left) { setBrushPower(1.0); }
        else if (gamepad1.dpad_right) { setBrushPower(-1.0); }
        else setBrushPower(0.0);

    }
}
