package DataAnalytics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class RunButtonListener implements ActionListener {
	private JSlider myJSlider;
	private  JFrame frame;
	@Override
	public void actionPerformed(ActionEvent e) {
		int value= myJSlider.getValue();
		int variable= ((value*2)+1);
		try {
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) ;
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			Graph.createAndShowGui(variable);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public JSlider getMyJSlider() {
		return myJSlider;
	}
	public void setMyJSlider(JSlider myJSlider) {
		this.myJSlider = myJSlider;
	}
	public JFrame getMyFrame(JFrame frame) {
		return frame;
	}
	public void setMyFrame(JFrame frame) {
		this.frame=frame;
	}

}
