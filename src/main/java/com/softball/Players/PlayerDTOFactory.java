package com.softball.Players;

import com.softball.Stats.StatsDTO;
import com.softball.Stats.StatsDTOFactory;
import com.softball.Stats.StatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerDTOFactory {
    private final StatsRepository statsRepository;

    public PlayerDTOFactory(StatsRepository statsRepository){
        this.statsRepository = statsRepository;
    }

//    public PlayersDTO create(Player player, List<StatsDTO> statsDTOS){
//        PlayersDTO playersDTO = PlayersDTO.builder()
//                .id(player._id)
//                .firstName(player.firstName)
//                .lastName(player.lastName)
//                .name(player.name)
//                .statsDTOS(statsDTOS)
//                .build();
//        return playersDTO;
//    }

    public List<PlayerDTO> createListOfPlayerDTOs(List<Player> players){
        return players
                .stream()
                .map(player -> {
                    List<StatsDTO> statsDTOList = StatsDTOFactory.create(statsRepository.findByPlayerId(player._id));
                    return PlayerDTO.builder()
                            .id(player._id)
                            .firstName(player.firstName)
                            .lastName(player.lastName)
                            .name(player.name)
                            .statsDTOS(statsDTOList)
                            .build();
                })
                .collect(Collectors.toList());

    }

    public PlayerDTO create(Player player){
        List<StatsDTO> statsDTOList = StatsDTOFactory.create(statsRepository.findByPlayerId(player._id));
        return PlayerDTO.builder()
                .id(player._id)
                .firstName(player.firstName)
                .lastName(player.lastName)
                .name(player.name)
                .statsDTOS(statsDTOList)
                .build();
    }

    public PlayerDTO createWithoutStats(Player player){
        return PlayerDTO.builder()
                .id(player._id)
                .firstName(player.firstName)
                .lastName(player.lastName)
                .name(player.name)
                .build();
    }
}
