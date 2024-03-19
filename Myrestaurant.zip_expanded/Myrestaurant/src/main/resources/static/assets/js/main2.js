 var app = angular.module('myApp', []);
   app.controller('myController', function($scope, $http) {
       $scope.menus = [];
         $scope.restaurants;
$scope.uniqueCategories=[];
       $scope.fetchMenus = function() {
		    fetchrestorent();
           $http.get('http://localhost:8080/api/menus/').then(function(response) {
               $scope.menus = response.data;
            $scope.uniqueCategories = getUniqueCategories($scope.menus);
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
        function getUniqueCategories(menus) {
        var categories = [];
        angular.forEach(menus, function(menu) {
            if (categories.indexOf(menu.menuFoodcategory) === -1) {
                categories.push(menu.menuFoodcategory);
            }
        });
        return categories;
    }
    
    
    $scope.fetchrestorent = function() {
           $http.get('http://localhost:8080/api/restaurants/101').then(function(response) {
               $scope.restaurants = response.data;
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
   });
