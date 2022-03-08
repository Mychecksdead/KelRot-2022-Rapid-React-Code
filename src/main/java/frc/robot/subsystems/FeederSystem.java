// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSystem extends SubsystemBase {
    VictorSPX feedermotor = new VictorSPX(3); //duzelt
    DigitalInput irsensor = new DigitalInput(2); //random

    

    public FeederSystem() {}

    public void feedBall(double output){
        feedermotor.set(ControlMode.PercentOutput, output);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
    public boolean ballFed(){
        return !irsensor.get();
    }
}
