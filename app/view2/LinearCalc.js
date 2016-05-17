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
  //  document.write("requ: "+requestedTime+" ,start: "+startPoints+" ,end: "+endPoints);
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