package com.jsonDemo.jsonReaderDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonDemo.jsonReaderDemo.pojo.RootData;
import com.jsonDemo.jsonReaderDemo.service.JsonService;

@RestController
@RequestMapping("api/jsonReader")
public class JsonFileReader {

	@Autowired
	JsonService jService;
	
	@PostMapping("/jsonCreate")
	public RootData createJson(@RequestBody RootData rootData) {
		return this.jService.createJson(rootData);
	}
}
