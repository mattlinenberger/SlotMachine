import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlotMachineApplet extends Applet implements ActionListener {

	private Spinner s1;
	private Spinner s2;
	private Spinner s3;
	private Button stop, stop2, stop3, stomp, play, quit;
	// create image array
	public final int IMAGE_COUNT = 9;
	public Icon[] image = new ImageIcon[IMAGE_COUNT];
	// create labels
	public JLabel s1Display = new JLabel();
	public JLabel s2Display = new JLabel();
	public JLabel s3Display = new JLabel();

	public void init() {

		initImageList();
		s1Display.setIcon(image[0]);
		s2Display.setIcon(image[0]);
		s3Display.setIcon(image[0]);
		createGUI();
		s2 = new Spinner(image, s2Display);
		s3 = new Spinner(image, s3Display);
		s1 = new Spinner(image, s1Display);
		// s1.state = Spinner.WAITING;
		// s2.start();
		// s2.state = Spinner.RUNNING;
		// s3.state = Spinner.RUNNING;
		// s3.start();
	}
	public Button makeButton(String caption, boolean enabled) {
		Button temp = new Button(caption);
		temp.setFont(new Font("Dialog", Font.PLAIN, 20));
		temp.setEnabled(enabled);
		temp.addActionListener(this);
		return temp;
	}
	public void initImageList() {
		for (int k = 0; k < IMAGE_COUNT; k++) {
			image[k] = new ImageIcon(getClass().getResource("image" + k + ".PNG"));
		}
	}
	public void createGUI() {
		this.setSize(600, 600);
		this.setLayout(new GridLayout(3, 3));
		this.add(s1Display);
		this.add(s2Display);
		this.add(s3Display);
		stop = makeButton("STOP", false);
		stop2 = makeButton("STOP", false);
		stop3 = makeButton("STOP", false);
		stomp = makeButton("STOMP", false);
		quit = makeButton("QUIT", false);
		play = makeButton("PLAY", true);
		this.add(stop);
		this.add(stop2);
		this.add(stop3);
		this.add(stomp);
		this.add(play);
		this.add(quit);
	}
	public static void handleSpinner(Spinner spinner, Button button) {
		if (spinner.state == Spinner.RUNNING) {
			spinner.state = Spinner.WAITING;
			button.setEnabled(false);
			return;
		}
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() == play) {
			System.out.println("play pressed");
			play.setEnabled(false);
			stop.setEnabled(true);
			stop2.setEnabled(true);
			stop3.setEnabled(true);
			stomp.setEnabled(true);
			quit.setEnabled(true);
			s1.start();
			s1.state = Spinner.RUNNING;
			s2.start();
			s2.state = Spinner.RUNNING;
			s3.start();
			s3.state = Spinner.RUNNING;
		}
		
		if (e.getSource() == stop) {
			handleSpinner(s1, stop);
			 if (stop2.isEnabled()==false && stop3.isEnabled()==false && stop.isEnabled()==false){
					stomp.setEnabled(false);
					}
			
		}
		if (e.getSource() == stop2) {
			handleSpinner(s2, stop2);
			 if (stop2.isEnabled()==false && stop3.isEnabled()==false && stop.isEnabled()==false){
					stomp.setEnabled(false);
					}
		}
		if (e.getSource() == stop3) {
			handleSpinner(s3, stop3);
			 if (stop2.isEnabled()==false && stop3.isEnabled()==false && stop.isEnabled()==false){
					stomp.setEnabled(false);
					}
		}
		
		
		if(e.getSource() == stomp){
			s1.state = Spinner.WAITING;
			s2.state = Spinner.WAITING;
			s3.state = Spinner.WAITING;
			stop.setEnabled(false);
			stop2.setEnabled(false);
			stop3.setEnabled(false);
			stomp.setEnabled(false);
			play.setEnabled(true);
		}
		if(e.getSource()== quit){
			System.exit(0);
		}
	}
}