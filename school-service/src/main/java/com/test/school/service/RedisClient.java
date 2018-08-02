package com.test.school.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
		name ="product-service",
	    url = "https://redsky.target.com/v1/pdp/tcin/")
public interface RedisClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics")
    public ResponseEntity<String> getProdcutDetails(@PathVariable("id") String id);

}
