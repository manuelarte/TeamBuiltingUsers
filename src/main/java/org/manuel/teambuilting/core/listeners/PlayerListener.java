package org.manuel.teambuilting.core.listeners;

import org.manuel.teambuilting.core.messages.PlayerDeletedMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * Listener for the player topic
 *
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(durable = "true", value = "${messaging.event.amqp.queue}"),
        exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
        key = "player.#"))
@Configuration
public class PlayerListener {

    @RabbitHandler
    public void handle(final PlayerDeletedMessage message) {
        System.out.println(message);
        // apply subscriber to event patter, and delete all the player history and player position
    }
}
