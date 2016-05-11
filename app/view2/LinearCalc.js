
var requestedTime, convertedTime;
var startPoints, endPoints, difference, diffperhour;


function runLinear(reqTime, stPoints,enPoints){
    requestedTime = reqTime;
    startPoints = stPoints;
    endPoints = enPoints;

    difference = endPoints - startPoints;
    diffperhour = Math.round(difference/24);
    getHours();
    return pointsAtRequestedTime();
}

function getHours(){

    var rTime = requestedTime | 0;
  //  document.write("rTime: "+rTime);
    var mod = Math.round((requestedTime % rTime) * 100);
  //  document.write("mod "+mod);
    convertedTime = mod;
}

function pointsAtRequestedTime(){

    var points = 0;
    var i;

    for(i = 0; i < convertedTime; i++){
        points += diffperhour;
    }
    return points + startPoints;
}
