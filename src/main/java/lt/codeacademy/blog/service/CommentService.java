package lt.codeacademy.blog.service;

import lt.codeacademy.blog.data.Comment;
import lt.codeacademy.blog.entity.CommentEntity;
import lt.codeacademy.blog.exception.CommentNotExistException;
import lt.codeacademy.blog.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Page<Comment> getComments(Pageable pageable) {
        return commentRepository.findAll(pageable).map(Comment::convert);
    }

    public void createNewComment(Comment comment) {
        comment.setCommentDateAndTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        commentRepository.save(CommentEntity.convert(comment));
    }

    public Comment getComment(UUID id) {
        return commentRepository.findById(id).map(Comment::convert).orElseThrow(() -> new CommentNotExistException(id));
    }

    public void updateComment(Comment comment) {
        comment.setCommentDateAndTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        commentRepository.save(CommentEntity.convert(comment));
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
