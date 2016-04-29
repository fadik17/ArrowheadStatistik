package cn.wang;

/**
 * Created by wang on 22/04/16.
 */
public class DefendEvent {

    private long id;
    private long time;
    private long error_code;
    private long season;
    private long event_id;
    private long start_time;
    private long end_time;
    private long region;
    private long enemy;
    private long points_max;
    private long points;
    private String status;


    public  DefendEvent() {

    }


	public DefendEvent(long id, long time, long error_code, long season,
			long event_id, long start_time, long end_time, long region,
			long enemy, long points_max, long points, String status) {
		super();
		this.id = id;
		this.time = time;
		this.error_code = error_code;
		this.season = season;
		this.event_id = event_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.region = region;
		this.enemy = enemy;
		this.points_max = points_max;
		this.points = points;
		this.status = status;
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


	public long getEvent_id() {
		return event_id;
	}


	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}


	public long getStart_time() {
		return start_time;
	}


	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}


	public long getEnd_time() {
		return end_time;
	}


	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}


	public long getRegion() {
		return region;
	}


	public void setRegion(long region) {
		this.region = region;
	}


	public long getEnemy() {
		return enemy;
	}


	public void setEnemy(long enemy) {
		this.enemy = enemy;
	}


	public long getPoints_max() {
		return points_max;
	}


	public void setPoints_max(long points_max) {
		this.points_max = points_max;
	}


	public long getPoints() {
		return points;
	}


	public void setPoints(long points) {
		this.points = points;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (end_time ^ (end_time >>> 32));
		result = prime * result + (int) (enemy ^ (enemy >>> 32));
		result = prime * result + (int) (error_code ^ (error_code >>> 32));
		result = prime * result + (int) (event_id ^ (event_id >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (points ^ (points >>> 32));
		result = prime * result + (int) (points_max ^ (points_max >>> 32));
		result = prime * result + (int) (region ^ (region >>> 32));
		result = prime * result + (int) (season ^ (season >>> 32));
		result = prime * result + (int) (start_time ^ (start_time >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (time ^ (time >>> 32));
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
		DefendEvent other = (DefendEvent) obj;
		if (end_time != other.end_time)
			return false;
		if (enemy != other.enemy)
			return false;
		if (error_code != other.error_code)
			return false;
		if (event_id != other.event_id)
			return false;
		if (id != other.id)
			return false;
		if (points != other.points)
			return false;
		if (points_max != other.points_max)
			return false;
		if (region != other.region)
			return false;
		if (season != other.season)
			return false;
		if (start_time != other.start_time)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (time != other.time)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "DefendEvent [id=" + id + ", time=" + time + ", error_code="
				+ error_code + ", season=" + season + ", event_id=" + event_id
				+ ", start_time=" + start_time + ", end_time=" + end_time
				+ ", region=" + region + ", enemy=" + enemy + ", points_max="
				+ points_max + ", points=" + points + ", status=" + status
				+ "]";
	}


	
    
}
