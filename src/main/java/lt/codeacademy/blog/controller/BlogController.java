package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.data.Post;
import lt.codeacademy.blog.service.CommentService;
import lt.codeacademy.blog.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping()
@SessionAttributes("post")
public class BlogController {
    private final PostService postService;
    private final CommentService commentService;

    public BlogController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @ModelAttribute("post")
    public Post initPost() {
        return new Post();
    }

    @GetMapping("/public/blog")
    public String showPosts(Model model, @PageableDefault(sort = {"postDateAndTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("posts", postService.getPosts(pageable));

        return "blog";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/save")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());

        return "postCreateOrUpdate";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/save")
    public String createNewPost(@Valid Post post, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "postCreateOrUpdate";
        }

        postService.createNewPost(post);

        redirectAttributes.addFlashAttribute("message", "lt.codeacademy.blog.post.create.message.success");

        return "redirect:/blog/save";
    }


    @GetMapping("/public/blog/{id}")
    public String postAndPostCommentReview(@PathVariable UUID id, @ModelAttribute("post") Post post, Model model, @PageableDefault(sort = {"commentDateAndTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("comments", commentService.getComments(pageable));

        post = postService.getPost(id);

        return "postReview";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/{id}/update")
    public String postUpdateForm(@PathVariable UUID id, Model model) {
        model.addAttribute("post", postService.getPost(id));

        return "postCreateOrUpdate";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/{id}/update")
    public String postUpdate(@Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "postCreateOrUpdate";
        }

        postService.updatePost(post);

        return "redirect:/public/blog/{id}";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blog/{id}/delete")
    public String postDelete(@PathVariable UUID id) {

        postService.deletePost(id);

        return "redirect:/public/blog";
    }
}
