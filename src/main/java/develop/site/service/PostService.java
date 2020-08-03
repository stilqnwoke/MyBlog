package develop.site.service;

import develop.site.model.entity.Comment;
import develop.site.model.entity.Post;
import develop.site.model.entity.Reply;

import java.util.List;

public interface PostService {


    Post getLatestPost();

    List<Post> list();

    Post getBySlug(String slug);

    List<Post> listByAuthor(Long id);

    Post get(Long id);

    Post save(Post post);

    void delete(Post post);



}
