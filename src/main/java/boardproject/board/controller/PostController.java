package boardproject.board.controller;

import boardproject.board.domain.Member;
import boardproject.board.domain.Post;
import boardproject.board.service.MemberService;
import boardproject.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("postForm", new PostForm());

        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "posts/postCreate";
    }

    @PostMapping("/posts/new")
    public String savePost(@ModelAttribute("post") @Valid PostForm postForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "posts/postCreate";
        }

        Post post = new Post();
        Member member = memberService.findById(postForm.getMemberId());
        post.createPost(member, postForm.getTitle(), postForm.getContent());

        postService.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        return "posts/postView";
    }
}
