package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by tdoylend on 2015-10-08.
 *
 * CHANGELOG:
 * 3.0.0 - Reversed robot direction *again*.
 * 2.0.0 - Reversed robot direction.
 * 1.1.0 - Added drive().
 * 1.0.0 - First version.
 */
public class TeslaBotHardwareBase extends OpMode {

    final static public VersionNumber hwbVersion = new VersionNumber(3,0,0);
    final static double REAR_MULTIPLIER = 0.875;

    DcMotorController.DeviceMode deviceMode;
    DcMotorController frontController;
    DcMotorController rearController;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;

    double clamp = 1.0;

    public void setupHardware() {
        frontController = hardwareMap.dcMotorController.get("front_ctrl");
        rearController  = hardwareMap.dcMotorController.get("rear_ctrl");

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        rearLeft = hardwareMap.dcMotor.get("rear_left");
        rearRight = hardwareMap.dcMotor.get("rear_right");

        deviceMode = DcMotorController.DeviceMode.WRITE_ONLY;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rearLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rearRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void setClamp(double clamp) {
        this.clamp = clamp;
    }

    public void drive(double ySpeed, double turnBias){
        double left_drive = ySpeed + turnBias;
        double right_drive = ySpeed - turnBias;

        left_drive = Range.clip(left_drive,-clamp,clamp);
        right_drive = Range.clip(right_drive,-clamp,clamp);

        frontLeft.setPower(left_drive);
        rearLeft.setPower(left_drive * REAR_MULTIPLIER);

        frontRight.setPower(right_drive);
        rearRight.setPower(right_drive * REAR_MULTIPLIER);
    }

    @Override
    public void init() {}
    @Override
    public void loop() {}
}
