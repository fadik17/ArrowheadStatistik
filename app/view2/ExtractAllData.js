/**
 * Created by dani on 18/05/16.
 * test.....
 */

var seasons=new Array();
var defend_ev=[];
var test=[];

function extractEverything(JsonObj, currentSeason){

    test[test.length]=[JsonObj.data.snapshots];
 //   document.write("TESTING: "+test[test.length].data.season);
    getSnapshots(JsonObj.data.snapshots, seasons.length);
    getAttackEvents(JsonObj.data.defend_events, defend_ev.length, currentSeason);
}


function getSnapshots(snapObject, currentSeasonLength){

    for(var counter=0;counter<snapObject.length;counter++){ // dagar
        var seasonTmp=snapObject[counter].season;
        var timeTmp= snapObject[counter].time;

        var extract=JSON.parse(snapObject[counter].data); //
        seasons[counter+currentSeasonLength]=new Array(extract.length);
       
        for(var extractCount=0;extractCount<extract.length;extractCount++){ // antal data
            seasons[counter+currentSeasonLength][extractCount]={
                time: timeTmp,
                season: seasonTmp,
                points: extract[extractCount].points,
                points_taken: extract[extractCount].points_taken,
                status: extract[extractCount].status
            };
        //    document.write("\n VAR: "+seasons[counter][extractCount].season+"\n");
        }
    }
}

function getAttackEvents(defendObject, currentDefenseLength, currentSeas){

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
 //   document.write("\n season tesT: "+defend_ev[0].season+"\n");
}

function getSeasonsArray(){
    return seasons;
}

function getDefend_evArray(){
    return defend_ev;
}

function getDo(){
    return test;
}

