// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

public class UseShooters extends CommandBase {
    
    double[] encvalues;
    private final DriveBase m_drive;
    private final Shooter m_shooter;
    private final double m_output;
    double distToHub, rpm_setpoint;
    public UseShooters(DriveBase drivo, Shooter shoter, double output) {
        m_drive = drivo;
        m_shooter = shoter;
        m_output = output;
        addRequirements(m_drive, m_shooter);
    }

    
    @Override
    public void initialize() {
        //distToHub = -1;
        /*for(int i = 0; i < 10; ++i){
            double dist = m_shooter.getDistanceToHub();
            if(distToHub == -1 && dist != -1){
                distToHub = dist;
                break;
            }
        }
        //rpm_setpoint = ;*/
        //distToHub = 220;
        m_shooter.resetEncoders();
        encvalues = m_shooter.getEncoderRate();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_shooter.controlShooter(m_output);
        m_shooter.encoderTest();
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