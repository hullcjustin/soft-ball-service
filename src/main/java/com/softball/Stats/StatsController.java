package com.softball.Stats;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @CrossOrigin(origins = "http://167.99.103.86:8080")
    @RequestMapping(value = "/get-all-players-and-stats", method = RequestMethod.GET)
    public List<PlayersDTO> getAllPlayersAndStats(){
        List<PlayersDTO> playersDTOS = statsService.getAllPlayersAndStats();
        return playersDTOS;
    }

    @CrossOrigin(origins = "http://167.99.103.86:8080")
    @RequestMapping(value = "/insert-uploaded-stats", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUploadedStats(@RequestBody List<PlayersDTO> playersDTO ){
        statsService.uploadStats(playersDTO);
    }
}
