// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.PIDValues;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.math.util.Units;

public class DriveBase extends SubsystemBase {
    /** Creates a new DriveBase. */
    ADIS16470_IMU gyro= new ADIS16470_IMU();
    Encoder enc = new Encoder(DriveConstants.encoderPorta , DriveConstants.encoderPortb);

    Talon frontLeft = new Talon(DriveConstants.solOn);
    Talon backLeft = new Talon(DriveConstants.solArka);
    Talon frontRight = new Talon(DriveConstants.sagOn);
    Talon backRight = new Talon(DriveConstants.sagArka);

    MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

    DifferentialDrive drive = new DifferentialDrive(left, right);

    PIDController drivepid = new PIDController(PIDValues.DrivekP , PIDValues.DrivekI , PIDValues.DrivekD);

    PhotonCamera cam;
    public double targetAlign = -1;

    public DriveBase(PhotonCamera came) {
        cam = came;
        frontRight.setInverted(true);
        backRight.setInverted(true);
        gyro.setYawAxis(IMUAxis.kZ);
    }

    @Override
    public void periodic() {
      
    }

    public void curvatureDrive(Joystick js){
        drive.curvatureDrive(-js.getRawAxis(1),js.getRawAxis(4),js.getRawButton(5));
    }

    public void arcadeDrive(double speed, double rotation){
        drive.arcadeDrive(speed, rotation);
    }

    public double getAngle(){
        return -gyro.getAngle();
    }

    public double getDistanceToHub(){
        var result = cam.getLatestResult();
        if(result.hasTargets()){
            return PhotonUtils.calculateDistanceToTargetMeters(
                VisionConstants.CAMERA_HEIGHT_METERS,
                VisionConstants.TARGET_HEIGHT_METERS,
                VisionConstants.CAMERA_PITCH_RADIANS,
                Units.degreesToRadians(result.getBestTarget().getPitch()));
        }
        return -1;
    }

    public double best_rpm_for_distance(double distance){
        return 3169;
    }

    public void resetGyro(){
        gyro.reset();
    }

    public void intakeRun(){
        
    }

    public double getDistance(){
        return enc.getDistance();
    }

    public void resetEncoder(){
        enc.reset();
    }

}
