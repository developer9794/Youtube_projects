var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope,$http) {
  $scope.firstName= "John";
  $scope.lastName= "Doe";
  $scope.employees;
 
  $scope.Docker=function() {
	 $http({
      method: 'GET',
      url: '/data'
   }).then(function (response){
 $scope.employees = response;
            alert($scope.employees)
            alert("edsad");
            console(response);
   },function (error){
 alert("edsad");
   });
 
	 
               }
            

 
});