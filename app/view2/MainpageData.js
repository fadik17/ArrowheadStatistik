var sliderVal=1;
var enemyType="bugs";
var currentSeason = 14;

function evalSlider2() {

    sliderVal = document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML = sliderVal;
}

var app = angular.module('app', [], function($httpProvider){

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});

app.service('dataService', function($http) {
    
    this.getData = function(season, start, end) {

        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: "http://localhost:8080/GetSnapshots",
            params: {"season": season, "start": start, "end": end}
        });
    }

    this.getCampaign=function () {

        return $http({
            method:'GET',
            url:"http://localhost:8080/GetCampaignStatus"
        });
    };
});

app.controller("WebApiCtrl", function($scope, dataService) {

    $scope.data = null;


    dataService.getData(sliderVal, sliderVal, sliderVal).then(function (dataResponse) {

        $scope.data = dataResponse;
    });

    $scope.evalSlider = function () {
        dataService.getData(sliderVal, sliderVal, sliderVal).then(function (dataResponse) {
            $scope.data = dataResponse;
        });
    };

    $scope.defaultSlide = function () {

        document.getElementById("sliderValue").value = "1";
    };

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize = function () {
        return currentSeason;
    };

    /**fixed currentsSeason in getCampaign function. It gets the currentSeason**/
    dataService.getCampaign().then(function(response){
        $scope.campaign=response.data;
        currentSeason = response.data.campaign_status[0].season;
    });
    /**
    saveEnemyType - current start + end value should be dynamic
    **/
    $scope.selectStatisticsInSeason = function (){
        var def_events = [];
        enemyType=document.getElementById('enemyType').value;
        dataService.getData(currentSeason, 2, 2).then(function(response)
        {
            if(enemyType == "global_stats")
            {
                $scope.data = response.data;
            }
            else
            {
                var i;
                if(response.data.defend_events != null)
                {
                    for(i =0;i<response.data.defend_events.length;i++)
                    {
                        if(response.data.defend_events[i].enemy == enemyType)
                        {
                            def_events.push(response.data.defend_events[i]);
                        }
                    }
                    $scope.data = def_events;
                }
            }
        });
    };
});