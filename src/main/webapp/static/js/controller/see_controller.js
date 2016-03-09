'use strict';

App.controller('SeeController', ['$scope', 'TicketService', function($scope, TicketService) {
        var self = this;   
        self.attribute ={feature_01:'', feature_02:'',uf_01:'',uf_02:''}; 
        self.attribute.uf_01= true;
        self.attribute.uf_02= true;
        self.attribute.feature_01= 1;
        self.attribute.feature_02= 1;         
        self.except= "Need to use at least one parametr!";        
        self.result= "The test result you can see on the server console and log file";
        self.wait= "Waiting for answer from server";
        self.sended= false;
        self.waiting= false;
        self.answers= []; 
        
        self.connectPut = function(attribute){
              TicketService.connectPut(attribute)
		              .then(   
		            		  function(d) { 
		            			    self.sended= true;
		            			    self.waiting= false;
		            			    while(self.answers.length > 0) {
		            			    	self.answers.pop();
		            			    }		            			    
	    					    	self.answers.push(d);    					    	
	    					    	console.log('Have Got answer from Server');
	    					  },
				              function(errResponse){
					               console.error('Error while connecting.');
				              }	
                  );
        }; 
          
        self.submitAttr = function() {        	         
        	console.log('Form is submitted for f_01:', self.attribute.feature_01, " and f_02: ",self.attribute.feature_02,
          		  " uf_01: ",self.attribute.uf_01, " uf_02: ",self.attribute.uf_02);
        	self.sended= false;
        	self.waiting= true;
            if (!self.attribute.uf_01) {          	            	            	  
          	     self.attribute.feature_01= 1;          	             	             
            } 
            if (!self.attribute.uf_02) {
            	self.attribute.feature_02= 1;
            }
      	      if (self.attribute.feature_01 != 0 || self.attribute.feature_02 != 0) {    	    	        	    	         	          	    	  
                console.log('Connecting');                
                self.connectPut(self.attribute);                               
      	    }                                     
        };
      }]);
