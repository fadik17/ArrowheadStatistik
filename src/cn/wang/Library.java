package cn.wang;


/**
 * Created by wang on 22/04/16.
 */

public class Library {

    private DatabaseDAO databaseDAO;

    public Library(DatabaseDAO databaseDAO) {

        this.databaseDAO = databaseDAO;

        try {
            databaseDAO.connect();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void close() {
        try {
            databaseDAO.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void addNewCampaignStatus(long time, long error_code, long season, long points, 
    		long points_taken, long points_max, String status, long introduction_order) {

        Thread thread = new Thread() {

            public void run() {

                CampaignStatus campaignStatus = new CampaignStatus();
                campaignStatus.setTime(time);
                campaignStatus.setError_code(error_code);
                campaignStatus.setSeason(season);
                campaignStatus.setPoints(points);
                campaignStatus.setPoints_taken(points_taken);
                campaignStatus.setPoints_max(points_max);
                campaignStatus.setStatus(status);
                campaignStatus.setIntroduction_order(introduction_order);

                databaseDAO.insertCampaignStatus(campaignStatus);

            }

        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void addNewDefendEvent(long time, long error_code, long season, long event_id, 
    		long start_time, long end_time, long region, long enemy, 
    		long points_max, long points, String status) {

        Thread thread = new Thread() {

            public void run() {

                DefendEvent defendEvent = new DefendEvent();
                defendEvent.setTime(time);
                defendEvent.setError_code(error_code);
                defendEvent.setSeason(season);
                defendEvent.setEvent_id(event_id);
                defendEvent.setStart_time(start_time);
                defendEvent.setEnd_time(end_time);
                defendEvent.setRegion(region);
                defendEvent.setEnemy(enemy);
                defendEvent.setPoints_max(points_max);
                defendEvent.setPoints(points);
                defendEvent.setStatus(status);

                databaseDAO.insertDefendEvent(defendEvent);

            }

        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void addNewAttackEvent(long time, long error_code, long season, long event_id, 
    		long start_time, long end_time, long enemy, long points_max, 
    		long points, String status, long players_at_start, long max_event_id) {

        Thread thread = new Thread() {

            public void run() {

                AttackEvent attackEvent = new AttackEvent();
                attackEvent.setTime(end_time);
                attackEvent.setError_code(error_code);
                attackEvent.setSeason(season);
                attackEvent.setEvent_id(event_id);
                attackEvent.setStart_time(start_time);
                attackEvent.setEnd_time(end_time);
                attackEvent.setEnemy(enemy);
                attackEvent.setPoints_max(points_max);
                attackEvent.setPoints(points_max);
                attackEvent.setStatus(status);
                attackEvent.setPlayers_at_start(players_at_start);
                attackEvent.setMax_event_id(max_event_id);

                databaseDAO.insertAttackEvent(attackEvent);

            }

        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void addNewStatistics(long time, long error_code, long season, long season_duration, 
    		long enemy, long players, long total_unique_players, long missions, 
    		long successful_missions, long total_mission_difficulty, long completed_planets, long defend_events, 
    		long successful_defend_events, long attack_events, long successful_attack_events, long deaths,
            long kills, long accidentals, long shots, long hits) {

        Thread thread = new Thread() {

            public void run() {

                Statistics statistics = new Statistics();
                statistics.setTime(time);
                statistics.setError_code(error_code);
                statistics.setSeason(season);
                statistics.setSeason_duration(season_duration);
                statistics.setEnemy(enemy);
                statistics.setPlayers(players);
                statistics.setTotal_unique_players(total_unique_players);
                statistics.setMissions(missions);
                statistics.setSuccessful_missions(successful_missions);
                statistics.setTotal_mission_difficulty(total_mission_difficulty);
                statistics.setCompleted_planets(completed_planets);
                statistics.setDefend_events(defend_events);
                statistics.setSuccessful_defend_events(successful_defend_events);
                statistics.setAttack_events(attack_events);
                statistics.setSuccessful_attack_events(successful_attack_events);
                statistics.setDeaths(deaths);
                statistics.setKills(kills);
                statistics.setAccidentals(accidentals);
                statistics.setShots(shots);
                statistics.setHits(hits);

                databaseDAO.insertStatistics(statistics);

            }

        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
