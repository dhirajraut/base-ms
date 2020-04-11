package com.galaxy.spring.service;

public interface IEntityService<T> {
	/**
	 * Returns all objects.
	 * 
	 * @return
	 */
	public Iterable<T> findAll();

	/**
	 * Creates new objects.
	 * 
	 * @param user
	 * @return
	 */
	public Iterable<T> saveAll(Iterable<T> objects);

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
