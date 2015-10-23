package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by tdoylend on 2015-10-17.
 */
public class BadLegacyTester extends OpMode {

    DcMotor a1;
    DcMotor a2;
    DcMotor b1;
    DcMotor b2;

    @Override
    public void init() {
        a1 = hardwareMap.dcMotor.get("a1");
        a2 = hardwareMap.dcMotor.get("a2");
        b1 = hardwareMap.dcMotor.get("b1");
        b2 = hardwareMap.dcMotor.get("b2");
    }

    @Override
    public void loop() {
        a1.setPower(gamepad1.a ? 1.0 : 0.0);
        a2.setPower(gamepad1.b ? 1.0 : 0.0);
        b1.setPower(gamepad1.x ? 1.0 : 0.0);
        b2.setPower(gamepad1.y ? 1.0 : 0.0);
    }
}
