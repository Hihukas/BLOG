package lt.codeacademy.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.PostEntity;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private UUID id;
    @NotBlank
    @Size(min = 5, max = 200)
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String photo;
    private String postDateAndTime;

    public static Post convert(PostEntity postEntity) {
        return new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getDescription(), postEntity.getPhoto(), postEntity.getPostDateAndTime());
    }
}
