package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-10-08.
 *
 * CHANGELOG:
 * 1.2.0 - Fixed bug where robot would drive in reverse.
 * 1.1.0 - Added driving and teleme try. Removed debug info in loop().
 * 1.0.1 - Fixed bug where the hardware was not set up.
 * 1.0.0 - First version.
 */
public class TeslaBotTeleOp extends TeslaBotHardwareBase {

    public final static VersionNumber version = new VersionNumber(1,2,0);

    @Override
    public void init() {

        setupHardware();

        telemetry.addData("Program version", version.string());
        telemetry.addData("HardwareBase version", hwbVersion.string());
    }

    @Override
    public void loop() {
        double drive_speed = -gamepad1.left_stick_y;
        double turn_speed  = gamepad1.right_stick_x;

        telemetry.addData("Drive Speed", drive_speed);
        telemetry.addData("Turn Speed", turn_speed);

        drive(drive_speed,turn_speed); //Drive the robot.
    }
}
