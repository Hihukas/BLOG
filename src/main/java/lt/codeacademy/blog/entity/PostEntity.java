package lt.codeacademy.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.data.Post;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class PostEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private String postDateAndTime;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntities;

    public PostEntity(UUID id, String title, String description, String photo, String postDateAndTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.postDateAndTime = postDateAndTime;
    }

    public static PostEntity convert(Post post) {
        return new PostEntity(post.getId(), post.getTitle(), post.getDescription(), post.getPhoto(), post.getPostDateAndTime());
    }
}
