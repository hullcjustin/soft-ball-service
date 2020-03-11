package com.softball.Stats;

import com.softball.Draft.DraftDTO;
import com.softball.Draft.DraftPicks.PicksDTO;
import com.softball.Draft.IDraftService;
import com.softball.Players.Player;
import com.softball.Players.PlayerRepository;
import com.softball.Players.PlayerDTO;
import com.softball.Players.PlayerDTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatsService implements IStatsService{

    private PlayerRepository playerRepository;
    private StatsRepository statsRepository;
    private PlayerDTOFactory playerDTOFactory;
    private IDraftService draftService;

    @Autowired
    public StatsService(StatsRepository statsRepository,
                        PlayerRepository playerRepository,
                        PlayerDTOFactory playerDTOFactory,
                        IDraftService draftService){
        this.statsRepository = statsRepository;
        this.playerRepository = playerRepository;
        this.playerDTOFactory = playerDTOFactory;
        this.draftService = draftService;

    }

    @Override
    public List<PlayerDTO> getAllPlayersAndStats() {
        List<PlayerDTO> playerDTOS = playerDTOFactory.createListOfPlayerDTOs(playerRepository.findAll());
        return playerDTOS;
    }

    @Override
    public List<PlayerDTO> getUndraftedPlayers(String draftId) {
        List<PlayerDTO> playerDTOS = playerDTOFactory.createListOfPlayerDTOs(playerRepository.findAll());
        DraftDTO draftDTO = draftService.getDraft(draftId);
        List<PlayerDTO> UndraftedList = playerDTOS.stream().filter(playerDTO -> {
            for(PicksDTO pick: draftDTO.getPicks()){
                if(playerDTO.getId().equals(pick.getPlayerId())){
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        return UndraftedList;
    }

    public Player getAPlayer(String firstName){
        Player player = playerRepository.findByFirstName(firstName);
        return player;
    }

    public Player getPlayerByName(String name){
        return playerRepository.findByName(name);
    }

    public void uploadStats(List<PlayerDTO> playerDTOS){
        playerDTOS.stream()
                .forEach(playerDTO -> {
                    UUID uuid = UUID.randomUUID();
                    String randomUUIDString = uuid.toString();

                    Player player = getPlayerByName(playerDTO.getName());
                    if(player == null){
                        String[] nameArray = playerDTO.getName().split(" ");
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
                                .name(playerDTO.getName())
                                .build();
                        playerRepository.insert(player);
                    }

                    StatsDTO statsDTO = playerDTO.getStatsDTOS().get(0);

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
