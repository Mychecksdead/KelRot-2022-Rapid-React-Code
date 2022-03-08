// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto_two_balls extends SequentialCommandGroup {
    private final DriveBase m_drive;
    private final Intake m_intake;
    private final Shooter m_shooter;
    private final PhotonCamera m_camera;
    public Auto_two_balls(DriveBase drivo, Intake intoke, Shooter shoter, PhotonCamera cam) {
        m_drive = drivo;
        m_intake = intoke;
        m_shooter = shoter;
        m_camera = cam;
        addCommands(
            new TargetAlign(m_drive, m_camera),
            new UseShooters(m_drive, m_shooter, 0),
            new Back(m_drive, m_intake, 120),
            new TargetAlign(m_drive, m_camera),
            new UseShooters(m_drive, m_shooter, 0)
        );
    }
}
