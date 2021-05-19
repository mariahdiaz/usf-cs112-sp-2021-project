

package DataAnalytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KNNPredictor extends Predictor {

	
	private int k;
	private int a;
	// number of passengers who survived
	private int b;
	// number of passengers who died
	private ArrayList<DataPoint> allPoints;
	
	private int tsd;
	//Training data size
		public KNNPredictor(int k) {
		this.k=k;
		this.a=0;
		this.b=0;
	
	}
		private double getDistance(DataPoint p1, DataPoint p2) {
			double x=p1.getF1()-p2.getF1();
			double y=p1.getF2()-p2.getF2();
			
			double xy=(x*x)+(y*y);
			double d=Math.sqrt(xy);
			return d;
		}
	
	public static  int rollDice(int number, int nSides)
    { 
        int num = 0;
        int roll = 0;
        Random  r = new Random(); 
        if(nSides >=3) 
        { 
            for(int i = 0; i < number; i++)
            { 
                roll = r.nextInt(nSides)+1;
               // System.out.println("Roll is:  "+roll);
                num = num + roll; 
            } 
        } 
        else
        { 
            //System.out.println("Error num needs to be from 3"); 
        } 
        return num;  
    } 
	@Override
	
	ArrayList<DataPoint> readData(String filename) throws FileNotFoundException {
		//pclass,survived,name,sex,age,fare
		//1,1,"Allen, Miss. Elisabeth Walton",female,29,211.3375
		ArrayList<DataPoint> allData= new ArrayList<DataPoint>();
		Scanner scanner = new Scanner(new File(filename));
		scanner.nextLine();
		
		while (scanner.hasNext()){
			String data = scanner.nextLine();
			String[] dataPoints= data.split(",");
			if (!dataPoints[5].equals("")) {
					
				int diceRoll= rollDice(1, 10);
				boolean isTest=true;
				String label=dataPoints[1];
				//If <=9 this is training data 
				if (diceRoll<=9) {
					isTest=false;
					tsd++;
					if (label.equals("0")){
						b++;
					}
					else {
						a++;
					}
					
				}
			
				
				DataPoint dp= new DataPoint(Double.parseDouble(dataPoints[5]),
						Double.parseDouble(dataPoints[6]), 
						label, 
						isTest);
				allData.add(dp);
				
			}
		
		
		}
		System.out.println("Number of passengers who survived "+a);
		
		System.out.println("Number of passengers who died "+b);
		
		allPoints=allData;
		return allData;
		
		
	}
	public ArrayList<DataPoint> getTrainingData(){
		ArrayList<DataPoint> trainingData= new ArrayList<DataPoint>();
		for(int i=0; i<allPoints.size(); i++) {
			DataPoint otherPoint= allPoints.get(i);
			if (!otherPoint.isTest()) {
				trainingData.add(otherPoint);
			}
			
		}
		return trainingData;
		
	}
	public ArrayList<DataPoint> getTestData(){
		ArrayList<DataPoint> testData= new ArrayList<DataPoint>();
		for(int i=0; i<allPoints.size(); i++) {
			DataPoint otherPoint= allPoints.get(i);
			if (otherPoint.isTest()) {
				testData.add(otherPoint);
			}
			
		}
		return testData;
		
	}
		
		
	
	

	@Override
	String test(DataPoint data) {
		
		if (data.isTest()) {
			Double[][] distanceArray= new Double [tsd][2];
			int arrayIndex=0;
			for (int i=0; i<allPoints.size(); i++) {
				DataPoint otherPoint= allPoints.get(i);
				if (!otherPoint.isTest()) {
					double distance=getDistance(data, otherPoint);
					Double[] dArray= {distance, Double.parseDouble(otherPoint.getLabel())};
					distanceArray[arrayIndex]=dArray;
					arrayIndex++;
				}
			}
			java.util.Arrays.sort(distanceArray,new java.util.Comparator<Double[]>(){
				public int compare(Double[]a,Double[]b){
					return a[0].compareTo(b[0]);
					}
				});
			int dead=0;
			int survived=0;
			for(int i=0; i<k; i++) {
				String label=distanceArray[i][1]+"";
	
				if (label.equals("0.0")){
						dead++;
						
						
				}
				else { survived++;
				

				}
			}
			if (dead>survived) {
				return "0";
				
			}
			else return "1";
		}
		
		return null;
	}

	double[] calcHelper(ArrayList<DataPoint> data) {
		double truePositive=0;
		double falsePositive=0;
		double falseNegative=0;
		double trueNegative=0;
		for (int i=0; i<allPoints.size(); i++) {
			DataPoint otherPoint= allPoints.get(i);

			if(otherPoint.isTest()) {
				String label=this.test(otherPoint);
				if (label.equals("1")& otherPoint.getLabel().equals("1") ) {
					truePositive++;
					
					
				}
				if (label.equals("1")& otherPoint.getLabel().equals("0") ) {
					falsePositive++;
					
				}
				if (label.equals("0")& otherPoint.getLabel().equals("1") ) {
					falseNegative++;
					
				}
				if (label.equals("0")& otherPoint.getLabel().equals("0") ) {
					trueNegative++;
					
				}
				
			}
		}
		double [] arrayNew={truePositive, falsePositive, falseNegative, trueNegative};
		return arrayNew;
	}
	@Override
	Double getAccuracy(ArrayList<DataPoint> data) {
		double[] newArray=calcHelper(data);
		double truePositive=newArray[0];
		double falsePositive=newArray[1];
		double falseNegative=newArray[2];
		double trueNegative=newArray[3];
		
		

		return ((truePositive + trueNegative) / (truePositive + 
				trueNegative + falsePositive + falseNegative));
	}

	@Override
	Double getPrecision(ArrayList<DataPoint> data) {
		double[] newArray=calcHelper(data);
		double truePositive=newArray[0];
		double falsePositive=newArray[1];
		double falseNegative=newArray[2];
		double trueNegative=newArray[3];
		
		return (truePositive/(truePositive+falseNegative));
	}

}
