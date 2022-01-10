package com.pluralsight.clientApp.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.clientApp.models.Session;
import com.pluralsight.clientApp.repositories.SessionRepository;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

	@Autowired
	private SessionRepository sessionRepository;

	@GetMapping
	public List<Session> sessionList() {
		return sessionRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Session get(@PathVariable Long id) {
		return sessionRepository.getById(id);
	}

	@RequestMapping
	public Session create(@RequestBody Session session) {
		return sessionRepository.saveAndFlush(session);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		if (sessionRepository.findById(id) != null) {
			sessionRepository.deleteById(id);
			System.out.println("Succes!!!! ");
		} else {
			System.out.println("The entered ID " + id + " does not exists.");
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		Session existingSession = sessionRepository.getById(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		return sessionRepository.saveAndFlush(existingSession);
	}
}
