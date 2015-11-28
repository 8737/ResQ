package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-11-28.
 *
 * Change log:
 * 1.0.0 - First version.
 */
public class PacmanBotManual3000 extends PacmanBotHardwareBase {

    VersionNumber version = new VersionNumber(1,0,0);

    @Override
    public void init() {
        telemetry.addData("Program","Manual Drive 3000");
        telemetry.addData("Version",version.string());
        telemetry.addData("HWB Version", hwbVersion.string());

        setupHardware();
    }
}
