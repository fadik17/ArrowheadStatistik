package cn.wang;

/**
 * Created by wang on 22/04/16.
 */
public class Statistics {


    private long id;
    private long time;
    private long error_code;
    private long season;
    private long season_duration;
    private long enemy;
    private long players;
    private long total_unique_players;
    private long missions;
    private long successful_missions;
    private long total_mission_difficulty;
    private long completed_planets;
    private long defend_events;
    private long successful_defend_events;
    private long attack_events;
    private long successful_attack_events;
    private long deaths;
    private long kills;
    private long accidentals;
    private long shots;
    private long hits;

    public Statistics() {

    }

	public Statistics(long id, long time, long error_code, long season,
			long season_duration, long enemy, long players,
			long total_unique_players, long missions, long successful_missions,
			long total_mission_difficulty, long completed_planets,
			long defend_events, long successful_defend_events,
			long attack_events, long successful_attack_events, long deaths,
			long kills, long accidentals, long shots, long hits) {
		super();
		this.id = id;
		this.time = time;
		this.error_code = error_code;
		this.season = season;
		this.season_duration = season_duration;
		this.enemy = enemy;
		this.players = players;
		this.total_unique_players = total_unique_players;
		this.missions = missions;
		this.successful_missions = successful_missions;
		this.total_mission_difficulty = total_mission_difficulty;
		this.completed_planets = completed_planets;
		this.defend_events = defend_events;
		this.successful_defend_events = successful_defend_events;
		this.attack_events = attack_events;
		this.successful_attack_events = successful_attack_events;
		this.deaths = deaths;
		this.kills = kills;
		this.accidentals = accidentals;
		this.shots = shots;
		this.hits = hits;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getError_code() {
		return error_code;
	}

	public void setError_code(long error_code) {
		this.error_code = error_code;
	}

	public long getSeason() {
		return season;
	}

	public void setSeason(long season) {
		this.season = season;
	}

	public long getSeason_duration() {
		return season_duration;
	}

	public void setSeason_duration(long season_duration) {
		this.season_duration = season_duration;
	}

	public long getEnemy() {
		return enemy;
	}

	public void setEnemy(long enemy) {
		this.enemy = enemy;
	}

	public long getPlayers() {
		return players;
	}

	public void setPlayers(long players) {
		this.players = players;
	}

	public long getTotal_unique_players() {
		return total_unique_players;
	}

	public void setTotal_unique_players(long total_unique_players) {
		this.total_unique_players = total_unique_players;
	}

	public long getMissions() {
		return missions;
	}

	public void setMissions(long missions) {
		this.missions = missions;
	}

	public long getSuccessful_missions() {
		return successful_missions;
	}

	public void setSuccessful_missions(long successful_missions) {
		this.successful_missions = successful_missions;
	}

	public long getTotal_mission_difficulty() {
		return total_mission_difficulty;
	}

	public void setTotal_mission_difficulty(long total_mission_difficulty) {
		this.total_mission_difficulty = total_mission_difficulty;
	}

	public long getCompleted_planets() {
		return completed_planets;
	}

	public void setCompleted_planets(long completed_planets) {
		this.completed_planets = completed_planets;
	}

	public long getDefend_events() {
		return defend_events;
	}

	public void setDefend_events(long defend_events) {
		this.defend_events = defend_events;
	}

	public long getSuccessful_defend_events() {
		return successful_defend_events;
	}

	public void setSuccessful_defend_events(long successful_defend_events) {
		this.successful_defend_events = successful_defend_events;
	}

	public long getAttack_events() {
		return attack_events;
	}

	public void setAttack_events(long attack_events) {
		this.attack_events = attack_events;
	}

	public long getSuccessful_attack_events() {
		return successful_attack_events;
	}

	public void setSuccessful_attack_events(long successful_attack_events) {
		this.successful_attack_events = successful_attack_events;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}

	public long getAccidentals() {
		return accidentals;
	}

	public void setAccidentals(long accidentals) {
		this.accidentals = accidentals;
	}

	public long getShots() {
		return shots;
	}

	public void setShots(long shots) {
		this.shots = shots;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accidentals ^ (accidentals >>> 32));
		result = prime * result
				+ (int) (attack_events ^ (attack_events >>> 32));
		result = prime * result
				+ (int) (completed_planets ^ (completed_planets >>> 32));
		result = prime * result + (int) (deaths ^ (deaths >>> 32));
		result = prime * result
				+ (int) (defend_events ^ (defend_events >>> 32));
		result = prime * result + (int) (enemy ^ (enemy >>> 32));
		result = prime * result + (int) (error_code ^ (error_code >>> 32));
		result = prime * result + (int) (hits ^ (hits >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (kills ^ (kills >>> 32));
		result = prime * result + (int) (missions ^ (missions >>> 32));
		result = prime * result + (int) (players ^ (players >>> 32));
		result = prime * result + (int) (season ^ (season >>> 32));
		result = prime * result
				+ (int) (season_duration ^ (season_duration >>> 32));
		result = prime * result + (int) (shots ^ (shots >>> 32));
		result = prime
				* result
				+ (int) (successful_attack_events ^ (successful_attack_events >>> 32));
		result = prime
				* result
				+ (int) (successful_defend_events ^ (successful_defend_events >>> 32));
		result = prime * result
				+ (int) (successful_missions ^ (successful_missions >>> 32));
		result = prime * result + (int) (time ^ (time >>> 32));
		result = prime
				* result
				+ (int) (total_mission_difficulty ^ (total_mission_difficulty >>> 32));
		result = prime * result
				+ (int) (total_unique_players ^ (total_unique_players >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistics other = (Statistics) obj;
		if (accidentals != other.accidentals)
			return false;
		if (attack_events != other.attack_events)
			return false;
		if (completed_planets != other.completed_planets)
			return false;
		if (deaths != other.deaths)
			return false;
		if (defend_events != other.defend_events)
			return false;
		if (enemy != other.enemy)
			return false;
		if (error_code != other.error_code)
			return false;
		if (hits != other.hits)
			return false;
		if (id != other.id)
			return false;
		if (kills != other.kills)
			return false;
		if (missions != other.missions)
			return false;
		if (players != other.players)
			return false;
		if (season != other.season)
			return false;
		if (season_duration != other.season_duration)
			return false;
		if (shots != other.shots)
			return false;
		if (successful_attack_events != other.successful_attack_events)
			return false;
		if (successful_defend_events != other.successful_defend_events)
			return false;
		if (successful_missions != other.successful_missions)
			return false;
		if (time != other.time)
			return false;
		if (total_mission_difficulty != other.total_mission_difficulty)
			return false;
		if (total_unique_players != other.total_unique_players)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Statistics [id=" + id + ", time=" + time + ", error_code="
				+ error_code + ", season=" + season + ", season_duration="
				+ season_duration + ", enemy=" + enemy + ", players=" + players
				+ ", total_unique_players=" + total_unique_players
				+ ", missions=" + missions + ", successful_missions="
				+ successful_missions + ", total_mission_difficulty="
				+ total_mission_difficulty + ", completed_planets="
				+ completed_planets + ", defend_events=" + defend_events
				+ ", successful_defend_events=" + successful_defend_events
				+ ", attack_events=" + attack_events
				+ ", successful_attack_events=" + successful_attack_events
				+ ", deaths=" + deaths + ", kills=" + kills + ", accidentals="
				+ accidentals + ", shots=" + shots + ", hits=" + hits + "]";
	}
    
    
}

    
	