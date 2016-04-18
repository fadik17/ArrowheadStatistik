/**
 * Created by Teddy on 2016-04-15.
 */
var app = angular.module('app', [], function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
});


app.controller("WebApiCtrl", function($scope, $http){
    $http({
        url : 'https://api.helldiversgame.com/0.3/',
        method : "POST",
        data : 'action=get_campaign_status',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).then(function(response) {
        console.log(response);
        $scope.result = response.data;
        //$scope.campaign = $scope.result.campaign_status;
    }, function(response) {
        //fail case
        console.log(response);
        $scope.result = response.data;
    });
});



