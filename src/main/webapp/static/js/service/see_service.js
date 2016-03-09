'use strict';

App.factory('TicketService', ['$http', '$q', function($http, $q){

	return {
		    connectPut: function(attribute){
					return $http.put('http://localhost:8080/Caching/connect/',attribute)					
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while connecting');
										return $q.reject(errResponse);
									}
							);
		    }		    
	};

}]);
