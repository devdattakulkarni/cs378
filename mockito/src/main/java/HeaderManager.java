package main.java;

public class HeaderManager {
	
	int dateValue;
	int responseTime;
	int ageValue;
	int requestTime;
	int now;
	
	public HeaderManager() {		
	}
	
	public void setDateValue(int v) {
		dateValue = v;
	}
	
	public int getDateValue() { return dateValue; }
	
	public void setResponseTime(int v) {
		responseTime = v;
	}
	
	public int getResponseTime() { return responseTime; }
	
	public void setAgeValue(int v) {
		ageValue = v;
	}
	
	public int getAgeValue() { return ageValue; }
	
	public void setRequestTime(int v) {
		requestTime = v;
	}
	
	public int getRequestTime() { return requestTime; }
	
	public void setNow(int v) {
		now = v;
	}
	
	public int getNow() { return now; }
}
