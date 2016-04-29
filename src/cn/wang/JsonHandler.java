package cn.wang;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonHandler {
	
	private JSONObject jsonObject;

	public JsonHandler(JSONObject jsonObject) {
		
		this.jsonObject = jsonObject;
	}

	// Global values
	public long getTime() {
		long time = (long) jsonObject.get("time");
		
		return time;
	}
	
	public long getError_code() {
		long error_code = (long) jsonObject.get("error_code");
		
		return error_code;
	}
	
	// Values of Campaign_status
	@SuppressWarnings("unchecked")
	public ArrayList<Long> getSeasonOfCampaignStatus() {
	    
		ArrayList<Long> seasonValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			seasons.add(campaign.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			seasonValues.add((long) season.get("season"));
		}
		
		return seasonValues;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Long> getPointsOfCampaignStatus() {
	    
		ArrayList<Long> pointsValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray points = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			points.add(campaign.get(i));
			JSONObject season = (JSONObject) points.get(i);
			pointsValues.add((long) season.get("points"));
		}
		
		return pointsValues;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Long> getPointsTakenOfCampaignStatus() {
	    
		ArrayList<Long> pointsTakenValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray pointsTaken = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			pointsTaken.add(campaign.get(i));
			JSONObject season = (JSONObject) pointsTaken.get(i);
			pointsTakenValues.add((long) season.get("points_taken"));
		}
		
		return pointsTakenValues;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Long> getPointsMaxOfCampaignStatus() {
	    
		ArrayList<Long> pointsMaxValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray pointsMax = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			pointsMax.add(campaign.get(i));
			JSONObject season = (JSONObject) pointsMax.get(i);
			pointsMaxValues.add((long) season.get("points_max"));
		}
		
		return pointsMaxValues;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getStatusOfCampaignStatus() {
	    
		ArrayList<String> statusValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray status = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			status.add(campaign.get(i));
			JSONObject season = (JSONObject) status.get(i);
			statusValues.add((String) season.get("status"));
		}
		
		return statusValues;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Long> getIntroductionOrderOfCampaignStatus() {
	    
		ArrayList<Long> introductionOrderValues = new ArrayList<>(); 
		
		JSONArray campaign = (JSONArray) jsonObject.get("campaign_status");
		JSONArray introductionOrder = new JSONArray();
		
		for(int i = 0; i < campaign.size(); i ++){
			
			introductionOrder.add(campaign.get(i));
			JSONObject season = (JSONObject) introductionOrder.get(i);
			introductionOrderValues.add((long) season.get("introduction_order"));
		}
		
		return introductionOrderValues;
		
	}
	
	// Values of Defend_Event

public long getSeasonOfDefendEvent() {
	    
		long seasonValues = 0; 
		
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		
		seasonValues = (long) defendEvent.get("season");
		
		return seasonValues;
		
	}

public long getEventIdOfDefendEvent() {
	    
		long eventIdValues = 0; 
		
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		eventIdValues = (long) defendEvent.get("event_id");
		
		return eventIdValues;
		
	}

public long getStartTimeOfDefendEvent() {
	    
		long startTimeValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		startTimeValues = (long) defendEvent.get("start_time");
		return startTimeValues;
		
	}


public long getEndTimeOfDefendEvent() {
	    
		long endTimeValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		endTimeValues = (long) defendEvent.get("end_time");
		
		return endTimeValues;
		
	}


public long getRegionOfDefendEvent() {
	    
		long regionValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		regionValues = (long) defendEvent.get("region");
		return regionValues;
		
	}


public long getEnemyOfDefendEvent() {
	    
		long enemyValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		enemyValues = (long) defendEvent.get("enemy");
		
		return enemyValues;
		
	}


public long getPointsMaxOfDefendEvent() {
	    
		long pointsMaxValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		pointsMaxValues = (long) defendEvent.get("points_max");
		
		return pointsMaxValues;
		
	}


public long getPointsOfDefendEvent() {
	    
		long pointsValues = 0; 
		JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
		pointsValues = (long) defendEvent.get("points");
		
		return pointsValues;
		
	}


public String getStatusOfDefendEvent() {
    
	String statusValues = null; 
	JSONObject defendEvent = (JSONObject) jsonObject.get("defend_event");
	statusValues = (String) defendEvent.get("status");
	
	return statusValues;
	
}


	// Values of Attack_Event
@SuppressWarnings("unchecked")
public ArrayList<Long> getSeasonOfAttackEvent() {
	    
		ArrayList<Long> seasonValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++) {
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			seasonValues.add((long) season.get("season"));
		}
		
		return seasonValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getEventIdOfAttackEvent() {
	    
		ArrayList<Long> eventIdValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			eventIdValues.add((long) season.get("event_id"));
		}
		
		return eventIdValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getStartTimeOfAttackEvent() {
	    
		ArrayList<Long> startTimeValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			startTimeValues.add((long) season.get("start_time"));
		}
		
		return startTimeValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getEndTimeOfAttackEvent() {
	    
		ArrayList<Long> endTimeValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			endTimeValues.add((long) season.get("end_time"));
		}
		
		return endTimeValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getEnemyOfAttackEvent() {
	    
		ArrayList<Long> enemyValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			enemyValues.add((long) season.get("enemy"));
		}
		
		return enemyValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getPointsMaxOfAttackEvent() {
	    
		ArrayList<Long> pointsMaxValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			pointsMaxValues.add((long) season.get("points_max"));
		}
		
		return pointsMaxValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getPointsOfAttackEvent() {
	    
		ArrayList<Long> pointsValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			pointsValues.add((long) season.get("points"));
		}
		
		return pointsValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<String> getStatusOfAttackEvent() {
    
	ArrayList<String> statusValues = new ArrayList<>(); 
	
	JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
	JSONArray status = new JSONArray();
	
	for(int i = 0; i < attackEvent.size(); i ++){
		
		status.add(attackEvent.get(i));
		JSONObject season = (JSONObject) status.get(i);
		statusValues.add((String) season.get("status"));
	}
	
	return statusValues;
	
}

@SuppressWarnings("unchecked")
public ArrayList<Long> getPlayerAtStartOfAttackEvent() {
	    
		ArrayList<Long> playersAtStartValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			playersAtStartValues.add((long) season.get("players_at_start"));
		}
		
		return playersAtStartValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getMaxEventIdOfAttackEvent() {
	    
		ArrayList<Long> maxEventIdValues = new ArrayList<>(); 
		
		JSONArray attackEvent = (JSONArray) jsonObject.get("attack_events");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < attackEvent.size(); i ++){
			
			seasons.add(attackEvent.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			maxEventIdValues.add((long) season.get("max_event_id"));
		}
		
		return maxEventIdValues;
		
	}



	// Values of Statistics
@SuppressWarnings("unchecked")
public ArrayList<Long> getSeasonOfStatistics() {
	    
		ArrayList<Long> seasonValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			seasonValues.add((long) season.get("season"));
		}
		
		return seasonValues;
		
	}
	
@SuppressWarnings("unchecked")
public ArrayList<Long> getSeasonDurationOfStatistics() {
	    
		ArrayList<Long> seasonDurationValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			seasonDurationValues.add((long) season.get("season_duration"));
		}
		
		return seasonDurationValues;
		
	}
	
@SuppressWarnings("unchecked")
public ArrayList<Long> getEnemyOfStatistics() {
	    
		ArrayList<Long> enemyValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			enemyValues.add((long) season.get("enemy"));
		}
		
		return enemyValues;
		
	}
	
@SuppressWarnings("unchecked")
public ArrayList<Long> getPlayersOfStatistics() {
	    
		ArrayList<Long> playersValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			playersValues.add((long) season.get("players"));
		}
		
		return playersValues;
		
	}
	
@SuppressWarnings("unchecked")
public ArrayList<Long> getTotalUniquePlayersOfStatistics() {
	    
		ArrayList<Long> totalUniquePlayersValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			totalUniquePlayersValues.add((long) season.get("total_unique_players"));
		}
		
		return totalUniquePlayersValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getMissionsOfStatistics() {
	    
		ArrayList<Long> missionsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			missionsValues.add((long) season.get("missions"));
		}
		return missionsValues;
}

@SuppressWarnings("unchecked")
public ArrayList<Long> getSuccessfulMissionsOfStatistics() {
	    
		ArrayList<Long> successfulMissionsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			successfulMissionsValues.add((long) season.get("successful_missions"));
		}
		return successfulMissionsValues;
}

@SuppressWarnings("unchecked")
public ArrayList<Long> getTotalMissionDifficultyOfStatistics() {
	    
		ArrayList<Long> totalMissionDifficultyValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			totalMissionDifficultyValues.add((long) season.get("total_mission_difficulty"));
		}
		return totalMissionDifficultyValues;
}

@SuppressWarnings("unchecked")
public ArrayList<Long> getCompletedPlanetsOfStatistics() {
	    
		ArrayList<Long> completedPlanetsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			completedPlanetsValues.add((long) season.get("completed_planets"));
		}
		
		return completedPlanetsValues;
		
	}	

	
@SuppressWarnings("unchecked")
public ArrayList<Long> getDefendEventsOfStatistics() {
	    
		ArrayList<Long> defendEventsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			defendEventsValues.add((long) season.get("defend_events"));
		}
		
		return defendEventsValues;
		
	}	

@SuppressWarnings("unchecked")
public ArrayList<Long> getSuccessfulDefendEventsOfStatistics() {
	    
		ArrayList<Long> successfulDefendEventsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			successfulDefendEventsValues.add((long) season.get("successful_defend_events"));
		}
		
		return successfulDefendEventsValues;
		
	}	

@SuppressWarnings("unchecked")
public ArrayList<Long> getAttackEventsOfStatistics() {
	    
		ArrayList<Long> attackEventsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			attackEventsValues.add((long) season.get("attack_events"));
		}
		
		return attackEventsValues;
		
	}	

@SuppressWarnings("unchecked")
public ArrayList<Long> getSuccessfulAttackEventsOfStatistics() {
	    
		ArrayList<Long> successfulAttackEventsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			successfulAttackEventsValues.add((long) season.get("successful_attack_events"));
		}
		
		return successfulAttackEventsValues;
		
	}	

@SuppressWarnings("unchecked")
public ArrayList<Long> getDeathsOfStatistics() {
	    
		ArrayList<Long> deathsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			deathsValues.add((long) season.get("deaths"));
		}
		
		return deathsValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getKillsOfStatistics() {
	    
		ArrayList<Long> killsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			killsValues.add((long) season.get("kills"));
		}
		
		return killsValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getAccidentalsOfStatistics() {
	    
		ArrayList<Long> accidentalsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			accidentalsValues.add((long) season.get("accidentals"));
		}
		
		return accidentalsValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getShotsOfStatistics() {
	    
		ArrayList<Long> shotsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			shotsValues.add((long) season.get("shots"));
		}
		
		return shotsValues;
		
	}

@SuppressWarnings("unchecked")
public ArrayList<Long> getHitsOfStatistics() {
	    
		ArrayList<Long> hitsValues = new ArrayList<>(); 
		
		JSONArray statistics = (JSONArray) jsonObject.get("statistics");
		JSONArray seasons = new JSONArray();
		
		for(int i = 0; i < statistics.size(); i ++){
			
			seasons.add(statistics.get(i));
			JSONObject season = (JSONObject) seasons.get(i);
			hitsValues.add((long) season.get("hits"));
		}
		
		return hitsValues;
		
	}
}



