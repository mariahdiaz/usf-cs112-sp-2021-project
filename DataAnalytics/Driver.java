package DataAnalytics;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Driver {

	public static void main(String[] args) throws IOException {
		ArrayList<DataPoint> trainingData= new ArrayList<DataPoint>();
		Random rd= new Random();
		
		File file = new File("TrainingData.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
		FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        File file2 = new File("TestData.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
		FileWriter fw2 = new FileWriter(file2);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        
		
		for (int i=0; i<5; i++) {
			
			Double f1=rd.nextDouble();
			Double f2=rd.nextDouble();
			int chance= rd.nextInt(2);
			String label=null;
			if (chance==1) {
				  label = "Green";
			}
			else {
				label="Blue";
			}
			
			DataPoint dp= new DataPoint(f1, f2, label, false);	
			trainingData.add(dp);
			//TODO write to file 
			bw.write(dp.toString()+"\n");
			
		
			
			
		}
		ArrayList<DataPoint> TestData= new ArrayList<DataPoint>();
		Random rd2= new Random();
		
		
		for (int i=0; i<5; i++) {
			
			Double f1=rd.nextDouble();
			Double f2=rd.nextDouble();
			int chance= rd.nextInt(2);
			String label=null;
			//If DataPoint if from a test data it will not have a label
			
			DataPoint dp2= new DataPoint(f1, f2, label, true);	
			TestData.add(dp2);
			//TODO write to file 
			
			 bw2.write(dp2.toString()+"\n");
			
		}
		 bw.flush();
	     bw.close();
	     bw2.flush();
	     bw2.close();
		
	      Predictor myDummy= new DummyPredictor();
	     myDummy.readData("TrainingData.txt");
	     for (int i=0; i<TestData.size(); i++) {
	    	 String closerPoint=myDummy.test(TestData.get(i));
	    	 System.out.println(closerPoint);
	     }
	    Double  accuracy=myDummy.getAccuracy(TestData);
	     //TODO figure out what this does 
	     Double precision=myDummy.getPrecision(TestData);
	     //TODO figure out what this does 
	     SwingUtilities.invokeLater(
	             new Runnable() { public void run() { initAndShowGUI(precision, accuracy); } }
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
