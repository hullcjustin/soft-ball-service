package com.softball.Rankings;

import com.softball.Players.*;
import com.softball.Players.CaptainsList.CaptainsListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/soft-ball/rankings")
public class RankingsController {

    public IRankingService rankingService;
    public PlayerRepository playerRepository;

    @Autowired
    public RankingsController(IRankingService rankingService,
                              PlayerRepository playerRepository){
        this.rankingService = rankingService;
        this.playerRepository = playerRepository;
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "save-ranking", method= RequestMethod.POST)
    public void saveRanking(@RequestBody CaptainRankingDTO captainRankingDTO){
        rankingService.saveRanking(captainRankingDTO);
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "get-rankings-by-week", method= RequestMethod.GET)
    public List<RankingsByWeekDTO> getRankingsByWeek(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        return rankingService.getRankingsByWeek(year);
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "create-new-week", method= RequestMethod.POST)
    public void creatNewWeek(@RequestBody WeekRankDTO weekRankDTO){
        rankingService.createNewWeek(weekRankDTO);
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "get-week-ranks", method= RequestMethod.GET)
    public List<WeekRankDTO> getWeekRanks(){
        return rankingService.getWeekRanks();
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "get-captains-list", method= RequestMethod.GET)
    public List<CaptainsListDTO> getCaptainsList(@Param("weekRankId")String weekRankId){
        return rankingService.getSubmitedRankingsByWeek(weekRankId);
    }

    @CrossOrigin(origins = "http://softball.justindev1.com")
    @RequestMapping(value = "get-players-list", method= RequestMethod.GET)
    public List<Player> getPlayersList(){
        return playerRepository.findAll();
    }
}