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
 1. om dagen är större än tillgängliga dagar
 2. om dagen är mindre än tillgängliga dagar (kanske början av säsongen)
 3. alla andra fall
 OBS: detta kommer returnera en tom array ifall vi befinner oss i början av säsogen!

 returnerar ett array som innehåller resultat för enemy 0-2
 */

function calculateLerp(res, sliderValue){
    var tmpSlider= sliderValue, prevSlider=null, nextSlider=null;
    var lerpResult=[];
    var prev=[];

    if(sliderValue % 1 !=0 && sliderValue!=0){
        tmpSlider = sliderValue | 0;
    }

    if(tmpSlider !=null && res.length > 0) {
        
        if (tmpSlider >= res.length || tmpSlider + 2 >= res.length) {
            nextSlider = res.length - 1;
            prevSlider = tmpSlider - 1;
            prevSlider--;
        }else if (tmpSlider <= 0 || tmpSlider-2 <= 0) {
            prevSlider = 0;
            nextSlider = tmpSlider + 1;
            nextSlider--;
        }else {
            prevSlider = tmpSlider - 2;
            prevSlider--;
            nextSlider = tmpSlider + 2;
            nextSlider--;
        }

        //minska med ett eftersom dagen som motsvaras i season arrayen är -1 dvs dagn 1 är [0]
        console.log("length : "+res.length+" ,tmpSlider: " + tmpSlider + "  ,nextSlider: " + nextSlider + "  ,prevSlider:" + prevSlider);

        for (var day = 0; day < res[day].length; day++) {
            lerpResult[day] = {
                points: res[nextSlider][day].points,
                points_taken: res[nextSlider][day].points_taken,
            };

            prev[day] = {
                points: res[prevSlider][day].points,
                points_taken: res[prevSlider][day].points_taken,
            };
        }

        for (var lerp = 0; lerp < lerpResult.length; lerp++) {
            lerpResult[lerp].points = main(sliderValue, prev[lerp].points, lerpResult[lerp].points);
            lerpResult[lerp].points_taken = main(sliderValue, prev[lerp].points_taken, lerpResult[lerp].points_taken);
        }
    }
    return lerpResult;
}