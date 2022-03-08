// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

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
    public Auto_two_balls(DriveBase drivo, Intake intoke, Shooter shoter) {
        m_drive = drivo;
        m_intake = intoke;
        m_shooter = shoter;
        addCommands(
            new UseShooters(m_drive, m_shooter),
            new Back(m_drive, m_intake, 120),
            new UseShooters(m_drive, m_shooter)
        );
    }
}
