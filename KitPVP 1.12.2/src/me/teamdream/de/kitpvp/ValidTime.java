package me.teamdream.de.kitpvp;

public class ValidTime {
	
	/*
	 * 
	 * Diese Klasse dient dazu,
	 * Zeiten abzuspeichern und zu vergleichen
	 * 
	 */
	
	private boolean isInfinite = false;
	
	private int day = 0;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private String createDate = "";
	
	public ValidTime(int day, int hour, int minute, int second, String createDate) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.createDate = createDate;
	}
	public ValidTime(boolean isInfinite) {
		this.isInfinite = isInfinite;
		if(isInfinite) {
			this.day = Integer.MAX_VALUE;
			this.hour = Integer.MAX_VALUE;
			this.minute = Integer.MAX_VALUE;
			this.second = Integer.MAX_VALUE;
			this.createDate = "INFINITE";
		}
	}
	public ValidTime() {}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public String getCreateDate() {
		return createDate;
	}
	
	public boolean isInfinite() {
		return isInfinite;
	}
	
	public void setInfinite(boolean isInfinite) {
		this.isInfinite = isInfinite;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void loadValidTime(String timeStamp) {
		/*
		 * 
		 * MUSS GEMACHT WERDEN
		 * 
		 */
	}
	
	public boolean compareTime(String timeStamp) {
		/*
		 * 
		 * Gibt TRUE zurück, wenn die vorgegebene Zeit vorbei ist
		 * Gibt FALSE zurück, wenn die vorgegeben Zeit nicht vorbei ist
		 * 
		 */
		
		return false;
	}
	
}
