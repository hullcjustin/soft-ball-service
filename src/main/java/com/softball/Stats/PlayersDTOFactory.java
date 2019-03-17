package com.softball.Stats;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayersDTOFactory {
    private final StatsRepository statsRepository;

    public PlayersDTOFactory(StatsRepository statsRepository){
        this.statsRepository = statsRepository;
    }

    public static PlayersDTO create(Player player, List<StatsDTO> statsDTOS){
        return new PlayersDTO(
                player._id,
                player.firstName,
                player.lastName,
                player.name,
                statsDTOS
        );
    }

    public List<PlayersDTO> create(List<Player> players){
        return players
                .stream()
                .map(player -> {
                    List<StatsDTO> statsDTOList = StatsDTOFactory.create(statsRepository.findByPlayerId(player._id));
                    return new PlayersDTO(
                            player._id,
                            player.firstName,
                            player.lastName,
                            player.name,
                            statsDTOList
                    );
                })
                .collect(Collectors.toList());

    }
}
