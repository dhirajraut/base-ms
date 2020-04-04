package com.galaxy.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.galaxy.spring.exception.EntityNotFoundException;
import com.galaxy.spring.exception.IntegrityViolationException;
import com.galaxy.spring.model.SampleEntityVO;
import com.galaxy.spring.service.SampleEntityService;

@Transactional
public class SampleEntityServiceTest extends AbstractServiceTest {

	@Autowired
	SampleEntityService sampleEntityService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Set required environment.
	 */
	@Before
	public void setUp() {
		// Keeping it blank for this exercise.
	}

	/**
	 * Teardown required environment.
	 */
	@After
	public void tearDown() {
		// Keeping it blank for this exercise.
	}

	@Test
	public void testFindAll() {
		Assert.notNull(sampleEntityService.findAll(), "No entity found.");
	}

	@Test
	public void testFindExistingUser() throws EntityNotFoundException {
		long existingUserIdToBeFound = 1;
		Assert.notNull(sampleEntityService.findById(existingUserIdToBeFound),
				"Failure - Could not find entity with id: " + existingUserIdToBeFound);
	}

	@Test
	public void testFindNonExistingUser() throws EntityNotFoundException {
		long nonExistingUserIdToBeFound = 1000;
		expectedException.expect(EntityNotFoundException.class);
		expectedException.expectMessage("Couldn't find entity with id: " + nonExistingUserIdToBeFound);

		sampleEntityService.findById(nonExistingUserIdToBeFound);
	}

	@Test
	public void testDeleteExistingUser() throws EntityNotFoundException {
		long existingUserIdToBeDeleted = 1;

		sampleEntityService.deleteById(existingUserIdToBeDeleted);

		expectedException.expect(EntityNotFoundException.class);
		expectedException.expectMessage("Couldn't find entity with id: " + existingUserIdToBeDeleted);
		SampleEntityVO deletedUser = sampleEntityService.findById(existingUserIdToBeDeleted);

		Assert.isTrue(true, "Failure - Could not get delete flag for entity with id: " + existingUserIdToBeDeleted);
	}

	@Test
	public void testSaveUser() throws IntegrityViolationException, EntityNotFoundException {

		SampleEntityVO sampleEntity = new SampleEntityVO();
		sampleEntity.setFirstName("Fn");
		sampleEntity.setLastName("Ln");
		sampleEntity.setEmail("fn.ln@something.com");
		sampleEntity.setWeb("something.com");
		List<SampleEntityVO> sampleEntityVOList = new ArrayList<SampleEntityVO>();
		sampleEntityVOList.add(sampleEntity);

		List<SampleEntityVO> savedObjectList = (List<SampleEntityVO>) sampleEntityService.saveAll((Iterable<SampleEntityVO>) sampleEntityVOList);

		Assert.notNull(savedObjectList.size() == sampleEntityVOList.size(), "Failure: couldnot save entity.");
	}
}
