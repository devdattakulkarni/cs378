package main.java;

public class CachingProxy {
	
	public HeaderManager headerMgr = null;
	
	public CachingProxy(HeaderManager hmgr) {
		if (hmgr != null) {
			headerMgr = hmgr;
		}
		else {
			headerMgr = new HeaderManager();
		}
	}
	
	public int calculateCurrentAgeWithGhostObject() {
		int currentAge = -1000;
		HeaderManager mgr = new HeaderManager(); // Ghost object
		
		int dateValue = mgr.getDateValue();
		int responseTime = mgr.getResponseTime();
		int ageValue = mgr.getAgeValue();
		int requestTime = mgr.getRequestTime();
		int now = mgr.getNow();
		
		int apparentAge = max(0, responseTime - dateValue);
		int correctedReceivedAge = max(apparentAge, ageValue);
		int responseDelay = responseTime - requestTime;
		int correctedInitialAge = correctedReceivedAge + responseDelay;
		int residentTime = now - responseTime;
		currentAge = correctedInitialAge + residentTime;
		
		return currentAge;
	}
	
	public int calculateCurrentAge() {
		int currentAge = -1000;
		
		int dateValue = headerMgr.getDateValue();
		int responseTime = headerMgr.getResponseTime();
		int ageValue = headerMgr.getAgeValue();
		int requestTime = headerMgr.getRequestTime();
		int now = headerMgr.getNow();
		
		int apparentAge = max(0, responseTime - dateValue);
		int correctedReceivedAge = max(apparentAge, ageValue);
		int responseDelay = responseTime - requestTime;
		int correctedInitialAge = correctedReceivedAge + responseDelay;
		int residentTime = now - responseTime;
		currentAge = correctedInitialAge + residentTime;
		
		return currentAge;
	}
	
	public int max(int v1, int v2) {
		if (v1 > v2) return v1;
		else return v2;
	}

}
