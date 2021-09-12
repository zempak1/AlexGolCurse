package ru.makhmudov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makhmudov.entity.ImageModel;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findAllByUserId(Long userId);

    Optional<ImageModel> findByPostId(Long postId);
}
