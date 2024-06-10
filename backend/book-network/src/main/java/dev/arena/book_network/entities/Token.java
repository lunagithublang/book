package dev.arena.book_network.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Token  extends BaseEntity{

    @Column(nullable = false)
    private String token;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDateTime validatedAt;
    private LocalDateTime expiresAt;
}
