package com.softball.Stats;

import org.bson.types.ObjectId;

import java.util.List;

public interface IStatsService {
  List<PlayersDTO> getAllPlayersAndStats();
  Player getAPlayer(String firstName);
  Player getPlayerByName(String name);
  void uploadStats(List<PlayersDTO> playersDTOS);
}
