package ru.bank.petbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bank.petbank.model.InternalTransfer;

@Repository
public interface InternalTransferRepository extends JpaRepository<InternalTransfer, Long> {
}
