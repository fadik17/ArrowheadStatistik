var sliderVal=1;
var enemyType="bugs";


function evalSlider(){

    sliderVal=document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML=sliderVal;
}

function saveEnemyType(){

    enemyType=document.getElementById('enemyType').value;
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
            url: "http://localhost:8080/c=3",
            params: {"season": "10", "start": sliderVal, "end": sliderVal}
        });
    }

    this.getCampaign=function () {

        return $http({

            method:'GET',
            url:"http://localhost:8080/post"
        });
    };
});


app.controller("WebApiCtrl", function($scope, dataService) {

    $scope.data = null;

    dataService.getData().then(function (dataResponse) {

        $scope.data = dataResponse;
    });

    $scope.defaultSlide = function () {

        document.getElementById("sliderValue").value = "1";
    };

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize = function () {

        return 50;
    };

    dataService.getCampaign().then(function(response){
        
        $scope.campaign=response.data;
    });
});

/*
app.controller("WebApiCtrl", function($scope, $http){

    $http.get("http://localhost:8080/post").then(function(response) {
        console.log(response);
        $scope.result = response.data;
    }, function(response) {

        //fail case
    //    document.write("fail");
        console.log(response);
        $scope.result = response.data;
    });

     $scope.lol=function(){
        $http.get("http://localhost:8080/c=3", {
            params: {"season": 14, "start": 20, "end": 20}
        }).success(function (snapResponse) {
            console.log("Success", snapResponse);
            $scope.snapResult = snapResponse;
        }).error(function () {
            console.log("error");
        });
    };

    $scope.defaultSlide = function () {
        document.getElementById("sliderValue").value = "1";
    };

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize=function () {
        return 50;
    };
});
*/