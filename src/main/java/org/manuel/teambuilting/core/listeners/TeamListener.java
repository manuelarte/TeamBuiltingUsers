package org.manuel.teambuilting.core.listeners;

import org.manuel.teambuilting.core.messages.TeamDeletedMessage;
import org.manuel.teambuilting.core.messages.TeamVisitedMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@RabbitListener(bindings = @QueueBinding(
	value = @Queue(durable = "true", value = "${messaging.event.amqp.team.queue.name}"),
	exchange = @Exchange(durable = "${messaging.event.amqp.exchange.durable}", value = "${messaging.event.amqp.exchange.name}", type = ExchangeTypes.TOPIC),
	key = "team.#"))
@Configuration
public class TeamListener {

	@RabbitHandler
	public void teamVisited(final TeamVisitedMessage message) {

	}

	@RabbitHandler
	public void teamDeleted(final TeamDeletedMessage message) {

	}
}
