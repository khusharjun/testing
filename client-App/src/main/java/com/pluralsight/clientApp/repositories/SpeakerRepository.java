package com.pluralsight.clientApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.clientApp.models.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

}
