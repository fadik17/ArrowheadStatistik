var sliderVal = 1;
var enemyType = "bugs";

function evalSlider() {

    sliderVal = document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML = sliderVal;
}

function saveEnemyType() {

    enemyType = document.getElementById('enemyType').value;
}

var app = angular.module('app', [], function ($httpProvider) {

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});

app.service('dataService', function($http) {
    
    this.getData = function() {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: "http://localhost:8080/c=3",
            params: {"season": 10, "start": sliderVal, "end": sliderVal}
        });
    }
});

app.controller("WebApiCtrl", function($scope, dataService) {

    $scope.data = null;

    dataService.getData().then(function(dataResponse) {

        $scope.data = dataResponse;
    });
});