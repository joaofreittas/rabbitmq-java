package com.microservice.consumer.consumer;

import consts.RabbitMQConstants;
import dto.InventoryDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumer {

	@RabbitListener(queues = RabbitMQConstants.QUEUE_INVENTORY)
	private void consumer(InventoryDTO inventoryDTO){
		System.out.println(inventoryDTO.productCode);
		System.out.println(inventoryDTO.quantity);
		System.out.println("-------------------------------");

	}
}
