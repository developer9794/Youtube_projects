 
   var app = angular.module('myApp', []);
   app.controller('myController', function($scope, $http,$location) {
       $scope.menus = [];
         $scope.restaurants;
         $scope.cooks =[];
$scope.uniqueCategories=[];
 $scope.customer= {};
 $scope.queryParams = {};
  $scope.tblinfo={};
  $scope.sessionrestid;
  $scope.sessiontblid;
   $scope.sessioncustId;
  $scope.showorder=false;
   $scope.sessioncust= {};
 function setLocalStorageItem(key, value) {
	
     localStorage.setItem(key, value);
   }

   // Function to retrieve a value from local storage
   function getLocalStorageItem(key) {
     return localStorage.getItem(key);
   }

   // Usage example
   
  $scope.formData = {};

$scope.logincustmer = function() {
 
  console.log('Form data submitted:', $scope.formData);
  $http.post('http://192.168.0.103:8080/api/customers',$scope.formData)
        .then(function(response) {
            // Handle the success response here
           $scope.customer = response.data;
             setLocalStorageItem("custId",$scope.customer);
             
           closeModal();
        })
        .catch(function(error) {
            // Handle the error here
            console.error('Error making POST request:', error);
        });
};


$scope.autocall = function() {
//    	   alert("dafsadf");
    	 
           $http.get('http://192.168.0.103:8080/api/restaurants/session').then(function(response) {
              response.data;
            
              setLocalStorageItem('restid', response.data.restid);
   setLocalStorageItem('tblId',response.data.tableId);
      $scope.sessionrestid = getLocalStorageItem('restid');
    $scope.sessiontblid = getLocalStorageItem('tblId');
$scope.formData.restId= $scope.sessionrestid;
$scope.formData.tblId=$scope.sessiontblid;
  $scope.sessioncustId =getLocalStorageItem('custId');
   console.log('restid:', $scope.sessionrestid );
   console.log('tblid:',$scope.sessiontblid);
 
   if (! $scope.sessionrestid  || !$scope.sessiontblid ) {
    // One or both values are empty or undefined
    alert("Scan QR Code !");
    console.log('One or both values are empty or undefined.');
} else {
 console.log("Customerid ="+  $scope.sessioncustId)
      if (!$scope.sessioncustId) {
		  console.log("Customerid ="+  $scope.sessioncustId)
		    openModal();
    $scope.fetchtablenamebyid($scope.sessiontblid);
		 
	  }else{
		  
	  }
    
     
    	  $scope.fetchcooks ();
    	    $scope.fetchMenus();
    	    $scope.fetchrestorent();
          
    console.log('Both values are not empty and defined.');
}
   
    	
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
        $scope.fetchtablenamebyid = function(id) {
    	  // alert("dafsadf");
    	 
    	  
    	   
           $http.get('http://192.168.0.103:8080/api/tables/'+id).then(function(response) {
               $scope.tblinfo= response.data;
              // alert( JSON.parse($scope.restaurants));
             
       $scope.formData.tbl=$scope.tblinfo.tblName;
        console.log('table name:', $scope.tblinfo.tblName);
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       $scope.fetchMenus = function() {
    	  // alert("dafsadf");
    	 
    	  
    	   
           $http.get('http://192.168.0.103:8080/api/menus/'+ $scope.sessionrestid).then(function(response) {
               $scope.menus= response.data;
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
           $http.get('http://192.168.0.103:8080/api/restaurants/'+ $scope.sessionrestid).then(function(response) {
        	   
               $scope.restaurants = response.data;
         
           
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
       $scope.fetchcooks = function() {
           $http.get('http://192.168.0.103:8080/api/cooks/'+ $scope.sessionrestid).then(function(response) {
        	   
               $scope.cooks = response.data;
         
           
           
           }).catch(function(error) {
               console.error('Error fetching menus:', error);
           });
       };
       
      
      
    	   $scope.order = []; // Initialize an empty order array
    	      $scope.orderEntities = [];
    	  
    	   $scope.orderprice;
    	   $scope.addToOrder = function(menu) {
    		     
    	       $scope.order.push(menu);
    	       $scope.orderprice;
    	     $scope.showorder=true;
    	       menu.addToCartDisabled = true; // Disable the button
    	      // menu.menuFoodqty = 0; // Reset the quantity to 0
    	     }
    	     
    	     
    	   $scope.myorderadd = function() {
     $scope.showorder=false;
    if (!$scope.sessioncustId) {
		    openModal();
    $scope.fetchtablenamebyid($scope.sessiontblid);
	 
	  }else{
		 console.log("cust id"+$scope.sessioncustId)
		 $scope.orderEntities1 = removeDuplicates($scope.orderEntities1);
		$scope.requestData = {
  
    "table": {
        "tblId": $scope.sessiontblid
    },
    "customer": {
        "custId": $scope.sessioncustId
    },
    "restaurant": {
        "restId": $scope.sessionrestid
    },
    "totalprice": $scope.orderprice,
    "datetime": "2023-09-01",
    "orderEntities":$scope.orderEntities1
    
}


var jsonString = JSON.stringify($scope.requestData);

// Now, jsonString contains the JSON representation of your object
console.log(jsonString);
  $scope.order = []; // Initialize an empty order array
    	      $scope.orderEntities1 = [];

    $http.post('http://192.168.0.103:8080/orders/post',$scope.requestData)
        .then(function(response) {
            // Handle the success response here
            $scope.cooks = response.data;
              
        })
        .catch(function(error) {
            // Handle the error here
            console.error('Error making POST request:', error);
        });
		
	}
    

};
function removeDuplicates(arr) {
    return arr.filter((item, index) => arr.indexOf(item) === index);
}
   // Initialize an empty order array
    	      $scope.orderEntities1 = [];
    	   $scope.changeCount = function(menu, delta) {
			   $scope.order1 = {}; 
    	     if (!menu.menuFoodqty) {
    	       menu.menuFoodqty = 0;
    	     }
    	     menu.menuFoodqty += delta;
    	     if (menu.menuFoodqty < 0) {
    	       menu.menuFoodqty = 0;
    	     }
    	    
    	         menu.totalPrice = menu.menuFoodprice * menu.menuFoodqty;
    	     //$scope.addToOrder(menu);
    	         let total = 0;
      	       for (const item of $scope.order) {
      	         total += item.total;
      	       }
      	       menu.total=total;
      	       $scope.orderprice=total;
      	       
//      	       $scope.orderEntitiesdata= {};
//    		$scope.orderEntitiesdata.ordFoodcategory=	menu.menuFoodcategory;
// $scope.orderEntitiesdata.ordFood=menu.menuFood;
// $scope.orderEntitiesdata.ordFoodinfo=menu.menuFoodinfo;
// $scope.orderEntitiesdata.ordFoodtest=menu.menuFoodtest;
// $scope.orderEntitiesdata.ordFoodqty=menu.menuFoodqty;
// $scope.orderEntitiesdata.ordFoodprice=menu.totalPrice;
//				

    	      $scope.orderEntities1.push(menu)
      	       
        	  //$scope.orderEntities1.push(menu);
        	  var jsonString = JSON.stringify($scope.orderEntities1);
console.log("++++++"+jsonString);
    	   };

    	   $scope.getCount = function(qty) {
			 
    	     return qty || 0;
    	   };
    	   $scope.calculateTotalPrice = function() {
    		    let total = 0;
    		    for (const item of $scope.order) {
    		      total += item.totalPrice;
    		    }
    		    return total;
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
	



   