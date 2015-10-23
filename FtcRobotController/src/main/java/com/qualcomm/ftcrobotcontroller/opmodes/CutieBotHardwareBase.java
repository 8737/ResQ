package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by tdoylend on 2015-10-03.
 *
 * Version 1.0.4
 *
 * CHANGELOG:
 * 1.0.4 - Fixed negative bug.
 *         Added logarithmic drive.
 * 1.0.3 - Added more documentation for drive().
 * 1.0.2 - Added variable speed clamp.
 * 1.0.1 - Added drive().
 * 1.0.0 - First version
 */

public class CutieBotHardwareBase extends OpMode {

    DcMotorController.DeviceMode devMode;
    DcMotorController wheelController;
    DcMotor motorRight;
    DcMotor motorLeft;
    double drive_speed_clamp = 1.0;

    public void setupHardware() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        wheelController = hardwareMap.dcMotorController.get("wheels");
        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    public void setDriveSpeedClamp(double new_speed_clamp) {
        /*Set the drive speed clamp. Motor power is clamped to this value.
        Defaults to 100% (1.0).
         */
        drive_speed_clamp = new_speed_clamp;
    }

    public void drive(double y_speed, double turn_bias){
        /*Drive forward at speed y_speed, turning according to turn_bias.
        Clips drive values to 100%.

        y_speed determines the forward speed of the robot. It
        ranges from 1 - full forward - to 0 - full backward.
        turn_bias controls turning movement, where a negative value turns
        to the left and a positive value turns to the right.

        Using turn_bias with y_speed = 0 produces a turn in place.
        Using turn_bias with y_speed > 0 produces a moving turn.
         */

        if (y_speed<0) { y_speed = -(y_speed * y_speed); }
        else { y_speed = y_speed * y_speed; }

        if (turn_bias<0) { turn_bias = -(turn_bias * turn_bias); }
        else { turn_bias = turn_bias * turn_bias ; }

        double drive_left = y_speed + turn_bias;
        double drive_right = y_speed - turn_bias;

        drive_left = Range.clip(drive_left, -drive_speed_clamp, drive_speed_clamp);
        drive_right = Range.clip(drive_right, -drive_speed_clamp, drive_speed_clamp);

        motorLeft.setPower(drive_left);
        motorRight.setPower(drive_right);
    }

    @Override
    public void init() {
        
    }

    @Override
    public void loop() {

    }
}
