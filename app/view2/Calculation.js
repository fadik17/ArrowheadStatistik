/**
 * Created by Fadi on 2016-05-06.
 */
var enemies=[];
var enemyResult=[];

function run(jsonObj) {

    document.write("In HERE! with shots: "+ jsonObj[1].shots); // test
    enemies.push(tmpenemy0(jsonObj)); // dessa behåller vi ifall vi vill spara allmänna statistik utöver beräkningarna
    enemies.push(tmpenemy1(jsonObj));
    enemies.push(tmpenemy2(jsonObj));

    for(i=0;i<3;i++){
        var accidential= accidentialCalc(enemies[i].accidentals, enemies[i].kills);
        var attackPercentage= attackSuccessCalc(enemies[i].successful_attack_events, enemies[i].attack_events);
        var defendPercentage=defendSuccessCalc(enemies[i].successful_defend_events, enemies[i].defend_events);
        var missionsPercentage=missionSuccessCalc(enemies[i].successful_missions, enemies[i].missions);
        var kdRatio=kdRatioCalc(enemies[i].kills, enemies[i].deaths);
        var accuracy=accuracyCalc(enemies[i].hits, enemies[i].shots);

        enemyResult[i]={
            accuracy:accuracy,
            kdRatio:kdRatio,
            missionsPercentage:missionsPercentage,
            defendPercentage:defendPercentage,
            attackPercentage:attackPercentage,
            accidentalKills:accidential
        };
    }
}

function accuracyCalc(hits, shots) {
    if (hits == 0 || shots == 0) {
        return 0;
    }else {
        return ((hits / shots) * 100);
    }
}

function kdRatioCalc(kills, deaths) {
    if (kills == 0 || deaths == 0){
        return 0;
    }else {
        return(kills / deaths);
    }
}

function accidentialCalc (accidentals,kills) {
    return ((accidentals / kills) * 100);
}

function attackSuccessCalc (successful_attack_events,attack_events) {
    if (successful_attack_events == 0 || attack_events == 0) {
       return 0;
    }
    else {
       return (successful_attack_events / attack_events) * 100;
    }
}

function defendSuccessCalc (successful_defend_events, defend_events){
    if (successful_defend_events == 0 || defend_events == 0){
        return 0;
    }
    else {
        return (successful_defend_events / defend_events) * 100;
    }
}

function missionSuccessCalc (successful_missions, missions) {
    if (successful_missions == 0 || missions == 0) {
        return 0;
    }
    else {
        return (successful_missions / missions) * 100;
    }
}

function tmpenemy0(jsonObj){
    this.seasons=jsonObj[0].seasons;
    this.missions = jsonObj[0].missions;
    this.successful_missions=jsonObj[0].successful_defend_events;
    this.successful_defend_events=jsonObj[0].successful_defend_events;
    this.defend_events=jsonObj[0].defend_events;
    this.attack_events=jsonObj[0].attack_events;
    this.successful_attack_events=jsonObj[0].successful_attack_events;
    this.deaths=jsonObj[0].deaths;
    this.kills=jsonObj[0].kills;
    this.accidentals=jsonObj[0].accidentals;
    this.shots=jsonObj[0].shots;
    this.hits=jsonObj[0].hits;
    this.players=jsonObj[0].players;
    this.total_unique_players=jsonObj[0].total_unique_players;
    this.completed_planets=jsonObj[0].completed_planets;
}

function tmpenemy1(jsonObj){
    this.seasons=jsonObj[1].seasons;
    this.missions = jsonObj[1].missions;
    this.successful_missions=jsonObj[1].successful_defend_events;
    this.successful_defend_events=jsonObj[1].successful_defend_events;
    this.defend_events=jsonObj[1].defend_events;
    this.attack_events=jsonObj[1].attack_events;
    this.successful_attack_events=jsonObj[1].successful_attack_events;
    this.deaths=jsonObj[1].deaths;
    this.kills=jsonObj[1].kills;
    this.accidentals=jsonObj[1].accidentals;
    this.shots=jsonObj[1].shots;
    this.hits=jsonObj[1].hits;
    this.players=jsonObj[1].players;
    this.total_unique_players=jsonObj[1].total_unique_players;
    this.completed_planets=jsonObj[1].completed_planets;
}

function tmpenemy2(jsonObj){
    this.seasons=jsonObj[2].seasons;
    this.missions = jsonObj[2].missions;
    this.successful_missions=jsonObj[2].successful_defend_events;
    this.successful_defend_events=jsonObj[2].successful_defend_events;
    this.defend_events=jsonObj[2].defend_events;
    this.attack_events=jsonObj[2].attack_events;
    this.successful_attack_events=jsonObj[2].successful_attack_events;
    this.deaths=jsonObj[2].deaths;
    this.kills=jsonObj[2].kills;
    this.accidentals=jsonObj[2].accidentals;
    this.shots=jsonObj[2].shots;
    this.hits=jsonObj[2].hits;
    this.players=jsonObj[2].players;
    this.total_unique_players=jsonObj[2].total_unique_players;
    this.completed_planets=jsonObj[2].completed_planets;
}
function getGeneralStats() {
    return enemies;
}
function getCalculations() {
    return enemyResult;
}

/*
NULL OBJEKTEN BEHÖVS INTE LÄNGRE
 function createObjects(){
 enemy0={
 accuracy:null,
 kdRatio:null,
 missionsPercentage:null,
 defendPercentage:null,
 attackPercentage:null,
 accidentalKills:null
 };

 enemy1={
 accuracy:null,
 kdRatio:null,
 missionsPercentage:null,
 defendPercentage:null,
 attackPercentage:null,
 accidentalKills:null
 };

 enemy2={
 accuracy:null,
 kdRatio:null,
 missionsPercentage:null,
 defendPercentage:null,
 attackPercentage:null,
 accidentalKills:null
 };
 enemyResult.push(enemy0);
 enemyResult.push(enemy1);
 enemyResult.push(enemy2);
 }

 var season; //ID
 var enemy; //enemy ID
 var missions, successful_missions; //missions percentage
 var defend_events, successful_defend_events; //defend event percentage
 var attack_events, successful_attack_events; //attack event percentage
 var deaths, kills, accidentals; //KD
 var shots, hits; //accuracy
 var players, total_unique_players;
 var total_mission_difficulty, completed_planets;
 var accuracy, kdRatio, missionsPercentage, defendPercentage, attackPercentage, accidentalKills;

function run(jsonObj) {
    getJSONObject(jsonObj);
    accuracyCalc();
    kdRatioCalc();
    missionSuccessCalc();
    defendSuccessCalc();
    attackSuccessCalc();
    accidentialCalc();
    createObjects();

}
*/
