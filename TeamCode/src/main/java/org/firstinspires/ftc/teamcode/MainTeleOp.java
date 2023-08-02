package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Savitar")
public class MainTeleOp extends CommandOpMode {

    private Motor frontLeft, backLeft, frontRight, backRight;
    private GamepadEx gpad;
    private FtcDashboard dashboard;

    private DriveSubsystem driveS;
    private DriveCommand driveC;

    @Override
    public void initialize() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        frontLeft = new Motor(hardwareMap, "fL");
        frontRight = new Motor(hardwareMap, "fR");
        backLeft = new Motor(hardwareMap, "bL");
        backRight = new Motor(hardwareMap, "bR");

        gpad = new GamepadEx(gamepad1);

        frontLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        frontRight.motor.setDirection(DcMotor.Direction.FORWARD);
        backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        backRight.motor.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        
        driveS = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);
        driveC = new DriveCommand(driveS, gpad::getLeftX, gpad::getLeftY, gpad::getRightX, 1.0);
        
        register(driveS);
        driveS.setDefaultCommand(driveC);
        

        schedule(new RunCommand(() ->{
            telemetry.update();
        }));

    }
}
