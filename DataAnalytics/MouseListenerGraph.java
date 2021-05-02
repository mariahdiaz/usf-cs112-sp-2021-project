package DataAnalytics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class MouseListenerGraph implements MouseListener {
	private JLabel myLabel;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//know what the point we clicked on is 
		myLabel.setText("Point:"+"("+ e.getX()+","+e.getY()+")");
		System.out.println(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public JLabel getMyLabel() {
		return myLabel;
	}

	public void setMyLabel(JLabel myLabel) {
		this.myLabel = myLabel;
	}

}
