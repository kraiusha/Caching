package ru.kraiush.springMVC.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import ru.kraiush.springMVC.controller.AppRestController;
import ru.kraiush.springMVC.model.CacheUse;

public class AppCache {
	
	private static final Logger log = LoggerFactory.getLogger(AppRestController.class);
	
	public boolean toCache(int maxH, int maxD, boolean useDisk ) throws IllegalArgumentException, CacheException {		 
		
       //Initialization parameters
       Element elem;
       CacheUse cu;
       int i;
       boolean sw;
       CacheManager cacheManager;
       Configuration cacheManagerConfig;
       Ehcache cache;
       
	  try {  	
	    cacheManager = CacheManager.getInstance();	  
	    cacheManager.shutdown();	  
	
	    //Create a cache manager configuration
	    if (useDisk) {		  
	       cacheManagerConfig = new Configuration()
  	         .diskStore(new DiskStoreConfiguration()
	         .path("c:/.cache"));
	       CacheConfiguration cacheConfig = new CacheConfiguration()
	         .name("writeToCache")
	         .maxEntriesLocalHeap(maxH)
	         .maxBytesLocalDisk(maxD, MemoryUnit.BYTES)		         
	         .eternal(false)
	         .diskSpoolBufferSizeMB(20)
	         .timeToIdleSeconds(300) .timeToLiveSeconds(10)
	         .memoryStoreEvictionPolicy("LFU") 
	         .transactionalMode("off")
	         .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP));
		   cacheManagerConfig.addCache(cacheConfig);
		   cacheManager = new CacheManager(cacheManagerConfig);	 	
	    }   
	    else {		  
		   cacheManagerConfig = new Configuration();
		   CacheConfiguration cacheConfig = new CacheConfiguration()
		     .name("writeToCache")
		     .maxEntriesLocalHeap(maxH)		     
		     .maxEntriesLocalDisk(maxD)   	     
		     .eternal(false)
	         .diskSpoolBufferSizeMB(20)
	         .timeToIdleSeconds(300) .timeToLiveSeconds(10)
	         .memoryStoreEvictionPolicy("LFU") 
	         .transactionalMode("off")
		     .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP));
		   cacheManagerConfig.addCache(cacheConfig);
		   cacheManager = new CacheManager(cacheManagerConfig);		  
	   }
	  	  
       //Create a cache manager	
	   cacheManager = CacheManager.newInstance(cacheManagerConfig);
       //cacheManager.clearAll();
	   cache = cacheManager.getCache("writeToCache");
	   cache.removeAll();		  
	   sw= false;

       for (i=1; i <= 10; i++) {      	  
        	String key = "key" + i;
        	String dummy = "dummy" + i;
    		cu = new CacheUse(dummy);
    		//Add element to cache
	    	cache.put(new Element(key,  cu));
	    	 slowQuery(1000L);
		    //Get the element from cache				
	        elem = cache.get(key);
	        cu= (CacheUse)elem.getObjectValue();
	        System.out.println("Caching is running..."); 
	        log.debug("Entry : {}", cu.getEntry());	 
	    }
        System.out.println("<---  Check the last record  --->");
        String smax= "key10";
        elem = cache.get(smax);
        cu= (CacheUse)elem.getObjectValue();
        log.debug("Entry : {}", cu.getEntry());
        System.out.println("smax Key: " + cache.isKeyInCache(smax));        
        sw= cache.isKeyInCache(smax);
	} catch (Exception ex) {	
		String err= "The testing caching is passed with exception!";
		System.out.println(err);
		log.debug(err);
        return false;
       
	}    //Call shutdown to close the cache manager
	    cacheManager.shutdown();
		return sw;
	}
	private void slowQuery(long seconds){
		try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
	}
}
