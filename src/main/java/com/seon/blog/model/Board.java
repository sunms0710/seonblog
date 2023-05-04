package com.seon.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; //조회수

    @JoinColumn(name = "userId")
    @ManyToOne //한명의 유저가 여러 글을 쓸 수 있다. Many = Board, One = User
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아님, fk가 아니므로 DB에 칼럼을 만들지 마라.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

}
