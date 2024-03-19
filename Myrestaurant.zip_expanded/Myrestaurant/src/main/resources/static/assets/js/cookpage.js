 
   var app = angular.module('myApp', []);
   app.controller('mycookController', function($scope, $http) {
       $scope.menus = [];
         $scope.restaurants;
         $scope.cooks =[];
           $scope.order = []; // Initialize an empty order array
    	   $scope.orderprice;
    	  
$scope.uniqueCategories=[];
       $scope.fetchMenus = function() {
    	  // alert("dafsadf");
    	  $scope.fetchcooks ();
    	   $scope.fetchrestorent();//+$scope.restaurants.restid
           $http.get('http://192.168.0.103:8080/api/orders/').then(function(response) {
               $scope.order = response.data;
              // alert( JSON.parse($scope.restaurants));
              
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
           $http.get('http://192.168.0.103:8080/api/restaurants/101').then(function(response) {
        	   
               $scope.restaurants = response.data;
         
           
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
       $scope.fetchcooks = function() {
           $http.get('http://192.168.0.103:8080/api/cooks').then(function(response) {
        	   
               $scope.cooks = response.data;
         
           
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
      
      
    	 
    		  
   });
   

   function openModal() {
		  const modalElement = document.getElementById('exampleModal');
		  if (modalElement) {
		    modalElement.classList.add('show');
		    modalElement.style.display = 'block';
		    document.body.classList.add('modal-open');
		  }
		}
	
   
   function closeModal() {
		  const modalElement = document.getElementById('exampleModal');
		  if (modalElement) {
		    modalElement.classList.add('hide');
		    modalElement.style.display = 'none';
		    document.body.classList.add('modal-open');
		  }
		}
	



   