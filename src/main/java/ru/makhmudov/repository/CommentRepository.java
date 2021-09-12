package ru.makhmudov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makhmudov.entity.Comments;
import ru.makhmudov.entity.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByPost(Post post);

    Comments findByIdAndUserId(Long commentId, Long userId);
}
