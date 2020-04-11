package com.galaxy.spring.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.galaxy.spring.entity.SampleEntity;
import com.galaxy.spring.exception.EntityNotFoundException;
import com.galaxy.spring.exception.IntegrityViolationException;
import com.galaxy.spring.jpa.ISampleEntityRepository;
import com.galaxy.spring.model.SampleEntityVO;
import com.google.common.reflect.TypeToken;

@Service
public class SampleEntityService implements IEntityService<SampleEntityVO> {

	@Autowired
	ISampleEntityRepository repository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Iterable<SampleEntityVO> findAll() {
		List<SampleEntity> sampleEntityList = repository.findAll(Sort.by(Direction.ASC, "firstName"));
		List<SampleEntityVO> sampleEntityVOList = modelMapper.map(sampleEntityList, new TypeToken<List<SampleEntityVO>>() {
		}.getType());
		return sampleEntityVOList;
	}

	@Override
	public Iterable<SampleEntityVO> saveAll(Iterable<SampleEntityVO> sampleVOList)
			throws IntegrityViolationException, EntityNotFoundException {
		Iterable<SampleEntity> sampleEntityList = modelMapper.map(sampleVOList, new TypeToken<List<SampleEntity>>() {
		}.getType());

		try {
			Iterable<SampleEntity> savedEntities = repository.saveAll(sampleEntityList);
			Iterable<SampleEntityVO> savedSampleEntityVOList = modelMapper.map(savedEntities, new TypeToken<List<SampleEntityVO>>() {
			}.getType());
			return savedSampleEntityVOList;
		} catch (DataIntegrityViolationException exception) {
			throw new IntegrityViolationException("Could not save object(s)", exception);
		}
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Couldn't find entity with id: " + id);
		}
	}

	@Override
	public SampleEntityVO findById(Long id) throws EntityNotFoundException {
		SampleEntity sampleEntity = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn't find entity with id: " + id));
		SampleEntityVO sampleVO = modelMapper.map(sampleEntity, SampleEntityVO.class);
		return sampleVO;
	}

}
