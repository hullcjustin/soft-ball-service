package com.softball.Rankings;

import com.softball.Players.*;
import com.softball.Players.CaptainsList.CaptainsList;
import com.softball.Players.CaptainsList.CaptainsListDTO;
import com.softball.Players.CaptainsList.CaptainsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RankingService implements IRankingService{

    private WeekRankRepository weekRankRepository;
    private RankingsRepository rankingsRepository;
    private PlayerRepository playerRepository;
    public CaptainsListRepository captainsListRepository;

    @Autowired
    public RankingService(WeekRankRepository weekRankRepository,
                          RankingsRepository rankingsRepository,
                          CaptainsListRepository captainsListRepository,
                          PlayerRepository playerRepository){
        this.weekRankRepository = weekRankRepository;
        this.rankingsRepository = rankingsRepository;
        this.captainsListRepository = captainsListRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void saveRanking(CaptainRankingDTO captainRankingDTO) {
        Date date = new Date();

        List<CaptainsListDTO> submitedCaptains = getSubmitedRankingsByWeek(captainRankingDTO.getWeekRankId());
        Boolean duplicate = true;
        for(CaptainsListDTO dto: submitedCaptains){
            if(dto.getPlayerId().equals(captainRankingDTO.getCaptainId()))
                duplicate = false;
        }
        if(!duplicate){
            for(PlayerRankDTO playerRankDTO: captainRankingDTO.getPlayerRankDTOList()){
                UUID uuid = UUID.randomUUID();
                Ranking ranking = Ranking.builder()
                        .id(uuid.toString())
                        .playerId(playerRankDTO.getPlayerId())
                        .weekRankId(captainRankingDTO.getWeekRankId())
                        .date(date)
                        .Captain(captainRankingDTO.getCaptain())
                        .captainId(captainRankingDTO.getCaptainId())
                        .rank(playerRankDTO.getRank())
                        .points(playerRankDTO.getPoints())
                        .build();

                rankingsRepository.save(ranking);

            }
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Captain already submitted for this.");

        }

    }

    @Override
    public List<RankingsByWeekDTO> getRankingsByWeek(Integer year) {

        List<WeekRank> weekRanks = weekRankRepository.findByYear(year);
        List<RankingsByWeekDTO> rankingsByWeekDTOS = new ArrayList<>();
        Set<String> positions = new HashSet<>();
        Collections.sort(weekRanks, new Comparator<WeekRank>() {
            @Override
            public int compare(WeekRank o1, WeekRank o2) {
                return o1.getWeek().compareTo(o2.getWeek());
            }
        });

        for(WeekRank weekRank: weekRanks){
            List<Ranking> rankings = rankingsRepository.findByWeekRankId(weekRank.id);
            List<PlayerRankDTO> playerRankDTOList = new ArrayList<>();
            Set<String> playerSetIds = new HashSet<>();


            if(!positions.contains(weekRank.position))
                positions.add(weekRank.position);

            for(Ranking ranking: rankings){
                if(!playerSetIds.contains(ranking.playerId))
                    playerSetIds.add(ranking.playerId);
            }

            for(String playerId: playerSetIds){

                List<Ranking> rankingsByPlayer = rankings.stream()
                        .filter(ranking -> playerId.equals(ranking.playerId))
                        .collect(Collectors.toList());
                Integer points = 0;
                for (Ranking ranking: rankingsByPlayer){
                    points += ranking.getPoints();
                }

                playerRankDTOList.add(new PlayerRankDTO(
                        playerId,
                        playerRepository.findBy_id(playerId).getName(),
                        0,
                        points));
            }

            Collections.sort(playerRankDTOList, new Comparator<PlayerRankDTO>() {
                @Override
                public int compare(PlayerRankDTO o1, PlayerRankDTO o2) {
                    return o2.getPoints().compareTo(o1.getPoints());
                }
            });
            rankingsByWeekDTOS.add(new RankingsByWeekDTO(weekRank.getId(), weekRank.week, weekRank.position, weekRank.status, playerRankDTOList));
        }

        List<RankingsByWeekDTO> overalPostions = getOverallRankingByPosition(positions, rankingsByWeekDTOS);
        for(RankingsByWeekDTO rankings: overalPostions){
            rankingsByWeekDTOS.add(rankings);
        }

        return rankingsByWeekDTOS;
    }


    @Override
    public void createNewWeek(WeekRankDTO weekRankDTO) {

        UUID uuid = UUID.randomUUID();

        Calendar cal = Calendar.getInstance();
        cal.setTime(weekRankDTO.getStartDate());
        int year = cal.get(Calendar.YEAR);


        WeekRank weekRank =  WeekRank.builder()
            .id(uuid.toString())
            .week(weekRankDTO.getWeek())
            .year(year)
            .status("InProgress")
            .position(weekRankDTO.getPosition())
            .rankStart(weekRankDTO.getStartDate())
            .rankEnd(weekRankDTO.getEndDate())
            .build();

        weekRankRepository.save(weekRank);
    }

    @Override
    public List<WeekRankDTO> getWeekRanks() {
        List<WeekRank> weekRankList = weekRankRepository.findAll();
        List<WeekRankDTO> liveRanks = new ArrayList<>();

        for(WeekRank rank : weekRankList){
//            if((date.after(rank.rankStart) && date.before(rank.rankEnd)) || date.equals(rank.rankStart) || date.equals(rank.rankEnd)){
//                liveRanks.add(WeekRankDTOFactory.create(rank));
//            }
            if(rank.status.equals("InProgress")){
                liveRanks.add(WeekRankDTOFactory.create(rank));
            }
        }

        return liveRanks;
    }

    @Override
    public List<CaptainsListDTO> getSubmitedRankingsByWeek(String weekRankId){
                List<String> captainsIds = new ArrayList<>();
        captainsIds.add("204fc5d7-640f-48c8-8c28-9f287a0c9cea");
        captainsIds.add("4d63aee4-4e86-49fd-bb8c-52c9dc270308");
        captainsIds.add("523ae1fc-6ecc-4849-a38d-175e0c7d5793");
        captainsIds.add("b161bf81-2e9c-4164-9c96-7ccf75b72967");
        captainsIds.add("31b50364-33bb-48c6-93c8-2ff9732b7e35");
        captainsIds.add("974a181d-c6ce-4bc5-90ff-cc91c64ece33");

        for(String id:captainsIds){
            UUID uuid = UUID.randomUUID();
            CaptainsList captainsList = CaptainsList.builder()._id(uuid.toString()).playerId(id).build();
            captainsListRepository.save(captainsList);
        }

        WeekRank weekRank = weekRankRepository.findByid(weekRankId);
        List<Ranking> rankingsByWeek =rankingsRepository.findByWeekRankId(weekRank.id);
        Set<String>submittedCaptainsIds = new HashSet<>();

        for(Ranking ranking: rankingsByWeek){
            if(!submittedCaptainsIds.contains(ranking.captainId))
                submittedCaptainsIds.add(ranking.captainId);
        }

        List<CaptainsList> captainsList = captainsListRepository.findAll();
        List<CaptainsListDTO> captainsListDTOS = new ArrayList<>();

        for(CaptainsList captain: captainsList) {
            Player player = playerRepository.findBy_id(captain.getPlayerId());
            if (!submittedCaptainsIds.contains(captain.playerId))
                captainsListDTOS.add(new CaptainsListDTO(player._id, player.getName()));
        }
        return captainsListDTOS;
    }

    private List<RankingsByWeekDTO> getOverallRankingByPosition(Set<String> positions, List<RankingsByWeekDTO> rankings){
        List<RankingsByWeekDTO> rankingsByWeekDTOS = new ArrayList<>();
        for(String position: positions){
            HashMap<String, PlayerRankDTO> hashmapPlayer = new HashMap<>();
            for(RankingsByWeekDTO weekRank: rankings){

                if(weekRank.getPosition().equals(position) && weekRank.getStatus().equals("Complete") && !weekRank.getWeek().equals(0)){
                    for(PlayerRankDTO playerRankDTO: weekRank.getPlayerRankDTOList()){
                        PlayerRankDTO hashPlayer = hashmapPlayer.get(playerRankDTO.getPlayerId());
                        if(hashPlayer != null){
                            PlayerRankDTO updatedPlayer = new PlayerRankDTO(hashPlayer.getPlayerId(), hashPlayer.getPlayerName(), hashPlayer.getRank(), hashPlayer.getPoints() + playerRankDTO.getPoints());
                            hashmapPlayer.put(hashPlayer.getPlayerId(), updatedPlayer);
                        }else{
                            hashmapPlayer.put(playerRankDTO.getPlayerId(), playerRankDTO);
                        }
                    }
                }

            }
            List<PlayerRankDTO> finalPlayersList = new ArrayList<PlayerRankDTO>(hashmapPlayer.values());
            Collections.sort(finalPlayersList, new Comparator<PlayerRankDTO>() {
                @Override
                public int compare(PlayerRankDTO o1, PlayerRankDTO o2) {
                    return o2.getPoints().compareTo(o1.getPoints());
                }
            });

            rankingsByWeekDTOS.add(new RankingsByWeekDTO("default", 1000, position, "Complete", finalPlayersList));
        }
        return rankingsByWeekDTOS;
    }
}