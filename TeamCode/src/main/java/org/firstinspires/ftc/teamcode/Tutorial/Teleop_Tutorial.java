package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Student on 8/12/2017.
 */

@TeleOp(name="Teleop_Tutorial")
public class Teleop_Tutorial extends OpMode {
// hardware map below
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo arm;
//semicolon for objects
    @Override
    public void init()  {
        //hardware map is to name everything
        //syntax: variable name = hardwareMap._____.get("name_goes_here");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        arm=hardwareMap.servo.get("arm");

        //assuming the right motor and the arm will be running in forward direction
        // must reverse one motor so left motor is reversed
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    //make sure all power set to 0 before it starts
    @Override
    public void start(){
        leftMotor.setPower(0.0);
        rightMotor.setPower(0);
        arm.setPosition(0);
        //servo from power 0 to 1
        //dc motor from -1 to 1
    }
    @Override
    public void loop(){
        arcadeDrive(-1*gamepad1.left_stick_y, gamepad1.right_stick_x);
    }

    @Override
    public void stop(){

    }
    //Arcade drive below is to use an arcade controller to drive it
    public void arcadeDrive(double power, double turn) {
        double leftpower = Range.clip(power + turn,-1,1);
        double rightpower = Range.clip(power + turn,-1,1);

        leftMotor.setPower(leftpower);
        rightMotor.setPower(rightpower);
    }
}
