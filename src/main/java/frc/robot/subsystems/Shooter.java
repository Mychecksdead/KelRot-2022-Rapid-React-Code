package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {

    VictorSPX motor1= new VictorSPX(ShooterConstants.motor1);
    VictorSPX motor2 = new VictorSPX(ShooterConstants.motor2);
    Encoder enc1= new Encoder (ShooterConstants.encoderPort1[0],ShooterConstants.encoderPort1[1]); //DEĞERLERI DÜZELT KOD PATLAMASIN DİYE KOYDUM
    Encoder enc2= new Encoder (ShooterConstants.encoderPort2[0],ShooterConstants.encoderPort2[1]);
    SimpleMotorFeedforward feedforward= new SimpleMotorFeedforward(0, 0);
    PIDController pcontroltop = new PIDController(0.0, 0.0, 0.0);
    PIDController pcontrolbottom = new PIDController(0.0, 0.0, 0.0);
    DriveBase m_drive;
    
    public Shooter(DriveBase drivo) {
        m_drive = drivo;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public double[] getEncoderRate(){
        double[] encoderValues= {enc1.getRate(),enc2.getRate()};
        return encoderValues;
    }

    public void useShooter(double setpoint) {
        double topoutput= feedforward.calculate(setpoint)+ pcontroltop.calculate(enc1.getRate(),setpoint);
        double bottomoutput= feedforward.calculate(setpoint)+ pcontrolbottom.calculate(enc1.getRate(),setpoint);

        motor1.set(ControlMode.PercentOutput,topoutput);
        motor2.set(ControlMode.PercentOutput,bottomoutput);
    }

    public boolean atSetpoint(){
        return pcontroltop.atSetpoint() && pcontrolbottom.atSetpoint();
    }

    public void encoderTest(){
        SmartDashboard.putNumber("üst", enc1.getRate());
        SmartDashboard.putNumber("alt", enc2.getRate());
    }
}