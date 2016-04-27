/**
 * Created by Teddy on 2016-04-15.
 */

var sliderVal=1;

function evalSlider(){
    sliderVal=document.getElementById('rating').value;
    document.getElementById('sliderValue').innerHTML=sliderVal;
}

// används inte förnärvarande
function correctSlider(){
    var sliderVal=document.getElementById('getNumbers').value;
    document.getElementById('rating').innerHTML=sliderVal;
}


var app = angular.module('app', [], function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});


app.controller("WebApiCtrl", function($scope, $http){
    // igen...
    /*$http.get({
        url : 'localhost:8080/post', //url : 'https://api.helldiversgame.com/0.3/' --> det gamla
        method : "GET",
        data : 'action=get_campaign_status' // lägg till ytterligare inparamterar som ska skickas in
      headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      }*/
    $http.get("http://localhost:8080/post").then(function(response) {
        console.log(response);
        $scope.result = response.data;
        //$scope.campaign = $scope.result.campaign_status;
    }, function(response) {
        //fail case
        document.write("fail");
        console.log(response);
        $scope.result = response.data;
    });
    
    $scope.defaultSlide=function() {
        document.getElementById("sliderValue").value = "1";
    }

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize=function () {
        return 50;
    }
});

/*
Göra GET från servern:

 $http({
 method: 'GET',
 url: '/someUrl'
 data : 'action=get_campaign_status'
 }).then(function successCallback(response) {
 // this callback will be called asynchronously
 // when the response is available
 }, function errorCallback(response) {
 // called asynchronously if an error occurs
 // or server returns response with an error status.
 });

 länken till koden ovan: https://docs.angularjs.org/api/ng/service/$http
 */

