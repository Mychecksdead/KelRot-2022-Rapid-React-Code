// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto_two_balls extends SequentialCommandGroup {
    private final DriveBase m_drive;
    private final Joystick js;
    public Auto_two_balls(DriveBase drivo, Joystick JS) {
        m_drive = drivo;
        js = JS;
        addCommands(
            new UseShooters(m_drive),
            new Back(m_drive),
            new UseShooters(m_drive)
        );
    }
}
