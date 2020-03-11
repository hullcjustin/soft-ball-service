package com.softball.Stats;

import com.softball.Players.Player;
import com.softball.Players.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/soft-ball/stats")
public class StatsController {

    public IStatsService statsService;

    @Autowired
    public StatsController(IStatsService statsService){
        this.statsService = statsService;
    }

    @RequestMapping(value = "/get-player-stats-by-first-name", method = RequestMethod.GET)
    public Player getPlayerStatsByFirstName(@RequestParam String firstname){
        Player player = statsService.getAPlayer(firstname);
        return player;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/get-all-players-and-stats", method = RequestMethod.GET)
    public List<PlayerDTO> getAllPlayersAndStats(){
        List<PlayerDTO> playerDTOS = statsService.getAllPlayersAndStats();
        return playerDTOS;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/insert-uploaded-stats", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUploadedStats(@RequestBody List<PlayerDTO> playerDTO){

        statsService.uploadStats(playerDTO);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "get-undrafted-players", method= RequestMethod.GET)
    public List<PlayerDTO> createPick(@RequestParam String draftId){
       return statsService.getUndraftedPlayers(draftId);
    }
}
