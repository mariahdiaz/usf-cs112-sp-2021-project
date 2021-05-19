package DataAnalytics;

public class DataPoint {
	private Double f1;
	private Double f2;
	private Double f3;
	private Double f4;
	private String label;
	private Boolean isTest;
	
	public DataPoint(Double f1, Double f2, String label, Boolean isTest) {
		this.f1=f1;
		this.f2=f2;
		setLabel(label);
		setIsTest(isTest);
	}
	
	public DataPoint () {
		 f1=0.0;
		 f2=0.0;
		 label=null;
		 isTest=true;
	}
	
	
	public DataPoint(Double f1, Double f2, Double f3, Double f4, String label, Boolean isTest) {
		setF1(f1);
		setF2(f2);
		setF3(f3);
		setF4(f4);
		setLabel(label);
		setIsTest(isTest);
	}

	
	
	public Double getF1() {
		return this.f1;
	}
	public Double getF2() {
		return this.f2;
	}
	public Double getF3() {
		return this.f3;
	}
	public Double getF4() {
		return this.f4;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Boolean isTest() {
		return this.isTest;
	}
	
	public void setF1(Double f1) {
		
		this.f1=f1;
	}
	
	public void setF2(Double f2) {
		
		this.f2=f2;
	}
	public void setF3(Double f3) {
		
		this.f3=f3;
	}
	
	public void setF4(Double f4) {
		
		this.f4=f4;
	}
	
	public void setLabel(String label) {
		if (label==null ||(!(label.equals("0")|| label.equals("1")))) {
			return;
		}
		this.label=label;
	}
	public void setIsTest(Boolean isTest) {
		this.isTest=isTest;
	}
	public String toString() {
		return (f1 +" "+ f2+" " +f3+ " "+f4+ " "+ label+" " + isTest);
		
	}
}
