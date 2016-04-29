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

    $scope.getSnapshot=function(){
        $http.get("http://localhost:8080/x",{
            params:{"SliderValue":sliderVal}
        }).success(function (snapResponse){
            console.log("Success",snapResponse);
            $scope.snapResult=snapResponse.data;
        }).error(function (){
            console.log("error");
        });
    };

    $scope.defaultSlide=function() {
        document.getElementById("sliderValue").value = "1";
    }

    // möjliggöra dynamisk ändring --> kommer att användas senare
    $scope.getEventSize=function () {
        return 50;
    }
});