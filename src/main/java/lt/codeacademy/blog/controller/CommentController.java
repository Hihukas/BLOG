package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.data.Comment;
import lt.codeacademy.blog.data.Post;
import lt.codeacademy.blog.data.User;
import lt.codeacademy.blog.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/save")
    public String newComment(Model model) {
        model.addAttribute("comment", new Comment());

        return "commentCreateOrUpdate";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/save")
    public String createNewComment(@Valid Comment comment, BindingResult bindingResult, @SessionAttribute("post") Post post, SessionStatus sessionStatus, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "commentCreateOrUpdate";
        }

        User user = (User) authentication.getPrincipal();

        comment.setUser(user);

        comment.setPost(post);

        commentService.createNewComment(comment);

        sessionStatus.setComplete();

        return "redirect:/public/blog/" + post.getId().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/update")
    public String commentUpdateForm(@PathVariable UUID id, Model model) {
        model.addAttribute("comment", commentService.getComment(id));

        return "commentCreateOrUpdate";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{id}/update")
    public String commentUpdate(@PathVariable UUID id, @Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "commentCreateOrUpdate";
        }

        comment.setUser(commentService.getComment(id).getUser());

        comment.setPost(commentService.getComment(id).getPost());

        commentService.updateComment(comment);

        return "redirect:/public/blog/" + commentService.getComment(id).getPost().getId().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String commentDelete(@PathVariable UUID id) {

        UUID postId = commentService.getComment(id).getPost().getId();

        commentService.deleteComment(id);

        return "redirect:/public/blog/" + postId.toString();
    }
}
