package dev.arena.book_network.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean isArchived;
    private boolean isShareable;
    @Column(nullable = false, updatable = false)
    private UUID createdBy;
    @Column(nullable = false, updatable = false)
    private UUID updatedBy;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name ="owner_id")
    private Account owner;

    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;
}
