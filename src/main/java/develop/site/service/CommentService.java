package develop.site.service;

import develop.site.model.entity.Comment;
import develop.site.model.entity.Reply;

public interface CommentService {
    Comment getCommentById(Long id);
    void saveComment(Comment comment);
    void deleteComment(Comment comment);

    void saveReply(Reply reply);
    Reply getReplyById(Long replyId);
    void deleteReply(Reply reply);
}
