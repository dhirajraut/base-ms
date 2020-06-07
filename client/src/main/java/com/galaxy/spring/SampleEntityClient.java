package com.galaxy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.galaxy.spring.model.SampleEntityVO;
import com.galaxy.spring.service.IEntityService;

@Service
public class SampleEntityClient implements IEntityService<SampleEntityVO>{

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${service_rest_url}")
	private String serverUrl;
	
	@Override
	public Iterable<SampleEntityVO> findAll() {
		ResponseEntity<Iterable> result = restTemplate.exchange(serverUrl, HttpMethod.GET, new HttpEntity<String>("parameters", null), Iterable.class);
		return result.getBody();
	}

	@Override
	public Iterable<SampleEntityVO> saveAll(Iterable<SampleEntityVO> objects) {
		restTemplate.exchange(serverUrl, HttpMethod.PUT, new HttpEntity<String>("parameters", null), Void.class);
		//@TODO
		return null;
	}

	@Override
	public void deleteById(Long id) {
		restTemplate.delete(serverUrl + "/" + id);
	}

	@Override
	public SampleEntityVO findById(Long id) {
		ResponseEntity<SampleEntityVO> result = restTemplate.exchange(serverUrl, HttpMethod.GET, new HttpEntity<String>("parameters", null), SampleEntityVO.class);
		return result.getBody();
	}

}
