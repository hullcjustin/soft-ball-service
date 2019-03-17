package com.softball.Stats;

import org.bson.types.ObjectId;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StatsService implements IStatsService{

    private PlayerRepository playerRepository;
    private StatsRepository statsRepository;
    private PlayersDTOFactory playersDTOFactory;

    @Autowired
    public StatsService(StatsRepository statsRepository,
                        PlayerRepository playerRepository,
                        PlayersDTOFactory playersDTOFactory){
        this.statsRepository = statsRepository;
        this.playerRepository = playerRepository;
        this.playersDTOFactory = playersDTOFactory;

    }

    @Override
    public List<PlayersDTO> getAllPlayersAndStats() {
        List<PlayersDTO> playersDTOS = playersDTOFactory.create(playerRepository.findAll());
        return playersDTOS;
    }

    public Player getAPlayer(String firstName){
        Player player = playerRepository.findByFirstName(firstName);
        return player;
    }

    public Player getPlayerByName(String name){
        return playerRepository.findByName(name);
    }

    public void uploadStats(List<PlayersDTO> playersDTOS){
        playersDTOS.stream()
                .forEach(playersDTO -> {
                    UUID uuid = UUID.randomUUID();
                    String randomUUIDString = uuid.toString();

                    Player player = getPlayerByName(playersDTO.getName());
                    if(player == null){
                        String[] nameArray =playersDTO.getName().split(" ");
                        String firstName = "";
                        String lastName = "";
                        if(nameArray.length == 2){
                            firstName = nameArray[0];
                            lastName = nameArray[1];
                        }
                        else if (nameArray.length == 3){
                            firstName = nameArray[0];
                            lastName = nameArray[2];
                        }


                         player = Player.builder()
                                ._id(randomUUIDString)
                                .firstName(firstName)
                                .lastName(lastName)
                                .name(playersDTO.getName())
                                .build();
                        playerRepository.insert(player);
                    }

                    StatsDTO statsDTO = playersDTO.getStatsDTOS().get(0);

                    Stats stats = statsRepository.findByPlayerIdAndYear(player._id, statsDTO.getYear());
                    if(stats != null){
                        stats.setPlateAppearences(statsDTO.getPlateAppearances());
                        stats.setAtBats(statsDTO.getAtBats());
                        stats.setRuns(statsDTO.getRuns());
                        stats.setHits(statsDTO.getHits());
                        stats.setFirstBase(statsDTO.getFirstBase());
                        stats.setSecondBase(statsDTO.getSecondBase());
                        stats.setThirdBase(statsDTO.getThirdBase());
                        stats.setHomeRuns(statsDTO.getHomeRuns());
                        stats.setRbis(statsDTO.getRbis());
                        stats.setWalks(statsDTO.getWalks());
                        stats.setBattingPercentage(statsDTO.getBattingPercentage());
                        stats.setAverage(statsDTO.getAverage());
                        stats.setSlugingPercentag(statsDTO.getSlugingPercentage());
                        stats.setPlayersInScoringPosition(statsDTO.getPlayersInScoringPosition());
                        stats.setCaptain(statsDTO.getCaptain());
                        stats.setDefensivePosition(statsDTO.getDefensivePosition());
                        statsRepository.save(stats);
                    }else{
                        UUID uuid2 = UUID.randomUUID();
                        String randomUUIDString2 = uuid2.toString();

                        stats = Stats.builder()
                                .id(randomUUIDString2)
                                .playerId(player.get_id())
                                .year(statsDTO.getYear())
                                .plateAppearences(statsDTO.getPlateAppearances())
                                .atBats(statsDTO.getAtBats())
                                .runs(statsDTO.getRuns())
                                .hits(statsDTO.getHits())
                                .firstBase(statsDTO.getFirstBase())
                                .secondBase(statsDTO.getSecondBase())
                                .thirdBase(statsDTO.getThirdBase())
                                .homeRuns(statsDTO.getHomeRuns())
                                .rbis(statsDTO.getRbis())
                                .walks(statsDTO.getWalks())
                                .battingPercentage(statsDTO.getBattingPercentage())
                                .average(statsDTO.getAverage())
                                .slugingPercentag(statsDTO.getSlugingPercentage())
                                .playersInScoringPosition(statsDTO.getPlayersInScoringPosition())
                                .captain(statsDTO.getCaptain())
                                .defensivePosition(statsDTO.getDefensivePosition())
                                .build();
                        statsRepository.insert(stats);
                    }

                });
    }
}
