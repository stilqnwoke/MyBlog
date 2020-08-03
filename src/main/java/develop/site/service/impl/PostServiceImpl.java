package develop.site.service.impl;

import develop.site.model.entity.Comment;
import develop.site.model.entity.Post;
import develop.site.model.entity.Reply;
import develop.site.repository.CommentRepository;
import develop.site.repository.PostRepository;
import develop.site.repository.ReplyRepository;
import develop.site.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(CommentRepository commentRepository, ReplyRepository replyRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;

        this.postRepository = postRepository;
    }

    @Override
    public Post getLatestPost() {
        return postRepository.findFirstByOrderByPostedOnDesc();
    }

    @Override
    public List<Post> list() {
        return postRepository.findAllByOrderByPostedOnDesc();
    }

    @Override
    public Post getBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    @Override
    public List<Post> listByAuthor(Long id) {
        return postRepository.findAllByAuthorIdOrderByPostedOnDesc(id);
    }

    @Override
    public Post get(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }




}
