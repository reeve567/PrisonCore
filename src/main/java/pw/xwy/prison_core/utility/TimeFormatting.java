package pw.xwy.prison_core.utility;

public class TimeFormatting {
	
	public static Time getTime(int seconds) {
		return new Time(seconds);
	}
	
	public static String signFormat(Time time) {
		return "§l" + time.getMinutes() + "m " + time.getSeconds() + "s";
	}
	
	public static String kitFormat(Time time) {
		return "§7" + time.getHours() + "§fh §7" + time.getMinutes() + "§fm";
	}
	
	public static class Time {
		private int hours;
		private int minutes;
		private int seconds;
		
		private Time(int seconds) {
			update(seconds);
		}
		
		public void update(int seconds) {
			hours = seconds / 3600;
			seconds -= hours * 3600;
			minutes = seconds / 60;
			seconds -= minutes * 60;
			this.seconds = seconds;
		}
		
		public int getSeconds() {
			return seconds;
		}
		
		public int getMinutes() {
			return minutes;
		}
		
		public int getHours() {
			return hours;
		}
	}
	
}
