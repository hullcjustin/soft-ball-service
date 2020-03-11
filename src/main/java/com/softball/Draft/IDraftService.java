package com.softball.Draft;

import com.softball.Draft.DraftCaptains.DraftCaptainDTO;
import com.softball.Draft.DraftPicks.PicksDTO;

import java.util.List;

public interface IDraftService {

    void createDraft(DraftDTO draftDTO);
    void deleteDraft(String draftId);
    void undoPick(String draftId);
    List<DraftDTO> getDrafts();
    DraftDTO getDraft(String draftId);
    void createDraftCaptains(List<DraftCaptainDTO> draftCaptains, String draftId);
    void createPick(PicksDTO picksDTO, String draftId);
}
