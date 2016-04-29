package cn.wang;
/**
 * Created by wang on 22/04/16.
 */
public class CampaignStatus {

    private long id;
    private long time;
    private long error_code;
    private long season;
    private long points;
    private long points_taken;
    private long points_max;
    private String status;
    private long introduction_order;

    public CampaignStatus() {

    }

    public CampaignStatus(long time, long error_code, long season,
			long points, long points_taken, long points_max, String status,
			long introduction_order) {
		super();
		
		this.time = time;
		this.error_code = error_code;
		this.season = season;
		this.points = points;
		this.points_taken = points_taken;
		this.points_max = points_max;
		this.status = status;
		this.introduction_order = introduction_order;
	}
    
	public CampaignStatus(long id, long time, long error_code, long season,
			long points, long points_taken, long points_max, String status,
			long introduction_order) {
		super();
		this.id = id;
		this.time = time;
		this.error_code = error_code;
		this.season = season;
		this.points = points;
		this.points_taken = points_taken;
		this.points_max = points_max;
		this.status = status;
		this.introduction_order = introduction_order;
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

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public long getPoints_taken() {
		return points_taken;
	}

	public void setPoints_taken(long points_taken) {
		this.points_taken = points_taken;
	}

	public long getPoints_max() {
		return points_max;
	}

	public void setPoints_max(long points_max) {
		this.points_max = points_max;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getIntroduction_order() {
		return introduction_order;
	}

	public void setIntroduction_order(long introduction_order) {
		this.introduction_order = introduction_order;
	}

	

}
