package cn.wang;






public class CalculateFieldsOfCalculation {
	
	private long shots;
	private long hits;
	private long kills;
	private long deaths;
	private long successful_missions;
	private long missions;
	private long defend_events;
	private long successful_defend_events;
	private long attack_events;
	private long successful_attack_events;
	private long accidentals;

	
	public CalculateFieldsOfCalculation(long time, long shots, long hits, long kills,
			long deaths, long successful_missions, long missions,
			long defend_events, long successful_defend_events,
			long attack_events, long successful_attack_events, long accidentals) {
		super();
		this.shots = shots;
		this.hits = hits;
		this.kills = kills;
		this.deaths = deaths;
		this.successful_missions = successful_missions;
		this.missions = missions;
		this.defend_events = defend_events;
		this.successful_defend_events = successful_defend_events;
		this.attack_events = attack_events;
		this.successful_attack_events = successful_attack_events;
		this.accidentals = accidentals;
	}
	
	public long calculateAccuracy() {
		
		long accuracy = -1L;
		if (hits == 0 || shots == 0) {
			return accuracy = 0;
		} else {
			return accuracy = hits * 100 / shots;
		}
	}
	
	

	public String calculateSnapshot(long seconds) {
	
		 String date = null;
		 date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(seconds * 1000));
		 return date;
		
	}
	public long calculateKillDeathRate() {
		long kill_death_rate = -1L;
		if (kills == 0 || deaths == 0) {
			return kill_death_rate = 0;
			
		}else {
			return kill_death_rate = deaths * 100 / kills;
		}
		
	}
	
	public long calculateAccidentalKills() {
		long accidental_kills = -1L;
		if (kills == 0 || accidentals == 0) {
			return accidental_kills = 0;
		}else {
			return accidental_kills = accidentals * 100 / kills;
		}
	}
	
	public long calculateSuccessfulMissionPercentage() {
		long mission_percentage = -1L;
		if(successful_missions == 0 || missions == 0) {
			return mission_percentage = 0;
		}else {
			return mission_percentage = successful_missions * 100 / missions;
		}
	}
	
	public long successfulDefendPercentage() {
		
		long defend_percentage = -1L;
		if(successful_defend_events == 0 || defend_events == 0) {
			return defend_percentage = 0;
		}else{
			return defend_percentage = successful_defend_events * 100 / defend_events;
		}
	}
	
	public long successfulAttackPercentage() {
		long successful_attack_percentage = -1L;
		
		if(successful_attack_events == 0 || attack_events == 0) {
			return successful_attack_percentage = 0;
		}else {
			return successful_attack_percentage = successful_attack_percentage * 100 / attack_events;
		}
	}
	

}