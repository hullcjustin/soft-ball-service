package com.softball.Draft;

import com.softball.Draft.DraftCaptains.DraftCaptain;
import com.softball.Draft.DraftCaptains.DraftCaptainDTO;
import com.softball.Draft.DraftCaptains.DraftCaptainRepository;
import com.softball.Draft.DraftPicks.DraftPicks;
import com.softball.Draft.DraftPicks.DraftPicksRepository;
import com.softball.Draft.DraftPicks.PicksDTO;
import com.softball.Players.PlayerDTO;
import com.softball.Players.PlayerDTOFactory;
import com.softball.Players.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DraftService implements IDraftService {

    public DraftRepository draftRepository;
    public DraftCaptainRepository draftCaptainRepository;
    public DraftPicksRepository draftPicksRepository;
    public PlayerRepository playerRepository;
    public PlayerDTOFactory playerDTOFactory;

    @Autowired
    public DraftService(DraftRepository draftRepository,
                        DraftCaptainRepository draftCaptainRepository,
                        DraftPicksRepository draftPicksRepository,
                        PlayerRepository playerRepository,
                        PlayerDTOFactory playerDTOFactory){
        this.draftRepository = draftRepository;
        this.draftCaptainRepository = draftCaptainRepository;
        this.draftPicksRepository = draftPicksRepository;
        this.playerRepository = playerRepository;
        this.playerDTOFactory = playerDTOFactory;
    }

    @Override
    public void createDraft(DraftDTO draftDTO) {
        UUID uuid2 = UUID.randomUUID();
        String randomUUIDString2 = uuid2.toString();
        Draft draft = new Draft().builder()
                ._id(randomUUIDString2)
                .year(draftDTO.getYear())
                .numberOfCaptains(draftDTO.getNumberOfCaptains())
                .type(draftDTO.getType())
                .rounds(draftDTO.getRounds())
                .dateCreated(new Timestamp(new Date().getTime()))
                .build();
        draftRepository.save(draft);
    }

    @Override
    public void deleteDraft(String draftId){
        Draft draft = draftRepository.findDistinctFirstBy_id(draftId);
        draftRepository.delete(draft);
    }

    @Override
    public List<DraftDTO> getDrafts() {
        List<Draft> drafts = draftRepository.findAll();
        List<DraftDTO> draftDTOS = new ArrayList<>();
        for (Draft draft: drafts){
            draftDTOS.add(getDraft(draft));
        }
        return draftDTOS;
    }

    @Override
    public DraftDTO getDraft(String draftId) {
        Draft draft = draftRepository.findDistinctFirstBy_id(draftId);
        return getDraft(draft);
    }

    @Override
    public void createDraftCaptains(List<DraftCaptainDTO> draftCaptains, String draftId){
        draftCaptains.stream().forEach(draftCaptainDTO -> {
            UUID uuid2 = UUID.randomUUID();
            String randomUUIDString2 = uuid2.toString();
            DraftCaptain draftCaptain = new DraftCaptain();
            draftCaptain.set_id(randomUUIDString2);
            draftCaptain.setPlayerId(draftCaptainDTO.getPlayerId());
            draftCaptain.setDraftId(draftId);
            draftCaptain.setPick(draftCaptainDTO.getPick());
            draftCaptainRepository.save(draftCaptain);
        });
    }

    @Override
    public void createPick(PicksDTO picksDTO, String draftId){
        Draft draft = draftRepository.findDistinctFirstBy_id(draftId);
        DraftPicks doesExistPick = draftPicksRepository.findByDraftIdAndPlayerId(draftId, picksDTO.getPlayerId());
        if(doesExistPick != null)
            throw new HTTPException(415);

        Integer lastRound = getLastround(draftId);
        Integer lastPick = getLastPickNumber(draftId, lastRound);

        Integer nextPick = 1;
        Integer nextRound = 1;
        if(draft.getNumberOfCaptains() == lastPick){
            nextRound += lastRound;
            nextPick = 1;
        }
        else {
            List<DraftPicks> draftPicks = draftPicksRepository.findByDraftId(draftId);
            nextRound = lastRound;
            if(draftPicks.size() > 0)
                nextPick += lastPick;
            else
                nextPick = 1;
        }

        UUID uuid2 = UUID.randomUUID();
        String randomUUIDString2 = uuid2.toString();
        DraftPicks draftPicks = new DraftPicks();
        draftPicks.set_id(randomUUIDString2);
        draftPicks.setPlayerId(picksDTO.getPlayerId());
        draftPicks.setDraftId(draftId);
        draftPicks.setPick(nextPick);
        draftPicks.setRound(nextRound);
        draftPicksRepository.save(draftPicks);
    }


    @Override
    public void undoPick(String draftId) {
        Integer lastRound = getLastround(draftId);
        Integer lastPick = getLastPickNumber(draftId,lastRound);

        List<DraftPicks> draftPicks = draftPicksRepository.findByDraftId(draftId);
        for(DraftPicks pick : draftPicks){
            if(pick.getRound() == lastRound&& pick.getPick() == lastPick){
                draftPicksRepository.delete(pick);
            }
        }


    }

    DraftDTO getDraft(Draft draft){
        DraftDTO draftDTO = new DraftDTO();
        draftDTO.setId(draft.get_id());
        draftDTO.setYear(draft.getYear());
        draftDTO.setRounds(draft.getRounds());
        draftDTO.setType(draft.getType());
        draftDTO.setNumberOfCaptains(draft.getNumberOfCaptains());
        draftDTO.setCaptains(getDraftCaptainDTOS(draft.get_id()));
        draftDTO.setPicks(getPicks(draft.get_id()));

        return draftDTO;
    }
    List<DraftCaptainDTO> getDraftCaptainDTOS(String draftId){
        List<DraftCaptain> draftCaptains = draftCaptainRepository.findByDraftId(draftId);
        return draftCaptains.stream().map( draftCaptain -> {
                PlayerDTO playerDTO = playerDTOFactory.createWithoutStats(playerRepository.findBy_id(draftCaptain.getPlayerId()));
                return new DraftCaptainDTO(playerDTO, draftCaptain.getPlayerId(), draftCaptain.pick);
            }).collect(Collectors.toList());
    }
    List<PicksDTO> getPicks(String draftId){
        List<DraftPicks> draftPicks = draftPicksRepository.findByDraftId(draftId);
        return draftPicks.stream().map(pick -> {
            PlayerDTO playerDTO = playerDTOFactory.createWithoutStats(playerRepository.findBy_id(pick.getPlayerId()));
            return new PicksDTO(pick.get_id(), pick.getPick(), pick.getRound(), playerDTO, pick.getPlayerId());
        }).collect(Collectors.toList());
    }
    Integer getLastround(String draftId){
        List<DraftPicks> draftPicks = draftPicksRepository.findByDraftId(draftId);
        Integer nextRound = 1;
        for(DraftPicks pick: draftPicks){
            if(pick.getRound() > nextRound)
                nextRound = pick.getRound();
        }
        return nextRound;
    }

    Integer getLastPickNumber(String draftId, Integer lastRound){
        List<DraftPicks> draftPicks = draftPicksRepository.findByDraftId(draftId).stream().filter(pick -> {
            if(pick.getRound() == lastRound) return true;
            else return false;
        }).collect(Collectors.toList());
        Integer nextPick = 1;
        for(DraftPicks pick: draftPicks){
            if(pick.getPick() > nextPick)
                nextPick = pick.getPick();
        }
        return nextPick;
    }

}
