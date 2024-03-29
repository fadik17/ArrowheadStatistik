/**
 * Created by dani on 18/05/16.
 *
 */

// nödvändiga arrayer
var seasons=new Array(), seasonsLengths=[];
var defend_ev=[], defend_ev_season=[];
var attack_ev=[], attack_ev_season=[];


function extractEverything(JsonObj){

    getSnapshots(JsonObj.data.snapshots, seasons.length);
    getDefendEvents(JsonObj.data.defend_events, defend_ev.length);
    getAttackEvents(JsonObj.data.attack_events, attack_ev.length);
}


function getSnapshots(snapObject, currentSeasonLength){
    var globalSeason=null;
    for(var counter=0;counter<snapObject.length;counter++){
        var seasonTmp=snapObject[counter].season;
        var timeTmp= snapObject[counter].time;
        globalSeason=seasonTmp;

        var extract=JSON.parse(snapObject[counter].data);
        seasons[counter+currentSeasonLength]=new Array(extract.length);
        for(var extractCount=0;extractCount<extract.length;extractCount++){ // antal data
            seasons[counter+currentSeasonLength][extractCount]={
                time: timeTmp,
                season: seasonTmp,
                points: extract[extractCount].points,
                points_taken: extract[extractCount].points_taken,
                status: extract[extractCount].status
            };
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
    if(defendObject !=null) {
        var globalSeason = defendObject[0].season;
        for (var counter = 0; counter < defendObject.length; counter++) {
            defend_ev [counter + currentDefenseLength] = {
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
    }
    defend_ev_season[globalSeason] = {
        start: currentDefenseLength,
        end: defend_ev.length
    }
}

/*
 1. sparar alla attackevents i en 1 dimensionell array
 */
function getAttackEvents(attackObject, currentAttackLength){
    if(attackObject !=null ) {
        var globalSeason = attackObject[0].season;
        for (var counter = 0; counter < attackObject.length; counter++) {
            attack_ev [counter + currentAttackLength] = {
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
    }
    attack_ev_season[globalSeason] = {
        start: currentAttackLength,
        end: attack_ev.length
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
    var endTmp=end;

    for(var xCount=0;xCount<end;xCount++){
        result[xCount]=new Array(3);

       for(var yCount=0;yCount<3;yCount++){
           result[xCount][yCount]=seasons[startTmp][yCount];
       }
       startTmp++;
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
    var start = defend_ev_season[season].start;
    var end= defend_ev_season[season].end;
    var result=[], resultCounter=0;



    for(var counter=start;counter<end;counter++){
        result[resultCounter++]=defend_ev[counter];
    }
    return result;
}

/*
1. returnerar alla attack_events för en specifik säsong
 */
function getSeasonAttackEvents(season){
    var start = attack_ev_season[season].start;
    var end= attack_ev_season[season].end;
    var result=[], resultCounter=0;

    for(var counter=start;counter<end;counter++){
        result[resultCounter++]=attack_ev[counter];
    }

    return result;
}