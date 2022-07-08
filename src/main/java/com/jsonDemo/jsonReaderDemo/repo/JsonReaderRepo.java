package com.jsonDemo.jsonReaderDemo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
//import javax.persistence.Id;
public interface JsonReaderRepo<T> extends MongoRepository<T,Id> {

}
