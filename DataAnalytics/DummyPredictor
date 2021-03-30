package DataAnalytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DummyPredictor extends Predictor {

	private Double greenAvg;
	private Double blueAvg;

	@Override
	ArrayList<DataPoint> readData(String filename) throws FileNotFoundException {
		//(f1 f2 label isTest)
		
		ArrayList<DataPoint> allData= new ArrayList<DataPoint>();
		Scanner scanner = new Scanner(new File(filename));
		while (scanner.hasNext()){
			String data = scanner.nextLine();
			String[] dataPoints= data.split(" ");
			DataPoint dp= new DataPoint(Double.parseDouble(dataPoints[0]),
					Double.parseDouble(dataPoints[1]), 
					dataPoints[2], 
					Boolean.parseBoolean(dataPoints[3]));
			allData.add(dp);
		
		}
		DataPoint[] array = allData.toArray(new DataPoint[allData.size()]);
		readData(array);
		return allData;
		
		
	}
	public void readData (DataPoint[] data) {
		Double greenAvg=0.0;
		Double blueAvg=0.0;
		Double sumGreen=0.0;
		Double sumBlue=0.0;
				
		for (int i=0; i<data.length; i++) {
			DataPoint d= data [i];
			Double f=d.getF1();
			String label= d.getLabel();
			
			if (label.equals("Green")) {
				sumGreen+=f;
				greenAvg=sumGreen/data.length;
			}
			else {
				sumBlue+=f;
				blueAvg=sumBlue/data.length;
			}
			
		}
		this.greenAvg=greenAvg;
		this.blueAvg=blueAvg;
		System.out.println("Green Average="+ greenAvg);
		System.out.println("Blue Average="+ blueAvg);
	}

	@Override
	String test(DataPoint data) {
		Double Green=Math.abs(data.getF1()- this.greenAvg);
		Double Blue=Math.abs(data.getF1()-this.blueAvg);
		if (Green<Blue) {
			return ("Green");
		}
		else {
			return ("Blue");
		}
		
	}

	@Override
	Double getAccuracy(ArrayList<DataPoint> data) {
		// TODO Auto-generated method stub
		return 2.0;
	}

	@Override
	Double getPrecision(ArrayList<DataPoint> data) {
		// TODO Auto-generated method stub
		return 1.0;
	}

}
