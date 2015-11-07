package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by tdoylend on 2015-11-05.
 */
public class ControlTester extends PacmanBotHardwareBase {
    public String boolStr(boolean a) {
        return a ? "True" : "False";
    }
    @Override
    public void loop() {
        telemetry.addData("Left trigger", gamepad1.left_trigger);
        telemetry.addData("Left Bumper",gamepad1.left_bumper);
        telemetry.addData("X",gamepad1.x);
        //gamepad1.
    }
}
