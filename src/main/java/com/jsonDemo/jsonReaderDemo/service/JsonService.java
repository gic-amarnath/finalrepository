package com.jsonDemo.jsonReaderDemo.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonDemo.jsonReaderDemo.pojo.RootData;

@Service
public interface JsonService {

	public RootData createJson(RootData rootData);
}
