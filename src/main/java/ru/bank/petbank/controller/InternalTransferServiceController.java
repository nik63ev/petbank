package ru.bank.petbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bank.petbank.DTO.CreateInternalTransferDraftRequest;
import ru.bank.petbank.DTO.CreateInternalTransferDraftResponse;
import ru.bank.petbank.services.InternalTransferService;

@Controller
@RequestMapping("api/transfer")
public class InternalTransferServiceController {

    @Autowired
    private InternalTransferService internalTransferService;

    @PostMapping("/create_new_transfer")
    public ResponseEntity<CreateInternalTransferDraftResponse> createNewTransfer(
            @RequestBody CreateInternalTransferDraftRequest request) {
        CreateInternalTransferDraftResponse transferDraftResponse = internalTransferService
                .createInternalTransferDraft(request.getSender_account_number(), request.getSenderID());

        return ResponseEntity.ok(transferDraftResponse);
    }
}
