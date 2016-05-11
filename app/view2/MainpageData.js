var sliderVal = 1;
var enemyType = "general";
var choosedSeason = 1;
var currentSeason = 1;
var flagg = false;
var one,two;
var timestamp=null;

function evalSlider2() {

    sliderVal = document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML = sliderVal;

    var integer = sliderVal | 0;
    var float=sliderVal%integer;

    if(float > 0.24){

        sliderVal=integer+1;
        document.getElementById('sliderValue').innerHTML= sliderVal;
        float=0;
    }
}

function isInt(number){

    if(number % 1 == 0) {

        return true;
    }
    else {

        return false;
    }
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

function saveSeason() {
    choosedSeason = document.getElementById('seasons').value;
}

var app = angular.module('app', [], function ($httpProvider) {

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});


app.service('dataService', function ($http) {

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
    };
});


app.controller("WebApiCtrl", function ($scope, dataService) {

    $scope.data = null;

    dataService.getData(choosedSeason, sliderVal, sliderVal).then(function (dataResponse) {
        $scope.data = dataResponse;
    });

    $scope.getSnaps = function () {

        dataService.getData(choosedSeason, sliderVal, sliderVal).then(function (dataResponse) {
            $scope.data = dataResponse;
        });
    };

    $scope.camp = function () {
        var j;
        var nowtimestamp = Math.round(new Date().getTime()/1000); // unix i sekunder
        document.write("NOW sTAMP: "+ nowtimestamp);
        timestamp = nowtimestamp-3600000; // en dag bakåt?
        for(j=0;j<2;j++){
            test(j, timestamp, nowtimestamp);
        }
    };

    function test(j, timestamp, nowtimestamp){
        dataService.getData(choosedSeason, timestamp, nowtimestamp).then(function (dataResponse) {

            // $scope.data = dataResponse;
            if(j==0){
                one=dataResponse.data.defend_events[0].points;
          //      document.write("lol1: "+one);
                timestamp-=3600000;
            }
            if(j==1){
                two=dataResponse.data.defend_events[0].points;
          //      document.write("One: "+one +" ,two :"+two +"<br />");
          //      document.write(" FUNKAR: "+runLinear(sliderVal,one,two));
            //    document.write("lol 2: "+two);
            }
        });

    }

    $scope.getSeason = function () {
        dataService.getCampaign().then(function (dataResponse) {
            $scope.trubble = dataResponse;
            currentSeason = dataResponse.data.campaign_status[1].season;
            createSelectOptions();
            run(dataResponse.data.statistics);
            $scope.calculation=getCalculations();
        });
    };

    $scope.defaultSlide = function () {
        return 1;
    };

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize = function () {
        return 30;
    };

    /**fixed currentsSeason in getCampaign function. It gets the currentSeason**/
    dataService.getCampaign().then(function (response) {

        $scope.campaign = response.data;
        currentSeason = response.data.campaign_status[0].season;
        run(response.data.statistics);
        $scope.calculation=getCalculations();
    });

    $scope.selectStatisticsInSeason = function () {

        var def_events = [];
        enemyType = document.getElementById('enemyType').value;

        dataService.getData(choosedSeason, sliderVal, sliderVal).then(function (response) {

            if (enemyType == "global_stats") {

                $scope.data = response.data;
            }
            else {

                var i;

                if (response.data.defend_events != null) {

                    for (i = 0; i < response.data.defend_events.length; i++) {

                        if (response.data.defend_events[i].enemy == enemyType) {

                            def_events.push(response.data.defend_events[i]);
                        }
                    }

                    $scope.data = def_events;
                }
            }
        });
    };
});