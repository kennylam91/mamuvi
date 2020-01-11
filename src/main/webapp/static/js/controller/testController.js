'use strict';
angular.module('myApp').controller('TestController',['$scope',function($scope){
	var self = this;
	$scope.hello = 'hello world';
	this.welcome = 'hello controller';
}])