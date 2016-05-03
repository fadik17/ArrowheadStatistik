var sliderVal=1;
var enemyType="general";
var choosedSeason=1;
var currentSeason=1;
var flagg=false;

function evalSlider2() {

    sliderVal = document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML = sliderVal;
}

function saveEnemyType(){

    enemyType=document.getElementById('enemyType').value;
}


function createSelectOptions(){
 //  document.write("in test: "+currentSeason);
    var x = document.getElementById('seasons');
    if(flagg!=true) {
        for (i = 1; i <= currentSeason; i++) {
            var option = document.createElement("option");
            option.text = i;
            x.add(option);
        }
        flagg=true;
    }
}

function saveSeason(){
    choosedSeason=document.getElementById('seasons').value;
}

var app = angular.module('app', [], function($httpProvider){

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});


app.service('dataService', function($http) {
    
    this.getData = function() {

        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: "http://localhost:8080/GetSnapshots",
            params: {"season": choosedSeason, "start": sliderVal, "end": sliderVal}
        });
    };

    this.getCampaign=function () {

        return $http({
            method:'GET',
            url:"http://localhost:8080/GetCampaignStatus"
        });
    };
});


app.controller("WebApiCtrl", function($scope, dataService) {

    $scope.data = null;

    dataService.getData().then(function (dataResponse) {
        $scope.data = dataResponse;
    });

    $scope.evalSlider = function () {
        dataService.getData().then(function (dataResponse) {
            $scope.data = dataResponse;
        });
    };

    $scope.camp=function () {
        dataService.getData().then(function (dataResponse) {
            $scope.data = dataResponse;
        });
    };
    $scope.getSeason=function () {
        dataService.getCampaign().then(function (dataResponse) {
            $scope.trubble = dataResponse;
            currentSeason = dataResponse.data.campaign_status[1].season;
            createSelectOptions();
        });
    };
    
    $scope.defaultSlide = function () {
        return 1;
    };

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize = function () {
        return 50;
    };

    dataService.getCampaign().then(function(response){
        $scope.campaign=response.data;
    });

});