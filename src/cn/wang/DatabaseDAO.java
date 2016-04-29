package cn.wang;

/**
 * Created by wang on 22/04/16.
 */

public interface DatabaseDAO extends DAO {

    public long insertCampaignStatus(CampaignStatus campaignStatus);
    public long insertDefendEvent(DefendEvent campaignStatus);
    public long insertAttackEvent(AttackEvent campaignStatus);
    public long insertStatistics(Statistics campaignStatus);

}
