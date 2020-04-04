package com.galaxy.spring.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Common interface for all controllers.
 */
@RestController
public interface IController<T> {

	

	/**
	 * Returns all objects.
	 * 
	 * @return
	 */
	public Iterable<T> findAll();

	/**
	 * Creates a new object.
	 * 
	 * @param objectList
	 * @return
	 */
	public Iterable<T> save(T object);

	/**
	 * Creates new objects.
	 * 
	 * @param objectList
	 * @return
	 */
	public Iterable<T> saveAll(Iterable<T> objectList);

	
	/**
	 * Updates an object
	 * @param id
	 * @param object
	 * @return
	 */
	public Iterable<T> update(Long id, T object);
	
	/**
	 * Deletes an object.
	 * 
	 * @param id
	 */
	public void deleteById(Long id);

	/**
	 * Finds object by id.
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Long id);
}
