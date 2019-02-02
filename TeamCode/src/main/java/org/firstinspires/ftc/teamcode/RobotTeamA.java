package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotTeamA
{
    // Declaring the motors! This is where we name the motors and initialize them to be "null",
    // which is to make sure that they contain NOTHING before we work with them.
    // The "DcMotor" Data Types are for the wheels.
    /* 0 - rightback_drive
    leftback_drive
    leftfront_drive
    3- rightfront_drive
    */
    public DcMotor  leftfrontDrive   = null;
    public DcMotor  rightfrontDrive  = null;
    public DcMotor  leftbackDrive = null;
    public DcMotor  rightbackDrive = null;

    public DcMotor  lift = null;
    public Servo leftarm_servo = null;
    public Servo rightarm_servo = null;
    public Servo symbolServo = null;

    // The "Servo" Data Types are for the "arm" mechanisms.
    //  public Servo    leftClaw    = null;
    // public Servo    rightClaw   = null;

    public Servo    armServo     = null;

    // These are some crazy lines of code coming up. Here's what the following things are:
    // public -> this is a modifier. "public" means that anyone can see it.
    // static -> this is a modifier. "static" means that this can be used anywhere--even outside of the "Robot" class!
    // final  -> this is a modifier. "final" means that this variable, when it is first assigned to, can NEVER change.
    // double -> this is the data type. "double" means that the variable we are creating will be a decimal.
    // MID_SERVO, ARM_UP_POWER, etc... -> this is the variable name. This is what we will use to reference this variable.
    // = 0.5, 0.45, etc... -> this is the value we are assigning.
    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    // The HardwareMap object, which we're calling "hwMap," will be where we get the references to the
    // actual, physical DcMotors and Servos on the robot.
    HardwareMap hwMap           = null;

    // The ElapsedTime object, which we're calling "period," will be what tracks the time that the robot
    // is running (for example, the autonomous period of the robot will be 1 minute and 30 seconds--this is
    // going to keep track of that, so that we don't go over time.
    ElapsedTime period  = new ElapsedTime();

    /* Constructor for the robot--we don't have anything here, because the app is handling the Robot initialization. */
    public RobotTeamA(){

    }

    // So, take a look through the rest of the code--if you have any questions, you can ask me (Corey) or
    // Mr. Steen about what something means, or you can try and figure it out yourself. Try figuring it out
    // yourself first to see if you can make sense of it. It's OK if you can't.

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftfrontDrive  = hwMap.get(DcMotor.class,"leftfront_drive");
        rightfrontDrive = hwMap.get(DcMotor.class,"rightfront_drive");
        rightbackDrive = hwMap.get(DcMotor.class,"rightback_drive");
        leftbackDrive = hwMap.get(DcMotor.class,"leftback_drive");
        symbolServo = hwMap.get(Servo.class, "symbol_servo");
        // leftarm_servo = hwMap.get(Servo.class, "leftarm_servo");
        // rightarm_servo = hwMap.get(Servo.class, "rightarm_servo");
        lift = hwMap.get(DcMotor.class, "lift");

        //  arm = hwMap.get(DcMotor.class,"arm");

        //    liftDrive = hwMap.get(DcMotor.class, "lift_drive");
        //    armServo = hwMap.get(Servo.class, "arm");

        //  arm = hwMap.get(DcMotor.class,"arm");


        leftfrontDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightfrontDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftbackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightbackDrive.setDirection(DcMotor.Direction.REVERSE);
        lift.setDirection(DcMotor.Direction.FORWARD);
        // leftarm_servo.setDirection(Servo.Direction.FORWARD);
        // rightarm_servo.setDirection(Servo.Direction.FORWARD);
        symbolServo.setDirection(Servo.Direction.FORWARD);


        // Set all motors to zero power
        leftfrontDrive.setPower(0);
        rightfrontDrive.setPower(0);
        leftbackDrive.setPower(0);
        rightbackDrive.setPower(0);
        //lift.setPower(0);
        // leftarm_servo.setPosition(0);
        // rightarm_servo.setPosition(0);
        symbolServo.setPosition(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftfrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftbackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightbackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize ALL installed servos.
    }

    void move_straight(double speed) {
        rightfrontDrive.setPower(speed);
        leftfrontDrive.setPower(speed);
        leftbackDrive.setPower(speed);
        rightbackDrive.setPower(speed);
    }

    void strafeLeft (double speed) {
        rightfrontDrive.setPower(-speed);
        rightbackDrive.setPower(speed);
        leftfrontDrive.setPower(speed);
        leftbackDrive.setPower(-speed);
    }

    void strafeRight (double speed) {
        rightfrontDrive.setPower(speed);
        rightbackDrive.setPower(-speed);
        leftfrontDrive.setPower(-speed);
        leftbackDrive.setPower(speed);
    }

    void turnRight (double speed) {
        leftfrontDrive.setPower(-speed);
        leftbackDrive.setPower(-speed);
        rightbackDrive.setPower(speed);
        rightfrontDrive.setPower(speed);
    }
    void turnLeft (double speed) {
        leftfrontDrive.setPower(speed);
        leftbackDrive.setPower(speed);
        rightbackDrive.setPower(-speed);
        rightfrontDrive.setPower(-speed);
    }

    void turn_by_degree(int direction, double speed, double seconds_to_turn) {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        while (runtime.seconds() < seconds_to_turn) {
            // set the appropriate motors to speed during the loop
            rightfrontDrive.setPower(direction * speed * -1);
            leftfrontDrive.setPower(direction * speed);
            rightbackDrive.setPower(direction * speed * -1);
            leftbackDrive.setPower(direction * speed);
        }

    }
    void turnNinetyRight(ElapsedTime runtime) {
        runtime.reset();
        while (runtime.seconds() < 0.85) {
            turnRight(0.5);
        }

    }

    void turnNinetyLeft(ElapsedTime runtime) {
        runtime.reset();
        while (runtime.seconds() < 0.85) {
            turnRight(-0.5);
        }

    }
    void dropSymbol () {
        symbolServo.setPosition(0);
    }


    void stop(){
        move_straight(0);
    }

    void wait(ElapsedTime runtime, double seconds) {
        while (runtime.seconds() < seconds) { stop(); }
    }
}