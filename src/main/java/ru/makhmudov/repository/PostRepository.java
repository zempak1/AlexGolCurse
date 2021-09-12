package ru.makhmudov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makhmudov.entity.Post;
import ru.makhmudov.entity.UserReg;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByCreatedDateDesc(UserReg user);

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, UserReg user);
}
