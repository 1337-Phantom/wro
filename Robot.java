import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class Robot
{
	private double raddurchmesser;
	private double radabstand;
	private double radumfang;
	EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2);
	SampleProvider gyrodegrees = gyroSensor.getAngleMode();

	public Robot()
	{
		raddurchmesser = 5.6;
		radabstand = 14;
		radumfang = Math.PI * raddurchmesser;
		Motor.B.setSpeed(180);
		Motor.C.setSpeed(180);
	}

	public Robot(double raddurchmesser, double radabstand)
	{
		this.raddurchmesser = raddurchmesser;
		this.radabstand = radabstand;
		radumfang = Math.PI * raddurchmesser;
		Motor.B.setSpeed(180);
		Motor.C.setSpeed(180);
	}

	public void drive(double distance)
	{
		double umdrehungen = distance / radumfang;
		Motor.B.rotate((int) (Math.round(umdrehungen * 360)), true);
		Motor.C.rotate((int) (Math.round(umdrehungen * 360)), false);
	}

	public void rotate(int degrees)
	{
		double eineUmdrehungRobot = Math.PI * radabstand;
		double umdrehungenRad = eineUmdrehungRobot / radumfang;
		double radRotation = umdrehungenRad * 360;
		double fraction = 1.0 * degrees / 360;
		int grad = (int) (radRotation * fraction);
		Motor.C.rotate(grad, true);
		Motor.B.rotate(-1 * grad, false);
	}
	public int getAngle () {
		
		
		float[] sample = new float[gyrodegrees.sampleSize()];
		gyrodegrees.fetchSample(sample, 0);
		int value = (int)sample[0];
		return value;
				
	}
	
	public void gyroRotatecc(int angle) {
		int richtunganfang = getAngle();
		int aktuelleRichtung = richtunganfang;
		while (aktuelleRichtung < richtunganfang + 90) {
			aktuelleRichtung = getAngle();
			Motor.C.rotate(-10, true);
			Motor.B.rotate(10, false);
		}
		}
		
	public void gyroRotateccl(int angle) {
		int richtunganfang = getAngle();
		int aktuelleRichtung = richtunganfang;
		while (aktuelleRichtung <= (richtunganfang + angle - 16.1)) {
			aktuelleRichtung = getAngle();
			LCD.drawString("" + aktuelleRichtung, 3, 3);
			LCD.drawString("" + richtunganfang, 3, 4);
			Motor.B.backward();
			Motor.C.forward();
		}
		Motor.B.rotate(0);
		Motor.C.rotate(0);
	}
	public void gyroRotateccr(int angle) {
		int richtunganfang = getAngle();
		int aktuelleRichtung = richtunganfang;
		while (aktuelleRichtung >= (richtunganfang - angle + 17.5)) {
			aktuelleRichtung = getAngle();
			LCD.drawString("" + aktuelleRichtung, 3, 3);
			LCD.drawString("" + richtunganfang, 3, 4);
			Motor.C.backward();
			Motor.B.forward();
		}
	Motor.B.rotate(0);
	Motor.C.rotate(0);
	}
}
