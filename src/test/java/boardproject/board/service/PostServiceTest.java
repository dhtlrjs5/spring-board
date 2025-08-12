package boardproject.board.service;

import boardproject.board.domain.Member;
import boardproject.board.domain.Post;
import boardproject.board.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    @Test
    void createPost() {
        //given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");

        //when
        postService.save(post);
        Post findPost = postService.findById(post.getId());

        //then
        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
        assertThat(findPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
        assertThat(findPost).isSameAs(post);
    }

}