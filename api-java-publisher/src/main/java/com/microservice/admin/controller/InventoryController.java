package com.microservice.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.admin.service.RabbitMQService;
import consts.RabbitMQConstants;
import dto.InventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

	@Autowired
	private RabbitMQService service;

	@Autowired
	private Logger logger;

	@PutMapping
	private ResponseEntity<Void> updateInventory(@RequestBody InventoryDTO inventoryDTO){
		logger.info(inventoryDTO.productCode);

		service.sendMessage(RabbitMQConstants.QUEUE_INVENTORY, inventoryDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
