package com.microservice.admin.controller;

import com.microservice.admin.service.RabbitMQService;
import consts.RabbitMQConstants;
import dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/price")
public class PriceController {

	@Autowired
	private RabbitMQService service;

	@Autowired
	private Logger logger;

	@PutMapping
	private ResponseEntity<Void> updatePrice(@RequestBody PriceDTO priceDTO){
		logger.info(priceDTO.productCode);

		service.sendMessage(RabbitMQConstants.QUEUE_PRICE, priceDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
