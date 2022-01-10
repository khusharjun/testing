package com.pluralsight.clientApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.clientApp.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
