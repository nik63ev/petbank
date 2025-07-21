package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "\"internal_transfer\"")
public class InternalTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transfer_id", nullable = false)
    private String transferID; // yyyymmdd - 24 num

    @Column(name = "sender_account_number", nullable = false)
    private String senderAccountNumber;

    @Column(name = "receiver_account_number", nullable = true)
    private String receiverAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Column(name = "sender_id", nullable = false)
    private String SenderID;

    @Column(name = "receiver_id", nullable = true)
    private String ReceiverID;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "change_status_date", nullable = false)
    private Date changeStatusDate;

    // 1 Создаем перевод в статусе DRUFT - получатель не заполнен, возварат номера перевода,
    // 2 Меняем сумму перевода
    // 3 Добавляем получателя
    // 4 Запрос на перевод, блокировка счета, проверка-сумма, блок счета, блокировка счета получателя
    // выполняется перевод, в случае успешно меняем суммы, разблокировка счета, статус трансфера на комплитед,
    // В случе не успеха статус транфера ерор.
}
