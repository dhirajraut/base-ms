package com.galaxy.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.spring.service.IService;
import com.galaxy.spring.model.SampleEntityVO;

@RestController
@RequestMapping(path = "v1/" + RestConstants.URL_ENTITIES_BASE)
public class SampleEntityController implements IController<SampleEntityVO> {

	@Autowired
	IService<SampleEntityVO> sampleEntityService;

	@RequestMapping(method = RequestMethod.GET, path = RestConstants.URL_ENTITIES_BYID)
	@Override
	public SampleEntityVO findById(/* Variable in URL path */ @PathVariable Long id) {
		return sampleEntityService.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@Override
	public Iterable<SampleEntityVO> findAll() {
		return sampleEntityService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public Iterable<SampleEntityVO> save(SampleEntityVO sampleEntityVO) {
		List<SampleEntityVO> sampleEntityVOList = new ArrayList<SampleEntityVO>();
		sampleEntityVOList.add(sampleEntityVO);
		return sampleEntityService.saveAll(sampleEntityVOList);
	}

	// @RequestMapping(method = RequestMethod.POST)
	// @ResponseStatus(HttpStatus.CREATED)
	// @Override
	// @TODO How to add list of new objects?
	public Iterable<SampleEntityVO> saveAll(Iterable<SampleEntityVO> sampleEntityVOList) {
		return sampleEntityService.saveAll(sampleEntityVOList);
	}

	@RequestMapping(method = RequestMethod.PUT, path = RestConstants.URL_ENTITIES_BYID)
	@ResponseStatus(HttpStatus.OK)
	@Override
	public Iterable<SampleEntityVO> update(/* Variable in URL Path */ @PathVariable Long id, SampleEntityVO sampleEntityVO) {
		sampleEntityVO.setId(id);
		List<SampleEntityVO> sampleEntityVOList = new ArrayList<SampleEntityVO>();
		sampleEntityVOList.add(sampleEntityVO);
		return sampleEntityService.saveAll(sampleEntityVOList);
	}

	// @TODO How to patch the objects?
	@RequestMapping(method = RequestMethod.PATCH, path = RestConstants.URL_ENTITIES_BYID)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<SampleEntityVO> partialUpdate(/* Variable in URL Path */ @PathVariable Long id, SampleEntityVO sampleEntityVO) {
		sampleEntityVO.setId(id);
		List<SampleEntityVO> sampleEntityVOList = new ArrayList<SampleEntityVO>();
		sampleEntityVOList.add(sampleEntityVO);
		return sampleEntityService.saveAll(sampleEntityVOList);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = RestConstants.URL_ENTITIES_BYID)
	@Override
	public void deleteById(/* Variable in URL path */ @PathVariable Long id) {
		sampleEntityService.deleteById(id);
	}


}
