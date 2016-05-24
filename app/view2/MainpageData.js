/**
 * Created by Fadi Kamil & Dani Daryaweesh
 */

var sliderVal = 1;
var enemyType = "global_stats";
var choosedSeason = 1;
var currentSeason = 1;
var flagg = false;
var firstDay=null,lastDay=null,sliderlength=null;
var calculatedTime=null;
var jsonData = null;


function evalSlider2() {

    sliderVal = document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML = sliderVal;

    var integer = sliderVal | 0;
    var float=sliderVal%integer;

    if(float > 0.99){
        sliderVal=integer+1;
        document.getElementById('sliderValue').innerHTML= sliderVal;
        float=0;
    }
}

function saveSeason(){

    choosedSeason = document.getElementById('seasons').value;
}

function createSelectOptions() {

    //  document.write("in test: "+currentSeason);
    var x = document.getElementById('seasons');
    var i;

    if (flagg != true) {

        for (i = 1; i <= currentSeason; i++) {

            var option = document.createElement("option");
            option.text = i;
            x.add(option);
        }

        flagg = true;
    }
}

var app = angular.module('app', [], function ($httpProvider) {

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
})


app.service('dataService', function ($http) {
/*
    this.getData = function (season, start, end) {

        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: "http://localhost:8080/GetSnapshots",
            //params: {"season": choosedSeason, "start": sliderVal, "end": sliderVal}
            params: {"season": season, "start": start, "end": end}
        });
    };

    this.getCampaign = function () {

        return $http({
            method: 'GET',
            url: "http://localhost:8080/GetCampaignStatus"
        });
    };*/

    this.getData = function (season, start, end) {

        /**
         * Teddy & Co modified following:
         */
        // $http() returns a $promise that we can add handlers with .then()
        return $http({

            method: 'POST',
            url: 'https://api.helldiversgame.com/1.0/',
            data : "action=get_snapshots" + "&season=" + season + "&start=" + start + "&end=" + end
        });
    };

    this.getCampaign = function () {

        /**
         * Teddy & Co modified following:
         */

        return $http({
            method: "POST",
            url: 'https://api.helldiversgame.com/1.0/',
            data :'action=get_campaign_status'
        });
    };

    this.getSeasonStatistics = function(season) {

        return $http({
            method: 'POST',
            url:"https://files.arrowheadgs.com/helldivers_api/default/",
            data :'action=get_season_statistics' + "&season=" + season
        });
    };
});


app.controller("WebApiCtrl", function ($scope, dataService) {

    $scope.data = null;

    function extractSeasons(lastSeason){

        for(var i=1;i<=lastSeason;i++){ // loppar igenom det och hämtar statistiken för varje säsong
            dataService.getData(i,null,null).then(function (dataResponse) { // skickar in och sparar
                extractEverything(dataResponse);
            });
        }
        $scope.campp = getSeasonsArray();
        $scope.def = getDefend_evArray();
        $scope.att=getAttack_evArray();
    }


    $scope.getInfoTest=function () {
    //    $scope.campp=null;
    //    $scope.campp=getDo();
        var seasonResult=getSeasonInfo(choosedSeason);  // returnerar information beroende av säsongen och dagen som skickas in
     //   document.writeln("ATTACK SEASON start time:  "+res[0].start_time + " ,season : "+res[0].season);
     //   document.write("day 1: "+res[0].season);
    //    document.write("LENGTG: "+res[0].length);

       var result= calculateLerp(seasonResult);

        for(var counter=0;counter<t.length;counter++){
            document.writeln("i: "+i+ "  ,result[i]: "+result[i]);
        }
    };

   

    // får en snapshots som senare används för att extraherar nödvändiga tider
    function getInitData(){

        dataService.getData(choosedSeason,null,null).then(function (dataResponse) {

           $scope.data=dataResponse.snapshots;
         //   se.push(dataResponse.data.snapshots);
         //   se.push(dataResponse.snapshots);
        //    document.write(se[lol].time[0]);
        //    extractInitData(dataResponse.data.snapshots);
        //    doSnap(lastDay,lastDay);
        //     return (dataResponse.snapshots[lol].time);
        });
    }


    // extraherar nödvändiga värden som behövs från början från en snapshot
    function extractInitData(dataa){

        firstDay=dataa[0].time;
        var length=dataa.length;
        lastDay=dataa[length-1].time;
        sliderlength=length;
    }

    // initierar data igen & beräknar den valda tidspunkten från slidern för att få en tillämplig snapshot
    $scope.allData = function () {

        var slidValue=(Math.ceil(sliderVal-1)*86400)+firstDay;
        calculatedTime=slidValue;
        doSnap(firstDay,slidValue);
    };

    // hämtar samtliga snapshots och registrera nödvändig info
    $scope.allDataAgain=function () {

        dataService.getData(choosedSeason,null,null).then(function (dataResponse) {

            $scope.dataNotNeeded=dataResponse;
            extractInitData(dataResponse.data.snapshots);
            $scope.data=null;
            calculatedTime=(Math.ceil(sliderVal-1)*86400)+firstDay;
            doSnap(firstDay,calculatedTime);
        });
    };
    // hämtar en snapshot beroende av tiden som skickas in
    function doSnap(first,lastDay){

        dataService.getData(choosedSeason, first, lastDay).then(function (dataResponse) {

            $scope.data=dataResponse;
            extractPoints(dataResponse.data.snapshots);
       //     $scope.baba=extractPoints(dataResponse.data.snapshots);
        //  var lol=main(sliderVal,firstTakenPoints, lastTakenPoints);
        });
    }
    // extraherar poäng från alla enemy typer + global enemy från en vald snapshot
    function extractPoints(dataa){

        var global=null,enemy0=null, enemy1=null, enemy2=null;
        var start=[], end=[];
        var enemyTypes=[], enemyLerps=[];

        for(var i =0;i<dataa.length;i++){

            var extract=JSON.parse(dataa[i].data);

            for(var j=0;j<extract.length;j++){

                global+=extract[j].points_taken;

                if(j==0){

                    enemy0+=extract[j].points_taken;
                }else if(j==1){

                    enemy1+=extract[j].points_taken;
                }else{

                    enemy2+=extract[j].points_taken;
                }
            }
        }
     //   document.write("Global: "+global+" ,enemy0: "+enemy0+" ,enemy1: "+enemy1+" ,enemy2: "+enemy2);
        enemyTypes.push(enemy0);
        enemyTypes.push(enemy1);
        enemyTypes.push(enemy2);

        /*
        for(var len=0;i<enemyTypes.length;len++){
            enemyLerps.push(main(sliderVal,enemyTypes[i]));
        }
        return enemy2;
        /*
        var extractFirst=JSON.parse(dataa[0].data);
        firstTakenPoints=extractFirst[0].points_taken;

        var secondTakenPoints=JSON.parse(dataa[dataa.length-1].data);
        lastTakenPoints=secondTakenPoints[secondTakenPoints.length-1].points_taken;
        */
    }

    // Ändrar dynamisk storleken på slidern beroende av den valda säsongen
    $scope.getEventSize = function () {

        $scope.len=sliderlength;
        return $scope.len;
    };

    /**
     * Teddy & Co modified:
     */
    $scope.camp = function () {
        enemyType=document.getElementById('enemyType').value;
        var filterOption = document.getElementById('all').value;
        var season = document.getElementById('seasons').value;
        console.log(enemyType);
        if(enemyType == "global_stats")
        {
            console.log(season);
            dataService.getSeasonStatistics(season).then(function (dataResponse) {

                console.log("in get season stats");
                console.log(dataResponse.data);
           //     $scope.data=null;
           //     $scope.data = dataResponse.data;
            });
        }
        //this means that other than global i chosen
        else {

        /*  calculatedTime=(Math.ceil(sliderVal-1)*86400)+firstDay; // ÄNDRAT HÄR ***
            document.write("DD: "+calculatedTime);*/
            dataService.getData(choosedSeason, firstDay, calculatedTime).then(function (dataResponse) {

                jsonData = dataResponse;
                // console.log("allfilter=" + allFilter + "filterOption=" + filterOption);
                console.log(filterOption);
                //save data to var
                var result = [];
                //save data to var
                var def_events = [];
                var atta_events = [];

                for(var i=0;i<dataResponse.data.defend_events.length;i++) {

                    if(enemyType == dataResponse.data.defend_events[i].enemy) {

                        console.log(dataResponse.data.defend_events[i]);
                        def_events.push(dataResponse.data.defend_events[i]);
                    }
                }
                for(var i=0;i<dataResponse.data.attack_events.length;i++) {

                    if(enemyType == dataResponse.data.attack_events[i].enemy) {

                        atta_events.push(dataResponse.data.attack_events[i]);
                    }
                }
                //save data to var end
                switch(filterOption) {

                    case "defend_events":
                        result = def_events;
                        console.log("in defend_events");
                        break;
                    case "attack_events":
                        result = atta_events;
                        console.log("in attack_events");
                        break;
                    default:
                        result = def_events.concat(atta_events);
                        console.log("in default");
                        console.log(result);
                }
            //    $scope.data=null;
           //     $scope.data = result;
            });
        }
    };

    $scope.filterData = function(){

        var element = document.getElementById('all');

        if(element.firstElementChild.nextElementSibling==null) {

            console.log("det är null");
        }

        if(jsonData != null && element.firstElementChild.nextElementSibling==null) {

            for(var datafiltered  in jsonData.data) {
                
                var option = document.createElement("option");
                option.text = datafiltered;
                //console.log(option);
                element.add(option);
            }
            /**
             * this part of filtering is not used
             * **/
        }
    };
});