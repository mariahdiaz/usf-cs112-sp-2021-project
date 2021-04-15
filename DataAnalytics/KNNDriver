package DataAnalytics;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class KNNDriver {
	public static void main(String[] args) throws IOException {
		
		int k=9;
		Scanner s= new Scanner(System.in);
		System.out.print("Input value for k");
		try {
		k = s.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Mismatch value input error, default value set at "+ k);
		}
	
		Predictor knn= new KNNPredictor(k);
		 ArrayList<DataPoint> myData = knn.readData("titanic.csv");
		 
		Double accuracyTest= knn.getAccuracy(myData);
		
		 Double precisionTest= knn.getPrecision(myData);
			
		
		 SwingUtilities.invokeLater(
	             new Runnable() { public void run() { initAndShowGUI(precisionTest, accuracyTest); } }
	           );
		 
			
	}
	 private static void initAndShowGUI(double precision, double accuracy) {
			
			JFrame myFrame = new JFrame("Data");

			
			Container contentPane = myFrame.getContentPane();
			 contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			 contentPane.add(new Label("Accuracy:"+accuracy));
			 contentPane.add(new Label("Precision:"+precision));
		
			
			myFrame.pack();
			myFrame.setVisible(true);
			
				
		    }


}
