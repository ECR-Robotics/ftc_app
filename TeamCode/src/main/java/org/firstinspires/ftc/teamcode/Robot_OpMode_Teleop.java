package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Team A Robot Teleop", group="Robot")
//@Disabled
public class Robot_OpMode_Teleop extends OpMode {

    /* Declare OpMode members. */
    RobotTeamA robot       = new RobotTeamA(); // this class defines our Robot's hardware configuration
    // Below are a few variables that we'll use in this OpMode
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
// Old code - but what happens if we just try it.
    /*  double fRight = gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
      double  bRight = gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
      double fLeft = gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
      double bLeft = gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x;
      //double liftUp = gamepad1.right_trigger - gamepad1.left_trigger;

        robot.leftfrontDrive.setPower(fLeft);
        robot.rightfrontDrive.setPower(fRight);
        robot.leftbackDrive.setPower(bLeft);
        robot.rightbackDrive.setPower(bRight);
*/
//New Code

    double right = gamepad1.left_stick_y - gamepad1.left_stick_x;
    double left = gamepad1.left_stick_y + (0.1 * gamepad1.left_stick_x);
    double front = gamepad1.right_stick_x;
    double back = -front;



        if ((gamepad1.left_stick_x < 0.1 && gamepad1.left_stick_x > -0.1) && (gamepad1.left_stick_y < 0.1 && gamepad1.left_stick_y > -0.1) ) {

            robot.leftfrontDrive.setPower(front);
            robot.leftbackDrive.setPower(back);
            robot.rightbackDrive.setPower(-back);
            robot.rightfrontDrive.setPower(-front);
  }
  else {
            robot.leftfrontDrive.setPower(left);
            robot.leftbackDrive.setPower(left);
            robot.rightfrontDrive.setPower(right);
            robot.rightbackDrive.setPower(right);
        }
        if (gamepad1.a) { robot.lift.setPower(robot.lift.getPower() + 0.1); }

        else if (gamepad1.b) {robot.lift.setPower(robot.lift.getPower() - 0.1);}

        else robot.lift.setPower(0);

     /*\\   if (gamepad1.x) {
            robot.leftarm_servo.setPosition(0);
            robot.rightarm_servo.setPosition(1);
        }
        else if (gamepad1.y) {
            robot.rightarm_servo.setPosition(0);
            robot.leftarm_servo.setPosition(1);
        } */

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}

