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
import com.pluralsight.clientApp.models.Speaker;
import com.pluralsight.clientApp.repositories.SpeakerRepository;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {

	@Autowired
	private SpeakerRepository speakerRepository;

	@GetMapping
	public List<Speaker> speakersList() {
		return speakerRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.getById(id);
	}

	@RequestMapping
	public Speaker create(@RequestBody Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		if (speakerRepository.findById(id) != null) {
			speakerRepository.deleteById(id);
			System.out.println("Success!!! ");
		} else {
			System.out.println("The entered ID does match: " + id);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerRepository.getById(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerRepository.saveAndFlush(existingSpeaker);
	}

}
