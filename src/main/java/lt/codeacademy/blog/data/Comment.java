package lt.codeacademy.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.CommentEntity;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private UUID id;
    @NotBlank
    private String comment;
    private String commentDateAndTime;
    private Post post;
    private User user;

    public static Comment convert(CommentEntity commentEntity) {
        Post post = Post.convert(commentEntity.getPostEntity());

        User user = User.convert(commentEntity.getUserEntity());

        return new Comment(commentEntity.getId(), commentEntity.getComment(), commentEntity.getCommentDateAndTime(), post, user);
    }
}
