package com.galaxy.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.galaxy.spring.SampleEntityClient;
//import com.galaxy.spring.exception.IntegrityViolationException;
import com.galaxy.spring.model.SampleEntityVO;

@Transactional
public class SampleEntityClientTest extends AbstractServiceTest {

	@Autowired
	SampleEntityClient SampleEntityClient;

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
	public void testFindExistingUser() throws EntityNotFoundException {
		long existingUserIdToBeFound = 1;
		Assert.notNull(SampleEntityClient.findById(existingUserIdToBeFound),
				"Failure - Could not find entity with id: " + existingUserIdToBeFound);
	}

/*
	@Test
	public void testFindAll() {
		
		Iterable<SampleEntityVO> findAll = SampleEntityClient.findAll();
		Assert.notNull(SampleEntityClient.findAll(), "No users found.");
	}

	@Test
	public void testFindNonExistingUser() throws EntityNotFoundException {
		long nonExistingUserIdToBeFound = 1000;
		expectedException.expect(EntityNotFoundException.class);
		expectedException.expectMessage("Couldn't find entity with id: " + nonExistingUserIdToBeFound);

		userService.findById(nonExistingUserIdToBeFound);
	}

	@Test
	public void testDeleteExistingUser() throws EntityNotFoundException {
		long existingUserIdToBeDeleted = 1;

		userManagementClient.deleteById(existingUserIdToBeDeleted);

		expectedException.expect(EntityNotFoundException.class);
		expectedException.expectMessage("Couldn't find entity with id: " + existingUserIdToBeDeleted);
		UserVO deletedUser = userManagementClient.findById(existingUserIdToBeDeleted);

		Assert.isTrue(true, "Failure - Could not get delete flag for entity with id: " + existingUserIdToBeDeleted);
	}

	@Test
	public void testSaveUser() throws IntegrityViolationException, EntityNotFoundException {

		UserVO user = new UserVO();
		user.setFirstName("Fn");
		user.setLastName("Ln");
		user.setEmail("fn.ln@something.com");
		user.setWeb("something.com");
		List<UserVO> userVOList = new ArrayList<UserVO>();
		userVOList.add(user);

		List<UserVO> savedObjectList = (List<UserVO>) userService.saveAll((Iterable<UserVO>) userVOList);

		Assert.notNull(savedObjectList.size() == userVOList.size(), "Failure: couldnot save user.");
	}
	*/
}
