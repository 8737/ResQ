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
        sweeper.setPosition(0.1);
        if (getColorString(getEyeColor()) == "RED") {
            sweeper.setPosition(-0.1);
        }
        timer.startTime();
        drive(0.1, 0);
        while (timer.time() < 0.1) {
        }
        drive(0, 0);
    }
        /*
    }
    public void loop() {

    }*/
}

