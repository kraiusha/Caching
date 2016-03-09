<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Angulars appliction</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">	
	<link rel="stylesheet" href='static/css/app.css' type="text/css"></link>	
	<script type="text/javascript" src="libs/angular.js"></script>	
	<script type="text/javascript"  src="libs/jquery.js"></script>
	<script type="text/javascript" src="static/js/app.js"></script>
	<script src="<c:url value='/static/js/service/see_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/see_controller.js' />"></script>
</head>
<body ng-app="seeApp" ng-controller="SeeController as ctrl">

    <div class="title" align="center">
         <p>Try to use Caching!</p>     
    </div>
    <div class= "radio">&nbsp;
       <strong class="label">Heap Memory is used?</strong>
          <label><input type="radio" ng-value="true" ng-model="ctrl.attribute.uf_01" />Yes </label>
          <label><input type="radio" ng-value="false" ng-model="ctrl.attribute.uf_01"/>No </label> 
          &nbsp;&nbsp;
       <strong class="label">Disk storage is used?</strong>
          <label><input type="radio" ng-value="true" ng-model="ctrl.attribute.uf_02" />Yes </label>
          <label><input type="radio" ng-value="false" ng-model="ctrl.attribute.uf_02"/>No </label>      
       </div>
     
<div class="generic-container">
  <div class="panel panel-default"align="center">
    <div class="panel-heading"><span class="lead formTitle">Enter parametres of Cache configuration</span></div>
      <div class="formcontainer" >
       <form ng-submit="ctrl.submitAttr()" name="attrForm" " class="form-horizontal">
        
        <div class="row" ng-show="ctrl.attribute.uf_01" >
         <div class="form-group col-xs-12">
           <label class="col-xs-10 control-lable entryTitle" for="file">Enter maximum number of objects to be held in the Memory Store</label>
           <div class="col-xs-7">  
             <input type="number" ng-model="ctrl.attribute.feature_01" name="feature_01" 
                    placeholder="max Entries Local Heap" required ng-minlength="1" /><br/><br/>
               <div class="has-error" ng-show="attrForm.$dirty">  
                 <span ng-show="attrForm.feature_01.$error.required">This is a required field</span>                                                
               </div>
           </div>
         </div>
        </div>        

        <div class="row" ng-show="ctrl.attribute.uf_02">
         <div class="form-group col-xs-12">
           <label class="col-xs-10 control-lable entryTitle" for="file">Enter maximum number of entries for the Disk Store</label>
           <div class="col-xs-7">  
             <input type="number" ng-model="ctrl.attribute.feature_02" name="feature_02"
                    placeholder="max Entries Local Disk" required ng-minlength="1" /><br/><br/>
               <div class="has-error" ng-show="attrForm.$dirty">  
                 <span ng-show="attrForm.feature_02.$error.required">This is a required field</span>                                                
               </div>
           </div>
         </div>
        </div>
                                         
        <div class="row">
           <div class="form-actions floatRight">               
               <button type="button" ng-click="ctrl.submitAttr()" class="buttonStyle">Submit</button>                 
           </div>
        </div>          
      </form>
     </div>
    </div>
   </div>
  </div>  
  
   <div class="except" class="ng-cloak" ng-show="!ctrl.attribute.uf_01 && !ctrl.attribute.uf_02">    	  	   
	  <span type="text"  ng-bind="ctrl.except"></span>
  </div>
  
  <div ng-repeat="a in ctrl.answers"" class="answer" ng-show="ctrl.sended">   		            		                                                                  
          <span ng-bind="a.answer"></span>                                                                                                                                 
  </div>
  
  <div class="sended" class="ng-cloak" ng-show="ctrl.sended" >    	  	   
	  <span type="text"  ng-bind="ctrl.result"></span>
  </div>

  <div class="waiting" class="ng-cloak" ng-show="ctrl.waiting">    	  	   
	  <span type="text"  ng-bind="ctrl.wait"></span>
  </div>
   
</body>
</html>