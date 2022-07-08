package com.jsonDemo.jsonReaderDemo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDatum {
	public String taskName;
	public String taskId;
	public String taskBy;
	public String taskSerialNo;
	@JsonProperty("taskSpecs")
	@JsonAlias("result")
	public TaskSpecs taskSpecs;
}
