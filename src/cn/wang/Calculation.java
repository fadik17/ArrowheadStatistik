package cn.wang;

public class Calculation {

	private long id;
	private long time;
	private long season;
	private String snapshot;
	private long accuracy;
	private long accidental_kills;
    private long mission_percentage;
    private long defend_percentage;
    private long attack_percentage;
    private long kill_death_rate;
    private long enemy;
    
    public Calculation() {}
    
    public Calculation(long season, String snapshot, long accuracy,
			long accidental_kills, long mission_percentage,
			long defend_percentage, long attack_percentage,
			long kill_death_rate, long enemy) {
		super();
		
		
		this.season = season;
		this.snapshot = snapshot;
		this.accuracy = accuracy;
		this.accidental_kills = accidental_kills;
		this.mission_percentage = mission_percentage;
		this.defend_percentage = defend_percentage;
		this.attack_percentage = attack_percentage;
		this.kill_death_rate = kill_death_rate;
		this.enemy = enemy;
	}

    
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public long getSeason() {
		return season;
	}

	public void setSeason(long season) {
		this.season = season;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public long getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(long accuracy) {
		this.accuracy = accuracy;
	}

	public long getAccidental_kills() {
		return accidental_kills;
	}

	public void setAccidental_kills(long accidental_kills) {
		this.accidental_kills = accidental_kills;
	}

	public long getMission_percentage() {
		return mission_percentage;
	}

	public void setMission_percentage(long mission_percentage) {
		this.mission_percentage = mission_percentage;
	}

	public long getDefend_percentage() {
		return defend_percentage;
	}

	public void setDefend_percentage(long defend_percentage) {
		this.defend_percentage = defend_percentage;
	}

	public long getAttack_percentage() {
		return attack_percentage;
	}

	public void setAttack_percentage(long attack_percentage) {
		this.attack_percentage = attack_percentage;
	}

	public long getKill_death_rate() {
		return kill_death_rate;
	}

	public void setKill_death_rate(long kill_death_rate) {
		this.kill_death_rate = kill_death_rate;
	}

	public long getEnemy() {
		return enemy;
	}

	public void setEnemy(long enemy) {
		this.enemy = enemy;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calculation other = (Calculation) obj;
		if (accidental_kills != other.accidental_kills)
			return false;
		if (accuracy != other.accuracy)
			return false;
		if (attack_percentage != other.attack_percentage)
			return false;
		if (defend_percentage != other.defend_percentage)
			return false;
		if (enemy != other.enemy)
			return false;
		if (id != other.id)
			return false;
		if (kill_death_rate != other.kill_death_rate)
			return false;
		if (mission_percentage != other.mission_percentage)
			return false;
		if (season != other.season)
			return false;
		if (snapshot != other.snapshot)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Calculation [id=" + id + ", time=" + time + ", season="
				+ season + ", snapshot=" + snapshot + ", accuracy=" + accuracy
				+ ", accidental_kills=" + accidental_kills
				+ ", mission_percentage=" + mission_percentage
				+ ", defend_percentage=" + defend_percentage
				+ ", attack_percentage=" + attack_percentage
				+ ", kill_death_rate=" + kill_death_rate + ", enemy=" + enemy
				+ "]";
	}
    
    

	
}
