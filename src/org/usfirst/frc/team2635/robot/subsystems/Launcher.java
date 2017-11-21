package org.usfirst.frc.team2635.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Launcher extends Subsystem {

	CANTalon talon;
	
	double p;
	double i;
	double d;
	double f;
	double setpoint;
	double output0;
	double output1;
	
	double countsPer100ms;
	double fullscaleToPercentBus;
	
	double loadSpeedNU;
	double setpointNU;
	double error;
	double pCmd;
	double fCmd;
	double motorCmdUnlimited;
	double motorCmdPercentBus;
	double sumAll;
	
	public Launcher() {
		countsPer100ms = 6.8264;
		fullscaleToPercentBus = 9.775E-4;
		
	}

	public void setPIDF(double p, double i, double d, double f) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.f = f;
	}
	
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double calculateSpeed(double speed) {
		loadSpeedNU = speed * countsPer100ms;
		setpointNU = setpoint * countsPer100ms;
		error = setpointNU - loadSpeedNU;
		pCmd = error * p;
		fCmd = setpointNU * f;
		
		sumAll = fCmd + pCmd;
		
		motorCmdUnlimited = sumAll * fullscaleToPercentBus;
		
		if(motorCmdUnlimited > 1.0) {
			motorCmdPercentBus = 1;
		} else if(motorCmdUnlimited < -1.0) {
			motorCmdPercentBus = -1;
		} else {
			motorCmdPercentBus = motorCmdUnlimited;
		}
		
		return motorCmdPercentBus;
	}
	
//	public void getOutput() {
//		output0 = talon0.getSpeed();
//		output1 = talon1.getSpeed();
//		
//		System.out.println("Talon0 Speed: " + output0 +". Talon1 Speed: " + output1 + ".");
//	}
	
	
	
	
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	
}

