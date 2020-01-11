'use strict';
angular
	.module('myApp')
	.controller('TestController',['$scope','$http',function($scope, $http){
	var self = this;
	this.welcome = 'hello controller';
	$http.get("http://localhost:8080/mamuvi/test").then(function(data){
		self.hello = data.data.hello;
		console.log(data.data['hello'])
	})
}])