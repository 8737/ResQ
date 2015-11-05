package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by tdoylend on 2015-11-05.
 */
public class TimerTest extends PacmanBotHardwareBase {

    ElapsedTime timer;
    boolean setupTime=false;
    final static double END_TIME = 5.0;
    @Override
    public void init() {
        telemetry.addData("TimerTest","Alive");
        setupHardware();
    }

    public void loop() {
        if (!setupTime) {
            timer.reset();
            setupTime=true;
        }

        if (timer.time() >= END_TIME) drive(0,0);
        else drive(1,0);



    }
}
