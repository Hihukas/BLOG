package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.data.Comment;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private String commentDateAndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static CommentEntity convert(Comment comment) {
        PostEntity postEntity = PostEntity.convert(comment.getPost());

        UserEntity userEntity = UserEntity.convert(comment.getUser());

        return new CommentEntity(comment.getId(), comment.getComment(), comment.getCommentDateAndTime(), postEntity, userEntity);
    }
}
