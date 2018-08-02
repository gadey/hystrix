package com.test.school.service;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StudentServiceDelegate {

	 @Autowired
	    RestTemplate restTemplate;
	 
	 @Autowired
	 private StudentClient client;
	 
	 @Inject
	 private RedisClient redisClient;
	     
	    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
	    public String callStudentServiceAndGetData(String schoolname) {
	 
	        System.out.println("Calling code in StudentServiceDelegate " );
	    	System.out.println("Getting School details for " + schoolname);
	 
	        /*String response1 = restTemplate
	                .exchange("http://student-service/getStudentDetailsForSchool/{schoolname}"
	                , HttpMethod.GET
	                , null
	                , new ParameterizedTypeReference<String>() {
	            }, schoolname).getBody();
	    	
	        System.out.println("response after normal call");*/
	        
	    	String response = client.getBookmarks(schoolname);
	 
	        System.out.println("Response Received as " + response + " -  " + new Date());
	 
	        return "NORMAL FLOW !!! - School Name -  " + schoolname + " :::  " +
	                    " Student Details " + response + " -  " + new Date();
	    }
	    
	    
	    
	    public String callProdcutData(String productId) throws Exception {
	   	 
	    	System.out.println("Getting Prodcut Details for " + productId);
	    	
	        System.out.println("response after normal call");
	        
	        ResponseEntity<String> response = redisClient.getProdcutDetails(productId);
	 
	        System.out.println("Response Received as " + response + " -  " + new Date());
	        
	        ObjectMapper infoMapper = new ObjectMapper();
	        Map<String, Map> infoMap;
	        infoMap = infoMapper.readValue(response.getBody(), Map.class);

            //product.productId = productId;
            Map<String,Map> productMap = infoMap.get("product");
            Map<String,Map> itemMap = productMap.get("item");
            Map<String,String> prodDescrMap = itemMap.get(("product_description"));
            String title = prodDescrMap.get("title");
	 
	        return "NORMAL FLOW !!! - School Name -  " + productId + " :::  " +
	                    " Prodcut Details " + title + " -  " + new Date();
	    }
	    
	   /* public String callProdcutData(String productId) throws Exception  {
	    	 RestTemplate restTemplate = new RestTemplate();
	         Product product = new Product();

	         String url = "https://redsky.target.com/v1/pdp/tcin/"+ productId +"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	         Map<String, String> urlVariables = new HashMap<String, String>();
	         urlVariables.put("id", productId);

	         ObjectMapper infoMapper = new ObjectMapper();
	         Map<String, Map> infoMap;


	             ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, urlVariables);
	             infoMap = infoMapper.readValue(response.getBody(), Map.class);

	             //product.productId = productId;
	             Map<String,Map> productMap = infoMap.get("product");
	             Map<String,Map> itemMap = productMap.get("item");
	             Map<String,String> prodDescrMap = itemMap.get(("product_description"));
	             String title = prodDescrMap.get("title");


	        


	         return title;

	    }*/
	    
	     
	    @SuppressWarnings("unused")
	    private String callStudentServiceAndGetData_Fallback(String schoolname) {
	 
	        System.out.println("Student Service is down!!! fallback route enabled...");
	 
	        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
	                    " Service will be back shortly - " + new Date();
	    }	    
}
