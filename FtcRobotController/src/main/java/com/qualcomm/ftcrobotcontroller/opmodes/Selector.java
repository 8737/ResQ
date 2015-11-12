package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by sathk_000 on 11/12/2015.
 */
public class Selector extends PacmanBotHardwareBase {
    boolean setup=true;
    boolean canLeft=true;
    boolean canRight=true;
    boolean canUp=true;
    boolean canDown=true;

    double power=1;
    double time=3;

    @Override
    public void init () {

    }

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void loop() {
        if (setup) {
            telemetry.addData("Power:",power);
            telemetry.addData("Time",time);
            if (!gamepad1.dpad_left) canLeft=true;
            else if (canLeft) {time -= .25; canLeft=false;}
            if (!gamepad1.dpad_right) canRight=true;
            else if (canRight){ time += .25; canRight=false;}
            if (!gamepad1.dpad_up) canUp=true;
            else if (canUp) { power += .1; canUp=false;}
            if (!gamepad1.dpad_down) canDown=true;
            else if (canDown){ time -= .1;canDown=false;}
            if (gamepad1.b) {
                setup=false;
                timer.reset();
            }

        }
        else {
            if (timer.time() < time) drive(power,0);
            else drive(0,0);
        }
    }
}
