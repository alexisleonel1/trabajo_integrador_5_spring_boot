package com.despensa.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<t> {

	public boolean create(t t);
	
	public List<t> findAll();
	
	public Optional<t> find(int id);
	
	public boolean update(t t);
	
	public void delete(int id);
}