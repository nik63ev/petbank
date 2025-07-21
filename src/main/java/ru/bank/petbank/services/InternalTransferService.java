package ru.bank.petbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.petbank.model.InternalTransfer;
import ru.bank.petbank.repository.InternalTransferRepository;

@Service
public class InternalTransferService {
    @Autowired
    private InternalTransferRepository internalTransferRepository;

    private InternalTransfer internalTransferDruft()
    {

        return null;
    }

}
