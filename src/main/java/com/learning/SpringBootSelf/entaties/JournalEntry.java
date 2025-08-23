package com.learning.SpringBootSelf.entaties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long journalId;

    @Column(nullable = false)
    private String journalTitle;

    private String storyContent;

    private LocalDate date;

    @ManyToOne
    private User user;


//    public void setDate(String date) {
////        String[] s= date.split("-");
//        CharSequence d = date.subSequence(0, date.length());
////        LocalDate.parse(s[0].);
//        this.date = LocalDate.parse(d,DateTimeFormatter.ofPattern("yyyy-M-dd"));
//    }


}
