package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class ColorSensorTest extends PacmanBotHardwareBase {
    final static VersionNumber version = new VersionNumber(1,0,0);
    ElapsedTime timer=new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Program", "Pacman Auto");
        telemetry.addData("Version", version.string());
        telemetry.addData("Hardware Base Version", hwbVersion.string());
        setupHardware();
    }

    //@Override
    public void main() {
        timer.startTime();
        sweeper.setPosition(0.7);
        if (getColorString(getEyeColor()) == "RED") {
            while (timer.time() < 0.1) {
                drive(0.1, 0);
            }
            drive(0, 0);
        }
    }
        /*
    }
    public void loop() {

    }*/
}

