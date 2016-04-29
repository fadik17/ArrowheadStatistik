/**
 * Created by wang on 15/04/16.
 */
var myModule = angular.module("HelloAngular", []);

myModule.controller("helloAngular", ['$scope',
    function HelloAngular($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);