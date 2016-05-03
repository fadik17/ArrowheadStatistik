package cn.wang;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 22/04/16.
 */
public class DerbyDatabaseDAO implements DatabaseDAO {

    private Connection connection;
    private QueryRunner dbAccess = new QueryRunner();
    private static final List<Calculation> EMPTY = new ArrayList<>();

    private String user = "me"; String passwd = "me";
    private String database = "ahDB";
    private String server= "jdbc:mysql://localhost:3306/" + database + "?UseClientEnc=UTF8";

    @Override
    public long insertCampaignStatus(CampaignStatus campaignStatus) {
        try{
            long id = dbAccess.update(connection, "insert into CampaignStatus(time, error_code, season, points, " +
                    "points_taken, points_max, status, introduction_order) values (?,?,?,?,?,?,?,?)",
                    campaignStatus.getTime(), campaignStatus.getError_code(), 
                    campaignStatus.getSeason(), campaignStatus.getPoints(),
                    campaignStatus.getPoints_taken(), campaignStatus.getPoints_max(), 
                    campaignStatus.getStatus(), campaignStatus.getIntroduction_order());

            return id;

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1L;	// Return -1 for error;

    }

    @Override
    public long insertDefendEvent(DefendEvent defendEvent) {
        try{
            long id = dbAccess.update(connection, "insert into DefendEvent(time, error_code, season, event_id, start_time, end_time," +
                    "region, enemy, points_max, points, status) values (?,?,?,?,?,?,?,?,?,?,?)",
                    defendEvent.getTime(), defendEvent.getError_code(), 
                    defendEvent.getSeason(), defendEvent.getEvent_id(),  
                    defendEvent.getStart_time(), defendEvent.getEnd_time(),
                    defendEvent.getRegion(), defendEvent.getEnemy(), 
                    defendEvent.getPoints_max(),defendEvent.getPoints(),
                    defendEvent.getStatus());

            return id;

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1L;	// Return -1 for error;
    }

    @Override
    public long insertAttackEvent(AttackEvent attackEvent) {
        try{
            long id = dbAccess.update(connection, "insert into AttackEvent(time, error_code, season, event_id, start_time, end_time, enemy," +
                    "points_max, points, status, players_at_start, max_event_id) values (?,?,?,?,?,?,?,?,?,?,?,?)",
                    attackEvent.getTime(), attackEvent.getError_code(), 
                    attackEvent.getSeason(), attackEvent.getEvent_id(), attackEvent.getStart_time(),
                    attackEvent.getEnd_time(), attackEvent.getEnemy(), attackEvent.getPoints_max(),
                    attackEvent.getPoints(), attackEvent.getStatus(), attackEvent.getPlayers_at_start(),
                    attackEvent.getMax_event_id());

            return id;

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1L;	// Return -1 for error;
    }

    @Override
    public long insertStatistics(Statistics statistics) {
        try{
            long id = dbAccess.update(connection, "insert into Statistics(time, error_code, season, season_duration, enemy, players," +
                    "total_unique_players, missions, successful_missions, total_mission_difficulty," +
                    "completed_planets, defend_events, successful_defend_events," +
                    "attack_events, successful_attack_events, deaths," +
                    "kills, accidentals, shots, hits) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    statistics.getTime(), statistics.getError_code(), 
                    statistics.getSeason(), statistics.getSeason_duration(), 
                    statistics.getEnemy(), statistics.getPlayers(),
                    statistics.getTotal_unique_players(), statistics.getMissions(), 
                    statistics.getSuccessful_missions(),statistics.getTotal_mission_difficulty(), 
                    statistics.getCompleted_planets(), statistics.getDefend_events(),
                    statistics.getSuccessful_defend_events(), statistics.getAttack_events(), 
                    statistics.getSuccessful_attack_events(), statistics.getDeaths(), 
                    statistics.getKills(), statistics.getAccidentals(), 
                    statistics.getShots(), statistics.getHits());

            return id;

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1L;	// Return -1 for error;
    }

    @Override
    public long insertCalculation(Calculation calculation) {
        try{
            long id = dbAccess.update(connection, "insert into Calculation(season, snapshot, accuracy, accidental_kills, "
            		+ "successful_mission_percentage, successful_defend_percentage,  successful_attack_percentage, kill_death_rate, "
            		+ "enemy) values (?,?,?,?,?,?,?,?,?)",
            		
            		calculation.getSeason(), calculation.getSnapshot(),
            		calculation.getAccuracy(), calculation.getAccidental_kills(),
            		calculation.getMission_percentage(), calculation.getDefend_percentage(),
            		calculation.getAttack_percentage(), calculation.getKill_death_rate(),
            		calculation.getEnemy());
            		
            
                   
            return id;

        }catch(Exception e){
            e.printStackTrace();
        }

        return -1L;	// Return -1 for error;
    }
    @Override
    public void connect() throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(server, user, passwd);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {

        connection.close();

    }

	@Override
	public List<Calculation> searchCalculation(CalculationSearchType searchType, Object value) {
		
		        // Generic methods
				
				String whereClause = "";
				String valueClause = "";
				
				switch(searchType){
				
				case SNAPSHOT:
					whereClause = "snapshot LIKE ?";
					valueClause = value.toString();
					break;
				case SEASON:
					whereClause = "season = ?";
					valueClause = "%" + value.toString() + "%";
					break;
				case ACCURACY:
					whereClause = "accuracy = ?";
					valueClause = value.toString();
					break;
				case ACCIDENTAL_KILLS:
					whereClause = "accidental_kills LIKE ?";
					valueClause = "%"+ value.toString() + "%";
					break;
				case SUCCESSFUL_MISSION_PERCENTAGE:
					whereClause = "successful_mission_percentage LIKE ?";
					valueClause = "%" + value.toString() + "%";
					break;
				case SUCCESSFUL_DEFEND_PERCENTAGE:
					whereClause = "successful_defend_percentage LIKE ?";
					valueClause = "%" + value.toString() + "%";
					break;
				case SUCCESSFUL_ATTACK_PERCENTAGE:
					whereClause = "successful_attack_percentage LIKE ?";
					valueClause = "%" + value.toString() + "%";
					break;
				case KILL_DEATH_RATE:
					whereClause = "kill_death_rate LIKE ?";
					valueClause = "%" + value.toString() + "%";
					break;
				case ENEMY:
					whereClause = "enemy LIKE ?";
					valueClause = "%" + value.toString() + "%";
					break;
				default:
					System.out.println("Unknow search type");
					break;
				
				}
				
				try{
					/**
					 * It is a MUST to add " " after the last "and" i mySQL syntax!!!!
					 */
					return dbAccess.query(connection, "SELECT snapshot, season, accuracy, accidental_kills,"
							+ "successful_mission_percentage, successful_defend_percentage, "
							+ "successful_attack_percentage,"
							+ "kill_death_rate, enemy from Calculation WHERE " 
							+  whereClause, new BeanListHandler<Calculation>(Calculation.class), valueClause);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return EMPTY;
		
	}
}
