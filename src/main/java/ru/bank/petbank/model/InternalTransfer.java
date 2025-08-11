package ru.bank.petbank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

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
    private Long SenderID;

    @Column(name = "receiver_id", nullable = true)
    private Long ReceiverID;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "change_status_date", nullable = false)
    private LocalDateTime changeStatusDate;

    public InternalTransfer(String senderAccountNumber, Long senderID){
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = null;
        this.amount = 0.0;
        this.status = TransferStatus.DRAFT;
        this.SenderID = senderID;
        this.ReceiverID = null;
        this.createDate = LocalDateTime.now();
        this.changeStatusDate = this.createDate;
        this.transferID = createTransferID(this.createDate);
    }
    public InternalTransfer() {}

    private String createTransferID(LocalDateTime date) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.getYear());
        if (date.getMonthValue() < 10) {
            sb.append("0");
        }
        sb.append(date.getMonthValue());
        if (date.getDayOfMonth() < 10) {
            sb.append("0");
        }
        sb.append(date.getDayOfMonth());

        Random random = new Random();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < 24; i++) {
            int digit = random.nextInt(10); // 0-9
            sb2.append(digit);
        }
        sb.append(sb2.toString());
        System.out.println(sb.toString());
        return sb.toString();
    }

    // 1 Создаем перевод в статусе DRUFT - получатель не заполнен, возварат номера перевода,
    // 2 Меняем сумму перевода
    // 3 Добавляем получателя
    // 4 Запрос на перевод, блокировка счета, проверка-сумма, блок счета, блокировка счета получателя
    // выполняется перевод, в случае успешно меняем суммы, разблокировка счета, статус трансфера на комплитед,
    // В случе не успеха статус транфера ерор.
}
