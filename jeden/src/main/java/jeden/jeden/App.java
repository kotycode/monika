package jeden.jeden;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class App {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("TEeeeST JEDEeeeN JEDEN");

		// get GIPO controller
		final GpioController gpio = GpioFactory.getInstance();

		// create the pin with parameter PinState.HIGH
		// will instantly power up the pin
		final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_01, "PinLEED", PinState.HIGH);
		final GpioPinDigitalOutput pin03 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_02, "PinNLED", PinState.HIGH);
		final GpioPinDigitalOutput pin04 = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_03, "PinLEDD", PinState.HIGH);
		
		System.out.println("Lights ON");

		// wait 2 sec
		Thread.sleep(2000);

		// turn off GPIO /2/3/4
		pin02.low();
		pin03.low();
		pin04.low();
		System.out.println("End of light");

		// wait 2 sec
		Thread.sleep(2000);

		// turn on lights in sequences
		System.out.println("turn on light in sequences");
		pin02.high();
		Thread.sleep(700);
		pin03.high();
		Thread.sleep(800);
		pin04.high();

		// wait 1 sec
		Thread.sleep(1000);

		pin02.low();
		pin03.low();
		pin04.low();

		// wait 1sec
		Thread.sleep(1000);

		// turn on GPIO /2/3/4 and then turn off
		System.out.println("Light is on for 3 sec");
		pin02.pulse(1000, true);
		pin03.pulse(2000, true);
		pin04.pulse(3000, true);

		// release the GPIO controller res
		gpio.shutdown();
	}

}
