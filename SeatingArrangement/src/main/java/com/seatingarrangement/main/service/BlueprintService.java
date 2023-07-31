package com.seatingarrangement.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatingarrangement.main.model.Blueprint;
import com.seatingarrangement.main.repository.BlueprintRepository;

@Service
public class BlueprintService {

	@Autowired
	private BlueprintRepository blueprintRepository;
	
	public Blueprint insert(Blueprint blueprint) {
		
		return blueprintRepository.save(blueprint);
	}

	public List<Blueprint> getAllStudentDetails() {
		
		return blueprintRepository.findAll();
	}

	public Blueprint getById(int id) {
		Optional<Blueprint> optional=blueprintRepository.findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}

}
