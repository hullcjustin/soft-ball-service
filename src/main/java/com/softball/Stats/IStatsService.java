package com.softball.Stats;

import com.softball.Players.Player;
import com.softball.Players.PlayerDTO;

import java.util.List;

public interface IStatsService {
  List<PlayerDTO> getAllPlayersAndStats();
  List<PlayerDTO> getUndraftedPlayers(String draftId);
  Player getAPlayer(String firstName);
  Player getPlayerByName(String name);
  void uploadStats(List<PlayerDTO> playerDTOS);
}
