package com.microservice.admin.connections;

import consts.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

	private final String EXCHANGE_NAME = "amq.direct";
	private final AmqpAdmin amqpAdmin;

	public RabbitMQConnection(AmqpAdmin amqpAdmin){
		this.amqpAdmin = amqpAdmin;
	}

	@PostConstruct
	private void add(){
		Queue queueInventory = queue(RabbitMQConstants.QUEUE_INVENTORY);
		Queue queuePrice = queue(RabbitMQConstants.QUEUE_PRICE);

		DirectExchange directExchange = exchangeDirect();

		Binding bindingInventory = binding(queueInventory, directExchange);
		Binding bindingPrice = binding(queuePrice, directExchange);

		amqpAdmin.declareQueue(queueInventory);
		amqpAdmin.declareQueue(queuePrice);

		amqpAdmin.declareExchange(directExchange);

		amqpAdmin.declareBinding(bindingInventory);
		amqpAdmin.declareBinding(bindingPrice);

	}

	private Queue queue(String nameQueue){
		return new Queue(
				nameQueue,
				true,
				false,
				false
		);
	}

	private DirectExchange exchangeDirect(){
		return new DirectExchange(EXCHANGE_NAME);
	}


	private Binding binding(Queue queue, DirectExchange exchange){
		return new Binding(
				queue.getName(),
				Binding.DestinationType.QUEUE,
				exchange.getName(),
				queue.getName(),
				null
		);
	}

}
