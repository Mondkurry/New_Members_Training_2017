package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Student on 8/14/2017.
 */

@TeleOp(name="practice coding")
public class practice_coding_Autonomous extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        waitForStart();

        encoderDrive(0.5, 48, 48, 3);
        encoderDrive(0.5, -20, 20, 3);
        encoderDrive(0.5, 25, 25, 3);

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
            newlefttarget = leftMotor.getCurrentPosition() * (int) (leftInches * 1440);
            newrighttarget = rightMotor.getCurrentPosition() * (int) (rightInches * 1440);
            leftMotor.setTargetPosition(newlefttarget);
            rightMotor.setTargetPosition(newrighttarget);

            runtime.reset();
            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {
                idle();
            }
        }
    }
}

