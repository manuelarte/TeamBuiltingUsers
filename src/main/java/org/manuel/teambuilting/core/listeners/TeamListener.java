package org.manuel.teambuilting.core.listeners;

import org.manuel.teambuilting.core.messages.TeamEventMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Configuration
public class TeamListener {

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(durable = "true", value="${messaging.event.amqp.queue}"),
		exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
		key = "${messaging.event.amqp.team-event-routing-key}") )
	public void teamVisited(final @Payload TeamEventMessage message) {
		System.out.println(message);
	}
}
