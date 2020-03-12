import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;


public class RunWRO
{
	public static EV3 ev3 = (EV3) BrickFinder.getLocal();
	public static Robot robbie = new Robot();
	public static Keys keys = ev3.getKeys();
	public static int evacStat = 1;
	public static int haeuserStat=1;

	public static void main(String[] args)
	{
		

		evacStat = getUserChoice(1, "Mod. Evak: ", 3);
		
		
		LCD.drawString("Evac stat: " + evacStat, 0, 0);
		
		sleep(3);
		
		if(evacStat==1)
		{
			LCD.drawString("gegenüber", 0, 0);
			driveConfig1();
		}else if (evacStat == 2) {
			LCD.drawString("nebeneinander", 0, 0);
			driveConfig2();
		}else if (evacStat == 3) {
			LCD.drawString("diagonal", 0, 0);
			driveConfig3();
		}
	}
	
	//gegenüber
	public static void driveConfig1()  {
		robbie.drive(27);
		robbie.gyroRotateccl(90);
		robbie.drive(40);
		robbie.drive(-7);
		robbie.gyroRotateccr(180);
		robbie.drive(75);
		
		
	}
	//nebeneinander
	public static void driveConfig2() {
		robbie.drive(27);
		robbie.gyroRotateccl(90);
		robbie.drive(40);
		robbie.drive(-7);
		robbie.gyroRotateccr(180);
		robbie.drive(33);
		robbie.gyroRotateccr(90);
		robbie.drive(100);
		robbie.gyroRotateccr(90);
		robbie.drive(40);
		robbie.drive(-7);
		robbie.gyroRotateccr(180);
		robbie.drive(40);
		
		
	}
	//diagonal
	public static void driveConfig3() {
		robbie.gyroRotateccr(180);
		
	}
	


	private static int getUserChoice(int init, String str, int max)
	{
		int state=init;
		int result;
		while(true)
		{
			
			LCD.clear();
			LCD.drawString(str + state, 0, 0);	
			keys.waitForAnyPress();
			if (Button.RIGHT.isDown())
			{
				state=state+1;
				if(state>max)
				{
					state=state-max;
				}
			}
			else if (Button.LEFT.isDown())
			{

				state=state-1;
				if(state<1)
				{
					state=state+max;
				}
			}
			else if (Button.ENTER.isDown())
			{
				result=state;
				break;
			}
			
		}
		LCD.clear();
		return result;
	}

	private static void test1()
	{
		robbie.drive(15);
		robbie.rotate(90);
	}

	private static void test2()
	{
		robbie.rotate(-90);
		robbie.drive(15);
	}

	public static void sleep(double seconds)
	{
		try
		{
			Thread.sleep(((long) seconds * 1000));
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
