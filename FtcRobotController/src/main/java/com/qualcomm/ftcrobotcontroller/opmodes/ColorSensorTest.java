package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class ColorSensorTest extends PacmanBotHardwareBase {
    final static VersionNumber version = new VersionNumber(1,0,0);
    ElapsedTime timer=new ElapsedTime();
    boolean done=false;
    int red=0;
    int blue=0;
    int iterations=0;

    @Override
    public void init() {
        telemetry.addData("Program", "Pacman Auto");
        telemetry.addData("Version", version.string());
        telemetry.addData("Hardware Base Version", hwbVersion.string());
        setupHardware();
    }
    @Override
    public void loop() {
        if(iterations<5) {
            sweeper.setPosition(0.5);
            //eye.enableLed(true);
            red += eye.red();
            blue += eye.blue();
            String sr = String.format("%d", red);
            String sb = String.format("%d", blue);
            telemetry.addData("Red", sr);
            telemetry.addData("Blue", sb);
        }
        else if(iterations==6) {
            if(red>blue) {
                sweeper.setPosition(0.4);
            }
            else {
                sweeper.setPosition(0.53);
            }

            timer.startTime();
        }
        else if(timer.time()>2) {
            drive(0.3,0);
        }
        else if(timer.time()>3.5){
            drive(0,0);
            thrower.setPosition(0);
        }

//            if (red1 - blue1 > 0.25) {
//                sweeper.setPosition(0.45);
//            }
//            else{
//                sweeper.setPosition(0.53);
//            }
//            timer.startTime();
//            done=true;
        /*else if(timer.time() > 4.0)
        {
            drive(0,0);
        }
        else if(timer.time()>2.0)
        {
            drive(0.3,0);
        }*/
        iterations++;
    }
}

