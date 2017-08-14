package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name ="Autonomous_Tutorial")
@Disabled

public class Autonomous_Tutorial extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
//set up hardware map
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
// robot will start code after wait for start
        waitForStart();
        //robot power=0.5 distance is 48 inches, and a 3 sec timeout before nextmove
        encoderDrive(0.5,48, 48, 3);
        //.7 power turn right 30 inches and 4 sec timeout
        encoderDrive(0.7,30, 0, 4);
        //.5 power 40 inches to the left and 2 sec timeout
        encoderDrive(0.5,0, 40, 2);
        /*
    arcadeDrive(0.5, 0);
        runtime.reset();
        while (opModeIsActive() &&(runtime.seconds()<4.0)) {
            telemetry.addData("path", "1:Drive forward for 4 seconds", runtime.seconds());
            telemetry.update();
        }
        //drive back for 6 sec
        runtime.reset();
    arcadeDrive(-0.5,0);
        while (opModeIsActive() &&(runtime.seconds()<6.0)) {
            telemetry.addData("path","1:Drive backward for 6 seconds", runtime.seconds());
            telemetry.update();
        }
        */



    }

    public void arcadeDrive(double power, double turn) {
        double leftpower = Range.clip(power + turn, -1, 1);
        double rightpower = Range.clip(power + turn, -1, 1);

        leftMotor.setPower(leftpower);
        rightMotor.setPower(rightpower);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches,
                             double timeoutS) throws InterruptedException {
        int newlefttarget;
        int newrighttarget;

        if (opModeIsActive()) {
            newlefttarget = leftMotor.getCurrentPosition() * (int) (leftInches*1440);
            newrighttarget = rightMotor.getCurrentPosition() * (int) (rightInches*1440);
            leftMotor.setTargetPosition(newlefttarget);
            rightMotor.setTargetPosition(newrighttarget);

            runtime.reset();
            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

            while(opModeIsActive()&&(runtime.seconds()<timeoutS)&&
                    (leftMotor.isBusy()&& rightMotor.isBusy())){
                idle();
            }
        }
    }
}