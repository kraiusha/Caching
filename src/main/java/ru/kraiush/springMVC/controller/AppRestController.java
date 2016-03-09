package ru.kraiush.springMVC.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.kraiush.springMVC.ehcache.AppCache;
import ru.kraiush.springMVC.model.Attribute;
import ru.kraiush.springMVC.model.Message;

@RestController
public class AppRestController {
	
	private static final Logger log = LoggerFactory.getLogger(AppRestController.class);
	
    //------------------- Processing Connection ------------------------------
    
    @RequestMapping(value = "/connect/", method = RequestMethod.PUT)
    public Message connectPut(@RequestBody Attribute attribute) {           	    	      	     		
    	
    	boolean useHeap = attribute.isUf_01();
		boolean useDisk = attribute.isUf_02();
		int maxH= Math.abs(attribute.getFeature_01());
		int maxD= Math.abs(attribute.getFeature_02());
		if (!useHeap) {maxH= 1;}
		if (!useDisk) {maxD=1;}
		if ((useHeap && useDisk) && (maxH < maxD)) {maxH= 1;}
		if (useDisk) {maxD= maxD*65;}		
		String mess = null;
    	
    	AppCache seeCache = new AppCache();
		boolean sw = seeCache.toCache(maxH, maxD, useDisk);
		
		if (sw) {
			mess= "Testing the caching is passed successful!";
		} else {
			mess= "For successful completion of the test, enter at least 10 values of the parametr!";
		}
		
		System.out.println(mess);
		log.debug(mess);

	    Message answer = new Message(mess);	    
		return answer;        
    }
 }