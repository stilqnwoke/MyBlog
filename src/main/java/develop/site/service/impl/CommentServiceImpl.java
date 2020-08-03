package develop.site.service.impl;

import develop.site.model.entity.Comment;
import develop.site.model.entity.Reply;
import develop.site.repository.CommentRepository;
import develop.site.repository.ReplyRepository;
import develop.site.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ReplyRepository replyRepository) {
        this.commentRepository = commentRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void saveReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public Reply getReplyById(Long replyId) {
        return replyRepository.getOne(replyId);
    }

    @Override
    public void deleteReply(Reply reply) {
        replyRepository.delete(reply);
    }
}
