package com.seon.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @JoinColumn(name = "boardId")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
