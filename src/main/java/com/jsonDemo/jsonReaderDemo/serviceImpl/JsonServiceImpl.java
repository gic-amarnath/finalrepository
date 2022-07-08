package com.jsonDemo.jsonReaderDemo.serviceImpl;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jsonDemo.jsonReaderDemo.pojo.ComponentScoping;
import com.jsonDemo.jsonReaderDemo.pojo.Components;
import com.jsonDemo.jsonReaderDemo.pojo.DataDatum;
import com.jsonDemo.jsonReaderDemo.pojo.MultiEngineResults;
import com.jsonDemo.jsonReaderDemo.pojo.Records;
import com.jsonDemo.jsonReaderDemo.pojo.RootData;
import com.jsonDemo.jsonReaderDemo.pojo.TaskSpecs;
import com.jsonDemo.jsonReaderDemo.service.JsonService;
import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class JsonServiceImpl implements JsonService {
	private static final ObjectMapper mapper=new ObjectMapper();
	@Override
	public RootData createJson(RootData rootData) {
		System.out.println("----JsonServiceImpl-----"+rootData);
		JsonNode node=mapper.convertValue(rootData, JsonNode.class);
		ObjectNode rtObj=(ObjectNode) node;
		List<JsonNode> dataSt=StreamSupport.stream(rtObj.get("data").spliterator(), false).collect(Collectors.toList());

		JsonNode taskSpckNode=dataSt.get(0).get("taskSpecs");
		if(taskSpckNode!=null) {
			ObjectNode objNode=(ObjectNode) dataSt.get(0);
			objNode.set("result", taskSpckNode);
			objNode.remove("taskSpecs");
		}
		// TODO Auto-generated method stub
		readJsonFile(rootData);
		//System.out.println(node);
		return rootData;
	}

	private RootData readJsonFile(RootData roots) {
		System.out.println("------RootData--------");
		//RootData roots=null;
		try {
			MultiEngineResults multiEngResults=new MultiEngineResults();
			List<DataDatum> data=roots.getData();
			data.forEach(dt ->{
				TaskSpecs tskSpcks=dt.getTaskSpecs();
				List<ComponentScoping> compScoping=tskSpcks.getComponentScoping();
				for (ComponentScoping comptSco : compScoping) {
					List<Components> components=comptSco.getComponents();
					for(Components compNen :components ) {
						List<Records> records=compNen.getRecord();
						for (Records reco : records) {
							List<String> resu=reco.getRuleResult().getResult();
							for(String finalResults :resu) {
								System.out.println("finalResults :: "+finalResults);
								if("Include".equalsIgnoreCase(finalResults)) {
									multiEngResults.setCbvutvi4vResult(" ");
									multiEngResults.setSuspectResult(" ");
									multiEngResults.setUniqueResult(" ");
									multiEngResults.setWellknownResult(" ");
									reco.setMultiEngineResults(multiEngResults);
								}
							}

						}
					}
				}

			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return roots;
	}

}
