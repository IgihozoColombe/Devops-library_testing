package com.sgic.library.services;

import com.sgic.library.entities.MainClassification;

import javax.validation.Valid;
import java.util.List;


public interface MainClassificationService {

	void saveMCL(@Valid MainClassification mcl); // save main classification
	MainClassification findMclById(long id); //find by id
	List<MainClassification> getAllMain();   //List all Main classifaction
}
