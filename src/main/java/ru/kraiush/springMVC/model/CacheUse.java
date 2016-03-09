package ru.kraiush.springMVC.model;

import java.io.Serializable;

import org.springframework.cache.annotation.CacheEvict;

public class CacheUse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String entry;

	public String getEntry() {
		return entry;
	}
	@CacheEvict(value = "emp", allEntries=true)
	public void setEntry(String entry) {
		this.entry = entry;
	}

	public CacheUse( String entry) {
		super();		
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "from CacheUse [entry=" + entry + "]";
	}

}