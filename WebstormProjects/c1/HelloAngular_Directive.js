/**
 * Created by wang on 15/04/16.
 */
var myModule = angular.module("MyModule", []);
myModule.directive("hello", function() {
    return {
        restrict: 'E',
        template: '<div>Hi everyone!</div>',
        replace: true
    }
});