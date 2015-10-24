package com.qualcomm.ftcrobotcontroller.opmodes;

//import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by tdoylend on 2015-10-03.
 *
 * This object allows simple manual drive of the CutieBot using the new
 * hardware abstraction methods.
 */

public class CutieBotManual extends CutieBotHardwareBase {
    static double DRIVE_RATE = 1.;  //This is the rate at which the robot will drive.
                                     //For example, setting it to 0.5 will run the drive at half power.
    static double TURN_RATE = 1.;   //This does the same thing for the turn speed.
    static double DRIVE_CLAMP = 1.; //Drive speed clamp. See setDriveSpeedClamp().

    com.qualcomm.robotcore.util.ElapsedTime timeClass;

    @Override
    public void init() {
        setupHardware();
        setDriveSpeedClamp(DRIVE_CLAMP);
        timeClass = new com.qualcomm.robotcore.util.ElapsedTime();
    }

    @Override
    public void loop() {

        double drive_speed = gamepad1.left_stick_y * DRIVE_RATE;
        double turn_speed  = gamepad1.right_stick_x * TURN_RATE;

        drive(drive_speed,turn_speed); //Drive the robot.

        telemetry.addData("CutieBot says", "Hello World from CBM4!");
        telemetry.addData("Timestamp",timeClass.time());
        telemetry.addData("Desired Power", drive_speed);
        telemetry.addData("Actual Power", drive_speed * drive_speed);
    }
}
