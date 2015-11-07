package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PacmanAuto1 extends PacmanBotHardwareBase {
    final static VersionNumber version = new VersionNumber(1,0,0);
    boolean set=false;
    ElapsedTime timer=new ElapsedTime();
    @Override
    public void init() {
        telemetry.addData("Program","Pacman Auto");
        telemetry.addData("Version",version.string());
        telemetry.addData("Hardware Base Version", hwbVersion.string());
        setupHardware();
        //setSweeperPosition(0);
    }

    @Override
    public void loop() {
        //Drive the robot
        /*double drive_rate = 100;
        double turn_rate  = 0;*/
        if(!set)
        {
            set=true;
            timer.startTime();
        }
        if(timer.time()<3)
        {
            drive(1,0);

        }
        else
        {
            drive(0,0);
        }
    }
}

