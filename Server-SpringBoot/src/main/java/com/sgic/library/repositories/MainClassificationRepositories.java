package com.sgic.library.repositories;

import com.sgic.library.entities.MainClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainClassificationRepositories extends JpaRepository <MainClassification, Long> {
	MainClassification findById(long id); // find by id
}
