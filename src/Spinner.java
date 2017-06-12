import java.awt.*;
import javax.swing.*;

public class Spinner extends Thread  {
	// state values made public so other threads can access
	public static final int BORN = 0;
	public static final int RUNNING = 1;
	public static final int WAITING = 2;
	public static final int DEAD = 3;
	// state variable public so other threads can access
	// made volatile so multiple threads access it with mutual exclusion
	public volatile int state;
	// internal instance variables
	private int counter;
	private int delay;
	// Instance Variables for the image array and display
	private Icon[] image;
	private JLabel display;

	public Spinner(Icon[] pics, JLabel lbl) {
		// Copy parameters to the instance variables
		image = pics;
		display = lbl;
		// set counter and .25 to 1 sec., randomly selected, delay
		counter = 0;
		delay = (int) (Math.random() * 20) + 100;
		// set state to BORN
		state = BORN;

	}

	@Override
	public synchronized void run() {
		try // Thread.sleep can throw InterruptedException
		{
			while (state != DEAD) {
				counter++; // increment counter display image

				if (counter == image.length) {
					counter = 0;
				}

				display.setIcon(image[counter]);
				Thread.sleep(delay); // sleep

				// not supposed to be running
				if (state != RUNNING) {
					// wait for state change

					while (state != RUNNING) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) { // this will probably never happen
											// but we can't use a
											// throws construct on run because
											// we're morphing it
			System.err.println("Thread's sleep interrupted");
		}
	}

}