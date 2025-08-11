package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.petbank.DTO.CreateInternalTransferDraftResponse;
import ru.bank.petbank.model.InternalTransfer;
import ru.bank.petbank.repository.InternalTransferRepository;
import ru.bank.petbank.repository.UserAccountsRepository;

@Service
public class InternalTransferService {
    @Autowired
    private InternalTransferRepository internalTransferRepository;

    @Autowired
    private UserAccountsRepository userAccountsRepository;

    public CreateInternalTransferDraftResponse createInternalTransferDraft(String senderAccountNumber, Long senderID)
    {
        InternalTransfer internalTransfer = new InternalTransfer(senderAccountNumber, senderID);
        return null;
    }

}
