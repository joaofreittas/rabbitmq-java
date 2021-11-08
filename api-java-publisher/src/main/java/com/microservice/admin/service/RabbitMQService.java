package com.microservice.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Logger logger;

	public void sendMessage(String queueName, Object message) {
		try{
			String messageJson = objectMapper.writeValueAsString(message);

			rabbitTemplate.convertAndSend(
					queueName,
					messageJson
			);

		}catch (Exception ex) {
			logger.warning("Erro ao serializar a mensagem!");
		}
	}
}
