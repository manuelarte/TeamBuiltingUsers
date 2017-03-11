package org.manuel.teambuilting.core.listeners;

import org.manuel.teambuilting.core.messages.PlayerDeletedMessage;
import org.manuel.teambuilting.core.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.repositories.PlayerToTeamRepository;
import org.manuel.teambuilting.core.repositories.PlayerToTeamSportDetailsRepository;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Listener for the player topic
 *
 * @author Manuel Doncel Martos
 * @since 31/12/2016.
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(durable = "true", value = "${messaging.amqp.player.queue.name}"),
        exchange = @Exchange(durable = "${messaging.amqp.player.exchange.durable}", value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC),
        key = "${messaging.amqp.player.queue.binding}"))
@Configuration
public class PlayerListener {


    private final PlayerToTeamRepository playerToTeamRepository;
    private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

    @Inject
    public PlayerListener(final PlayerToTeamRepository playerToTeamRepository, final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository) {
        this.playerToTeamRepository = playerToTeamRepository;
        this.playerToTeamSportDetailsRepository = playerToTeamSportDetailsRepository;
    }

    @RabbitHandler
    public void handle(final PlayerDeletedMessage message) {
        playerToTeamRepository.delete(playerToTeamRepository.findByPlayerId(message.getPlayer().getId()));
        playerToTeamSportDetailsRepository.delete(playerToTeamSportDetailsRepository.findByPlayerId(message.getPlayer().getId()));
    }
}
