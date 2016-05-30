/**
 * Created by dani on 18/05/16.
 *
 */
var APIURL1 = "https://api.helldiversgame.com/1.0/";
var APIURL2 = "https://files.arrowheadgs.com/helldivers_api/default/" ;
var latestSeason;
// nödvändiga arrayer
var seasons=new Array(), seasonsLengths=[];
var defend_ev=[], defend_ev_season=[];
var attack_ev=[], attack_ev_season=[];




/**

 * **/
var allSeasons = []; // has every season data 1-current season
var allAttackEvents = [];
var allDefendEvents = [];
/**
 * **/

//change name in the future
function getSeasonSnapshot(season)
{
    ;
    for(var i=0;i<allSeasons.length;i++)
    {
        if(allSeasons[i].snapshots[0].season  == season)
        {
            return allSeasons[i];
        }
    }
}

function getSnapshotsInSeason(season)
{
    for(var i=0;i<allSeasons.length;i++)
    {
        if(allSeasons[i].snapshots[0].season  == season)
        {
            return allSeasons[i].snapshots;
        }
    }
}

function getAttackEvents2(season, enemytype)
{
    var seasonAttackEvents = getSeasonSnapshot(season).attack_events;
    if(seasonAttackEvents == null)
    {
      
        return null;
    }

    if(enemytype != null)
    {
        var attackEventsForEnemy = [];

        for(var i=0;i<seasonAttackEvents.length;i++)
        {
            if(seasonAttackEvents[i].enemy == enemytype)
            {
                attackEventsForEnemy.push(seasonAttackEvents[i]);
            }
        }
        return attackEventsForEnemy;
    }
    else
    {
        //return attack evetn for all enemy
        return seasonAttackEvents;
    }
}

function getDefendEvents2(season, enemytype)
{
    var seasonDefEvents = getSeasonSnapshot(season).defend_events;

    if(seasonDefEvents == null)
    {
        return null;
    }

    if(enemytype != null)
    {
        var defendEventsForEnemy = [];
        for(var i=0;i<seasonDefEvents.length;i++)
        {
            if(seasonDefEvents[i].enemy == enemytype)
            {
                defendEventsForEnemy.push(seasonDefEvents[i]);
            }
        }

        return defendEventsForEnemy;
    }
    else
    {
        //return attack evetn for all enemy
        return seasonDefEvents;
    }
}

function getLatestSeason(){
    return latestSeason;
}

function getStartTimeInSeason(season)
{
    var dataResponse = getSnapshotsInSeason(season);
    if( dataResponse.length != 0)
    {
        return dataResponse[0].time;

    }

    return null;
}

function getLatestDayInSeason(season, enemytype){

    var tempSeasonInfo = getSnapshotsInSeason(season);
    //earlier season can get an extra day error.
    if(tempSeasonInfo != null && enemytype == null)
    {
        return tempSeasonInfo.length;
    }
    else
    {
        var defEventsForEnemyAmount = 0;
        var attackEventsForEnemyAmount = 0;

        var enemyTypeDefEvents = getDefendEvents2(season, enemytype);
        var enemyTypeAttackEvents = getAttackEvents2(season, enemytype);
       
        if(enemyTypeDefEvents != null)
        {

            defEventsForEnemyAmount = enemyTypeDefEvents.length;
        }

        if(enemyTypeAttackEvents != null)
        {
            attackEventsForEnemyAmount = enemyTypeAttackEvents.length;
        }
       
        return Math.max(defEventsForEnemyAmount,attackEventsForEnemyAmount);
    }
    return null;
}

function extractEverything(JsonObj){

    allSeasons.push(JsonObj.data);

    if(JsonObj.data.attack_events === undefined)
    {
        allAttackEvents.push(null);
    }
    else
    {
        allAttackEvents.push(JsonObj.data.attack_events);
    }
    //got to do same for defend event as attack events
    allDefendEvents.push(JsonObj.data.defend_events);



    getSnapshots(JsonObj.data.snapshots, seasons.length, JsonObj.data.points_max);
    getDefendEvents(JsonObj.data.defend_events, defend_ev.length);
    getAttackEvents(JsonObj.data.attack_events, attack_ev.length);
}

var app2 = angular.module('app2', [], function ($httpProvider) {

    /**
     * Teddy & Co modified following:
     */

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    //Access-Control-Allow-Origin not needed anymore
}).run(function(dataService){
    initialize(dataService);
});

app2.service('dataService', function ($http) {

    this.getSnapshots = function (season, start, end) {
        /**
         * Teddy & Co modified following:
         */
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'POST',
            url: APIURL1,
            data : "action=get_snapshots" + "&season=" + season + "&start=" + start + "&end=" + end
        });
    };

    this.getCampaign = function () {
        /**
         * Teddy & Co modified following:
         */
        return $http({
            method: "POST",
            url: APIURL1,
            data :'action=get_campaign_status'
        });
    };

    this.getSeasonStatistics = function(season)
    {
        return $http({
            method: 'POST',
            url:APIURL1,
            data :'action=get_season_statistics' + "&season=" + season
        });
    };

});

initialize = function (dataService){
    dataService.getCampaign().then(function(dataResponse){
        latestSeason = dataResponse.data.campaign_status[0].season;

        for(var i=1;i<=latestSeason;i++){ // loppar igenom det och hämtar statistiken för varje säsong
            dataService.getSnapshots(i,null,null).then(function (dataResponse) { // skickar in och sparar

                extractEverything(dataResponse);
            });
        }

        //for stats ex. KD etc
        run(dataResponse.data.statistics);

        choosedSeason = currentSeason = latestSeason;
        createSelectOptions();


    });
};

function getSnapshots(snapObject, currentSeasonLength, pointsMaxObj){
    if(snapObject !=null){
        var globalSeason=null;
        for(var counter=0;counter<snapObject.length;counter++){
            var seasonTmp=snapObject[counter].season;
            var timeTmp= snapObject[counter].time;
            globalSeason=seasonTmp;

            var extract=JSON.parse(snapObject[counter].data);
            seasons[counter+currentSeasonLength]= new Array(extract.length);
            for(var extractCount=0;extractCount<extract.length;extractCount++){ // antal data
                seasons[counter+currentSeasonLength][extractCount]={
                    points_max: pointsMaxObj[extractCount],
                    time: timeTmp,
                    season: seasonTmp,
                    points: extract[extractCount].points,
                    points_taken: extract[extractCount].points_taken,
                    status: extract[extractCount].status
                };
            }
        }

    }

    seasonsLengths[globalSeason]={
        start: currentSeasonLength,
        end: seasons.length
    };
}

/*
1. sparar alla defend events i en 1 dimensionell array
 */
function getDefendEvents(defendObject, currentDefenseLength){


    if(defendObject != null){
        var globalSeason=defendObject[0].season;
        for(var counter=0;counter<defendObject.length;counter++){
            defend_ev [counter+currentDefenseLength]={
                season: defendObject[counter].season,
                event_id: defendObject[counter].event_id,
                start_time: defendObject[counter].start_time,
                end_time: defendObject[counter].end_time,
                region: defendObject[counter].region,
                enemy: defendObject[counter].enemy,
                points_max: defendObject[counter].points_max,
                points: defendObject[counter].points,
                status: defendObject[counter].status,
                players_at_start: defendObject[counter].players_at_start
            };
        }
        defend_ev_season[globalSeason]={
            start:currentDefenseLength,
            end: defend_ev.length
        };
    }
    else
    {
        defend_ev_season[globalSeason]= null;
    }

}



/*
 1. sparar alla attackevents i en 1 dimensionell array
 */
function getAttackEvents(attackObject, currentAttackLength){
    if(attackObject != null)
    {
        var globalSeason=attackObject[0].season;
        for(var counter=0;counter<attackObject.length;counter++){
            attack_ev [counter+currentAttackLength]={
                season: attackObject[counter].season,
                event_id: attackObject[counter].event_id,
                start_time: attackObject[counter].start_time,
                end_time: attackObject[counter].end_time,
                region: attackObject[counter].region,
                enemy: attackObject[counter].enemy,
                points_max: attackObject[counter].points_max,
                points: attackObject[counter].points,
                status: attackObject[counter].status,
                players_at_start: attackObject[counter].players_at_start
            };
        }
        attack_ev_season[globalSeason]={
            start:currentAttackLength,
            end: attack_ev.length
        }
    }
    else
    {
        attack_ev_season[globalSeason]=null;
    }


}


// returnerar samtliga säsonger
function getSeasonsArray(){
    return seasons;
}

// returnerar samtliga defend_events
function getDefend_evArray(){
    return defend_ev;
}

// returnerar samtliga attack_ events
function getAttack_evArray(){
    return attack_ev;
}

/*
1. Returnerar en hel säsong med objekt och möjligheten att välja enemytyper för varje dag
2. resultatet som returneras: möjligheten att välja dag och enemytyp [dag (beroende av hur lång en säsong är 0 -x )] [enemytyp (0-2 max)]
 */
function getSeasonInfo(season){
    var start= seasonsLengths[season].start;
    var end = seasonsLengths[season].end;
    var result=[];

    var startTmp=start;
    var tmpCounter=0;

    for(var xCount=start;xCount<end;xCount++){
        result[tmpCounter]=new Array(seasons[xCount].length);

        for(var yCount=0;yCount<seasons[xCount].length;yCount++){
            result[tmpCounter][yCount]=seasons[startTmp][yCount];

        }
        startTmp++;
        tmpCounter++;
    }
    return result;
}

/*
1. returnerar information för en specifik dag bara
2. innehåller de tre olika enemytyperna bara, alltså kan man välja endas från 0-2
3.  resultatet som returneras: [möjlighet att välja enemy 0-2 (max 3 enemies) ]
 */
function getSeasonDay(season,day){
    var start= seasonsLengths[season].start;
    var end = seasonsLengths[season].end;
    var result=[];
    // start dagen + inskickade dagen för att få den specifika dagen
    for(var count=0; count<3;count++){
        result[count] = seasons[start+day][count];
    }

    return result;
}

/*
1. returnerar alla defend_events för en specifik säsong
 */
function getSeasonDefendEvents(season){
    var result=[], resultCounter=0;

    if(defend_ev_season[season] != null)
    {
        var start = defend_ev_season[season].start;
        var end= defend_ev_season[season].end;

        for(var counter=start;counter<end;counter++){
            result[resultCounter++]=defend_ev[counter];
        }
    }
    return result;

}

/*
1. returnerar alla attack_events för en specifik säsong
 */
function getSeasonAttackEvents(season){

    var result=[], resultCounter=0;

    if(attack_ev_season[season] != null)
    {
        var start = attack_ev_season[season].start;
        var end= attack_ev_season[season].end;


        for(var counter=start;counter<end;counter++){
            result[resultCounter++]=attack_ev[counter];
        }

    }

    return result;

}