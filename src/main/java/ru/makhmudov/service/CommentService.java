package ru.makhmudov.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.makhmudov.dto.CommentDTO;
import ru.makhmudov.entity.Comments;
import ru.makhmudov.entity.Post;
import ru.makhmudov.entity.UserReg;
import ru.makhmudov.exceptions.PostNotFoundException;
import ru.makhmudov.repository.CommentRepository;
import ru.makhmudov.repository.PostRepository;
import ru.makhmudov.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comments saveComment(Long postId, CommentDTO commentDTO, Principal principal) {
        UserReg user = getUserByPrincipal(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found for username: " + user.getEmail()));

        Comments comment = new Comments();
        comment.setPost(post);
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setMessage(commentDTO.getMessage());

        LOG.info("Saving comment for Post: {}", post.getId());
        return commentRepository.save(comment);
    }

    public List<Comments> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found"));
        List<Comments> comments = commentRepository.findAllByPost(post);

        return comments;
    }

    public void deleteComment(Long commentId) {
        Optional<Comments> comment = commentRepository.findById(commentId);
        comment.ifPresent(commentRepository::delete);
    }


    private UserReg getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
