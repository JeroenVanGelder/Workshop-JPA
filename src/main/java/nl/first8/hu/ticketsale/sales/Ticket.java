package nl.first8.hu.ticketsale.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.first8.hu.ticketsale.registration.Account;
import nl.first8.hu.ticketsale.venue.Concert;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TicketId.class)
public class Ticket {

    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}