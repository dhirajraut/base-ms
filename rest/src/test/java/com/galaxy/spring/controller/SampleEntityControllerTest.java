package com.galaxy.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.galaxy.spring.model.SampleEntityVO;
import com.galaxy.spring.service.IEntityService;

@Transactional
public class SampleEntityControllerTest extends AbstractControllerTest {

	@Mock
	IEntityService<SampleEntityVO> sampleEntityService; // Service to mock.

	@InjectMocks
	SampleEntityController sampleEntityController; // Controller where the mocks to be injected.

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUp(sampleEntityController);
	}

	/**
	 * Mock data generator.
	 * 
	 * @param numberOfObjectsRequired
	 * @return
	 */
	private List<SampleEntityVO> getSampleEntityMockData(int numberOfObjectsRequired) {

		/* Create dummy object. */
		List<SampleEntityVO> sampleEntityList = new ArrayList<SampleEntityVO>();
		IntStream.rangeClosed(1, numberOfObjectsRequired).forEach(value -> sampleEntityList.add(createMockUser(value)));
		
		return sampleEntityList;
	}

	private SampleEntityVO createMockUser(int index) {
		SampleEntityVO sampleEntity = new SampleEntityVO();
		sampleEntity.setFirstName("Sharon" + index);
		sampleEntity.setLastName("Shelly");
		sampleEntity.setEmail("sharon.shelly@somecompany.com");
		return sampleEntity;
	}
	
	/**
	 * Test for GetAll
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAll() throws Exception {
		Mockito.when(sampleEntityService.findAll()).thenReturn(getSampleEntityMockData(3));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/entities").accept(MediaType.ALL)).andReturn();
		String contents = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();
		Mockito.verify(sampleEntityService, Mockito.times(1)).findAll();

		Assert.isTrue(HttpStatus.OK.value() == status, "Failure - Unable to get 200 status");
		Assert.notNull(contents, "Failure - Unable to get contents");
		Assert.isTrue(contents.length() > 0, "Failure - Unable to get contents size > 0");
	}

	/**
	 * Test Get specific
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserGet() throws Exception {
		List<SampleEntityVO> sampleEntityMockDataList = getSampleEntityMockData(1);
		SampleEntityVO mockUser = (SampleEntityVO) ((List<SampleEntityVO>) sampleEntityMockDataList).get(0);
		Mockito.when(sampleEntityService.findById(Mockito.anyLong())).thenReturn(mockUser);

		String uri = "/v1/entities/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.ALL)).andReturn();
		String contents = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();
		Mockito.verify(sampleEntityService, Mockito.times(1)).findById(Mockito.anyLong());

		Assert.isTrue(HttpStatus.OK.value() == status, "Failure - Unable to get 200 status");
		Assert.notNull(contents, "Failure - Unable to get contents");
		Assert.isTrue(contents.length() > 0, "Failure - Unable to get contents size > 0");
	}

	/**
	 * Test delete specific.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserDelete() throws Exception {
		Mockito.doNothing().when(sampleEntityService).deleteById(1L);

		String uri = "/v1/entities/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.ALL)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Mockito.verify(sampleEntityService, Mockito.times(1)).deleteById(Mockito.anyLong());

		Assert.isTrue(HttpStatus.OK.value() == status, "Failure - Unable to get 200 status");
	}

	@Test
	public void testUserPost() throws Exception {
		Iterable<SampleEntityVO> sampleEntityMockDataList = getSampleEntityMockData(1);

		Mockito.when(sampleEntityService.saveAll(Mockito.any(ArrayList.class))).thenReturn(sampleEntityMockDataList);

		String uri = "/v1/entities";
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri, sampleEntityMockDataList).contentType(MediaType.APPLICATION_JSON).content(""))
				.andReturn();
		String contents = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();
		Mockito.verify(sampleEntityService, Mockito.times(1)).saveAll(Mockito.any(ArrayList.class));

		Assert.isTrue(HttpStatus.CREATED.value() == status, "Failure - Unable to get 201 status");
		Assert.notNull(contents, "Failure - Unable to get contents");
		Assert.isTrue(contents.length() > 0, "Failure - Unable to get contents size > 0");
	}
}
