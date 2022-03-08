// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class Back extends CommandBase {
    private final DriveBase m_drive;
    double backP, backI, backD;
    PIDController backPID;
    double distanceToBack = 120; //centimeters
    public Back(DriveBase drivo) {
        m_drive = drivo;
        addRequirements(m_drive);
    }

    
    @Override
    public void initialize() {
        m_drive.resetEncoder();
    } 

    
    @Override
    public void execute() {
        double d = backPID.calculate(-m_drive.getDistance(), distanceToBack);
        m_drive.arcadeDrive(d, 0);
        if(Math.abs(-m_drive.getDistance() - distanceToBack) < 50){
            m_drive.intakeRun();
        }
        System.out.print("DISTANCE: ");
        System.out.println(m_drive.getDistance());
    }

    
    @Override
    public void end(boolean interrupted) {}

    
    @Override
    public boolean isFinished() {
        return false;
        //until first ar is on
    }
}
