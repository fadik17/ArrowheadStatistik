/**
 * Created by Alican Bircan 
 * Edited by Fadi Kamil & Dani Daryaweesh
 */

var requestedTime, convertedTime;
var startPoints, endPoints;

function main(reqTime, stPoints, enPoints){

    requestedTime = reqTime;
    startPoints = stPoints;
    endPoints = enPoints;
    
  
    getT();
    return lerp(startPoints, endPoints, convertedTime);
}

function getT(){

    var rTime = Math.floor(requestedTime);
    convertedTime = requestedTime - rTime;
    convertedTime = Math.ceil(convertedTime * 100) / 100;
}

function lerp(a, b, t) {
    var x = a + t * (b - a);
    return x;
}

/*
 returnerar ett array resultat där enemy0-2 och sista är globalstats!
 */
function calculateLerp(res, sliderValue){
    var tmpSlider= sliderValue, globalStatsPoints=null, globalStatsPointsTaken=null;
    var lerpResult=[];

    if(sliderValue % 1 !=0 && sliderValue!=0){
        tmpSlider = sliderValue | 0;
        tmpSlider++;
    }

    for(var day=0;day<res[day].length;day++){
        lerpResult[day] = {
            points: res[tmpSlider-1][day].points,
            points_taken: res[tmpSlider-1][day].points_taken,
            globalStatsPoints: 0,
            globalStatsPoints_taken: 0
        };

        globalStatsPoints+= res[tmpSlider-1][day].points;
        globalStatsPointsTaken+=res[tmpSlider-1][day].points_taken;
    }

    for(var lerp=0;lerp<lerpResult.length;lerp++){
        lerpResult[lerp].points = main(sliderValue, 0, lerpResult[lerp].points);
        lerpResult[lerp].points_taken =  main(sliderValue, 0, lerpResult[lerp].points_taken);
        lerpResult[lerp].globalStatsPoints= main(sliderValue, 0, globalStatsPoints);
        lerpResult[lerp].globalStatsPoints_taken= main(sliderValue, 0, globalStatsPointsTaken);
    }

    return lerpResult;
}