package cn.wang;

import java.util.Date;
import java.util.TimerTask;

public class Controller {
	
	private Library model;
	
	public Controller () {
		
	}

	public Controller(Library model) {
		super();
		this.model = model;
	}
	
	
	// We can put codes below in a timer later ... See details in class Timer
	
	// For Teddy & Carlos' sake: FrontEnd
		public void searchDatabase () {
			
		}
		
		class updateDatabasePeriodically extends TimerTask  {
			
			//TimerTaskSnapshot snapshot = new TimerTaskSnapshot();
			private JsonHandler handler;
			
			public void run() {    
				updateDatabase(handler);
				Date date = new Date(this.scheduledExecutionTime());        
				System.out.println("A new update is made at：" + date);    }
		}
		
		// BackEnd: to insert items to database
		public void updateDatabase (JsonHandler handler) {
			updateCampaignStatus(handler);
			updateDefendEvent(handler);
			updateAttackEvent(handler);
			updateStatistics(handler);
			
		}

		public void updateCampaignStatus (JsonHandler handler) {
			int cnt = 0;
			for(int i = 0; i < handler.getSeasonOfCampaignStatus().size(); i ++) {
				long time = handler.getTime();
				long error_code = handler.getError_code();
			    long season = handler.getSeasonOfCampaignStatus().get(i);
			    long points = handler.getPointsOfCampaignStatus().get(i);
			    long points_taken = handler.getPointsTakenOfCampaignStatus().get(i);
			    long points_max = handler.getPointsMaxOfCampaignStatus().get(i);
			    String status = handler.getStatusOfCampaignStatus().get(i);
			    long introduction_order = handler.getIntroductionOrderOfCampaignStatus().get(i);
			    model.addNewCampaignStatus(time, error_code, season, points, 
			    		points_taken, points_max, status, introduction_order);
			    cnt ++;
			    
			}
			System.out.println(cnt + " items ---> CampaignStatus, ahDB!");
		}

		public void updateDefendEvent (JsonHandler handler) {
			
				int cnt = 0;
				long time = handler.getTime();
				long error_code = handler.getError_code();
				long season = handler.getSeasonOfDefendEvent();
			    long event_id = handler.getEventIdOfDefendEvent();
			    long start_time = handler.getStartTimeOfDefendEvent();
			    long end_time = handler.getEndTimeOfDefendEvent();
			    long region = handler.getRegionOfDefendEvent();
			    long enemy = handler.getEnemyOfDefendEvent();
			    long points_max = handler.getPointsMaxOfDefendEvent();
			    long points = handler.getPointsOfDefendEvent();
			    String status = handler.getStatusOfDefendEvent();
			    model.addNewDefendEvent(time, error_code, season, event_id, start_time, end_time, region, enemy, points_max, points, status);
			    cnt ++;
			    System.out.println(cnt + " items ---> DefendEvent, ahDB!");
		
				
		}
		

		public void updateAttackEvent (JsonHandler handler) {
			int cnt = 0;
			for(int i = 0; i < handler.getSeasonOfAttackEvent().size(); i ++) {
				long time = handler.getTime();
				long error_code = handler.getError_code();
				long season = handler.getSeasonOfAttackEvent().get(i);
			    long event_id = handler.getEventIdOfAttackEvent().get(i);
			    long start_time = handler.getStartTimeOfAttackEvent().get(i);
			    long end_time = handler.getEndTimeOfAttackEvent().get(i);
			    long enemy = handler.getEnemyOfAttackEvent().get(i);
			    long points_max = handler.getPointsMaxOfAttackEvent().get(i);
			    long points = handler.getPointsOfAttackEvent().get(i);
			    String status = handler.getStatusOfAttackEvent().get(i);
			    long players_at_start = handler.getPlayerAtStartOfAttackEvent().get(i);
			    long max_event_id = handler.getMaxEventIdOfAttackEvent().get(i);
			    model.addNewAttackEvent(time, error_code, season, event_id, start_time, end_time, enemy, points_max, points, status, players_at_start, max_event_id);
			    cnt ++;
			   
			}	
			System.out.println(cnt + " items ---> AttackEvent, ahDB!");
		}
		
		public void updateStatistics (JsonHandler handler) {
			int cnt = 0;
			for (int i = 0; i < handler.getSeasonOfStatistics().size(); i ++) {
				long time = handler.getTime();
				long error_code = handler.getError_code();
				long season = handler.getSeasonOfStatistics().get(i);
				long season_duration = handler.getSeasonDurationOfStatistics().get(i);
				long enemy = handler.getEnemyOfStatistics().get(i);
				long players = handler.getPlayersOfStatistics().get(i);
				long total_unique_players = handler.getTotalUniquePlayersOfStatistics().get(i);
				long missions = handler.getMissionsOfStatistics().get(i);
				long successful_missions = handler.getSuccessfulMissionsOfStatistics().get(i);
				long total_mission_difficulty = handler.getTotalMissionDifficultyOfStatistics().get(i);
				long completed_planets = handler.getCompletedPlanetsOfStatistics().get(i);
				long defend_events = handler.getDefendEventsOfStatistics().get(i);
				long successful_defend_events = handler.getSuccessfulDefendEventsOfStatistics().get(i);
				long attack_events = handler.getAttackEventsOfStatistics().get(i);
				long successful_attack_events = handler.getSuccessfulAttackEventsOfStatistics().get(i);
				long deaths = handler.getDeathsOfStatistics().get(i);
				long kills = handler.getKillsOfStatistics().get(i);
				long accidentals = handler.getAccidentalsOfStatistics().get(i);
				long shots = handler.getShotsOfStatistics().get(i);
				long hits = handler.getHitsOfStatistics().get(i);
				
				model.addNewStatistics(time, error_code, season, season_duration, enemy, players, total_unique_players, missions, successful_missions, total_mission_difficulty, completed_planets, defend_events, successful_defend_events, attack_events, successful_attack_events, deaths, kills, accidentals, shots, hits);
				cnt ++;
			}
			System.out.println(cnt + " items ---> Statistics, ahDB!");
		}

}
