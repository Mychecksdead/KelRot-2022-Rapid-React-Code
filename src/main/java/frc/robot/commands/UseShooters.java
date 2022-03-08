// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class UseShooters extends CommandBase {
    DriveBase m_drive;
    double distToHub, rpm_setpoint;
    public UseShooters(DriveBase drivo) {
        m_drive = drivo;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        distToHub = -1;
        for(int i = 0; i < 10; ++i){
            double dist = m_drive.getDistanceToHub();
            if(distToHub == -1 && dist != -1){
                distToHub = dist;
                break;
            }
        }
        rpm_setpoint = m_drive.best_rpm_for_distance(distToHub);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        //2. ar off olana kadar
    }
}