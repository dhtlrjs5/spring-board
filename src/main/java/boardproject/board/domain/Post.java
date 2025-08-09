package boardproject.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }
}
