package dev.arena.book_network.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class BookTransactionHistory extends BaseEntity {

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    private boolean isReturned;
    private boolean isReturnedApproved;
}
