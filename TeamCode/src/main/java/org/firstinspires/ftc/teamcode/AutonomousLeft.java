package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Example OpMode. Demonstrates use of gyro, color sensor, encoders, and telemetry.
 *
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "AutonomousLeft", group = "MecanumBot")
public class AutonomousLeft extends LinearOpMode {
    DcMotor m1, m2, m3, m4;
    @Override
    public void runOpMode(){
        m1 = hardwareMap.dcMotor.get("back_left_motor");
        m2 = hardwareMap.dcMotor.get("front_left_motor");
        m3 = hardwareMap.dcMotor.get("front_right_motor");
        m4 = hardwareMap.dcMotor.get("back_right_motor");
        m1.setDirection(DcMotor.Direction.REVERSE);
        m2.setDirection(DcMotor.Direction.REVERSE);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                m1.getCurrentPosition(),
                m2.getCurrentPosition(),
                m3.getCurrentPosition(),
                m4.getCurrentPosition());
        telemetry.update();
//
//        DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
//        DistanceSensor leftDistance = hardwareMap.get(DistanceSensor.class, "left_distance");
//        DistanceSensor rightDistance = hardwareMap.get(DistanceSensor.class, "right_distance");
//        DistanceSensor backDistance = hardwareMap.get(DistanceSensor.class, "back_distance");

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.accelerationIntegrationAlgorithm = null;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationData = null;
        parameters.calibrationDataFile = "";
        parameters.loggingEnabled = false;
        parameters.loggingTag = "Who cares.";
        imu.initialize(parameters);

        //ColorSensor colorSensor = hardwareMap.colorSensor.get("color_sensor");
        telemetry.addData("Press Start When Ready","");
        telemetry.update();

        waitForStart();
//first and third moves it right, second and fourth moves it left
        driveleft(24);
        driveright(22);
        driveforward(24);




       /* String color = "red";
        if (color == "red") {
            // rotation("clockwise", 1050, 1);
            forwardDrive("forward", 950, 1);
            StrafeDrive("right", 1260, 1);
            StrafeDrive("left", 2100,1);

        } else if (color == "blue") {
            forwardDrive("forward", 1000, 1);
            StrafeDrive("right", 1260, 1);
            StrafeDrive("left", 1200,1);
        }
        else {
            forwardDrive("forward", 1000, 1);
            StrafeDrive("right", 1260, 1);
            StrafeDrive("left", 450, 1);
        }*/



        /*telemetry.addData("Color","R %d  G %d  B %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        telemetry.addData("Heading", " %.1f", orientation.firstAngle * 180.0 / Math.PI);

        telemetry.addData("Front Distance", " %.1f", frontDistance.getDistance(DistanceUnit.CM));
        telemetry.addData("Left Distance", " %.1f", leftDistance.getDistance(DistanceUnit.CM));
        telemetry.addData("Right Distance", " %.1f", rightDistance.getDistance(DistanceUnit.CM));
        telemetry.addData("Back Distance", " %.1f", backDistance.getDistance(DistanceUnit.CM));
        telemetry.addData("Encoders"," %d %d %d %d", m1.getCurrentPosition(), m2.getCurrentPosition(),
                m3.getCurrentPosition(), m4.getCurrentPosition());
        telemetry.update();*/


    }
    public void driveforward(int inches) {
        int ticks = (int) (inches * (1440 / 35.7));
        m1.setTargetPosition(ticks);
        m2.setTargetPosition(ticks);
        m3.setTargetPosition(ticks);
        m4.setTargetPosition(ticks);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        int pauseTime = (int) (inches * 100);
        sleep(pauseTime);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
    public void driveleft(int inches) {
        int ticks = (int) (inches * (1440 / 27.1));
        m1.setTargetPosition(ticks);
        m2.setTargetPosition(-ticks);
        m3.setTargetPosition(ticks);
        m4.setTargetPosition(-ticks);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        int pauseTime = (int) (inches * 100);
        sleep(pauseTime);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
    public void driveright(int inches) {
        int ticks = (int) (inches * (1440 / 27.1));
        m1.setTargetPosition(-ticks);
        m2.setTargetPosition(ticks);
        m3.setTargetPosition(-ticks);
        m4.setTargetPosition(ticks);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        int pauseTime = (int) (inches * 100);
        sleep(pauseTime);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }

    public void drivebackward(int inches) {

        int ticks = (int) (inches * (1440 / 35.7));
        m1.setTargetPosition(-ticks);
        m2.setTargetPosition(-ticks);
        m3.setTargetPosition(-ticks);
        m4.setTargetPosition(-ticks);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        int pauseTime = (int) (inches * 100);
        sleep(pauseTime);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
    /*public void forward(int position){

        m1.setTargetPosition(position);
        m2.setTargetPosition(position);
        m3.setTargetPosition(position);
        m4.setTargetPosition(position);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        sleep(3000);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void left(int position){
        m1.setTargetPosition(position);
        m2.setTargetPosition(-position);
        m3.setTargetPosition(position);
        m4.setTargetPosition(-position);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        sleep(3000);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void right(int position){
        m1.setTargetPosition(-position);
        m2.setTargetPosition(position);
        m3.setTargetPosition(-position);
        m4.setTargetPosition(position);
        m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m1.setPower(1);
        m2.setPower(1);
        m3.setPower(1);
        m4.setPower(1);
        sleep(3000);
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void StrafeDrive(String direction,
                            int time, double speed){
        if (direction.equals("left")){
            m1.setPower(speed);
            m2.setPower(-speed);
            m3.setPower(speed);
            m4.setPower(-speed);
            sleep(time);
            m1.setPower(0);
            m2.setPower(0);
            m3.setPower(0);
            m4.setPower(0);
        }
        if (direction.equals("right")){
            m1.setPower(-speed);
            m2.setPower(speed);
            m3.setPower(-speed);
            m4.setPower(speed);
            sleep(time);
            m1.setPower(0);
            m2.setPower(0);
            m3.setPower(0);
            m4.setPower(0);
        }
        sleep(1000);
    }

    public void forwardDrive(String direction,
                             long time, double speed){
        if (direction.equals("forward")){
            m1.setPower(speed);
            m2.setPower(speed);
            m3.setPower(speed);
            m4.setPower(speed);
            sleep(time);
            m1.setPower(0);
            m2.setPower(0);
            m3.setPower(0);
            m4.setPower(0);


        }
        if (direction.equals("back")){
            m1.setPower(-1);
            m2.setPower(-1);
            m3.setPower(-1);
            m4.setPower(-1);
            sleep(1000);
            m1.setPower(0);
            m2.setPower(0);
            m3.setPower(0);
            m4.setPower(0);
        }
        sleep(1000);
    }

    public void rotation(String direction,
                         long time, double speed){
        if (direction.equals("clockwise")){
            m1.setPower(speed);
            m2.setPower(speed);
            m3.setPower(-speed);
            m4.setPower(-speed);
            sleep(time);
        }

        if (direction.equals("counter-clockwise")){
            m1.setPower(-speed);
            m2.setPower(-speed);
            m3.setPower(speed);
            m4.setPower(speed);
            sleep(time);
        }
    }*/



}

