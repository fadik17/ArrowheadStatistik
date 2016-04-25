/**
 * Created by Teddy on 2016-04-15.
 */

var sliderVal;

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

    $scope.lol="Hello world!";
    $scope.dimension = function (){

    }
});

/*
Göra GET från servern:

 $http({
 method: 'GET',
 url: '/someUrl'
 }).then(function successCallback(response) {
 // this callback will be called asynchronously
 // when the response is available
 }, function errorCallback(response) {
 // called asynchronously if an error occurs
 // or server returns response with an error status.
 });

 länken till koden ovan: https://docs.angularjs.org/api/ng/service/$http
 */

jQuery(document).ready(function ($) {

    $('#checkbox').change(function () {
        setInterval(function () {
            moveRight();
        }, 3000);
    });

    var slideCount = $('#slider ul li').length;
    var slideWidth = $('#slider ul li').width();
    var slideHeight = $('#slider ul li').height();
    var sliderUlWidth = slideCount * slideWidth;

    $('#slider').css({width: slideWidth, height: slideHeight});

    $('#slider ul').css({width: sliderUlWidth, marginLeft: -slideWidth});

    $('#slider ul li:last-child').prependTo('#slider ul');

    function moveLeft() {
        $('#slider ul').animate({
            left: +slideWidth
        }, 200, function () {
            $('#slider ul li:last-child').prependTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    function moveRight() {
        $('#slider ul').animate({
            left: -slideWidth
        }, 200, function () {
            $('#slider ul li:first-child').appendTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    $('a.control_prev').click(function () {
        moveLeft();
    });

    $('a.control_next').click(function () {
        moveRight();
    });

});

