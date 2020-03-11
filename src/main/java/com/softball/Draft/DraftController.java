package com.softball.Draft;


import com.softball.Draft.DraftCaptains.DraftCaptainDTO;
import com.softball.Draft.DraftPicks.PicksDTO;
import com.softball.Rankings.CaptainRankingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/soft-ball/draft")
public class DraftController {

    public IDraftService draftService;

    @Autowired
    public DraftController(IDraftService draftService){
        this.draftService = draftService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "create-draft", method= RequestMethod.POST)
    public void createDraft(@RequestBody DraftDTO draftDTO){
        draftService.createDraft(draftDTO);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "get-drafts", method= RequestMethod.GET)
    public List<DraftDTO> getDrafts(){
        return draftService.getDrafts();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "delete-drafts", method= RequestMethod.GET)
    public void deleteDrafts(@RequestParam String draftId){
        draftService.deleteDraft(draftId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "get-draft", method= RequestMethod.GET)
    public DraftDTO getDraft(@RequestParam String draftId){
        return draftService.getDraft(draftId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "create-captains", method= RequestMethod.POST)
    public void createCaptains(@RequestBody List<DraftCaptainDTO> draftCaptainDTO,
                               @RequestParam String draftId){
        draftService.createDraftCaptains(draftCaptainDTO, draftId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "create-pick", method= RequestMethod.POST)
    public void createPick(@RequestBody PicksDTO picksDTOS,
                           @RequestParam String draftId){
        draftService.createPick(picksDTOS, draftId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "undo-pick", method= RequestMethod.GET)
    public void undoPick(@RequestParam String draftId){
        draftService.undoPick(draftId);
    }
}
