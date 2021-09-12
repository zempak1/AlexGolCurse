package ru.makhmudov.facade;


import org.springframework.stereotype.Component;
import ru.makhmudov.dto.CommentDTO;
import ru.makhmudov.entity.Comments;

@Component
public class CommentFacade {

    public CommentDTO commentToCommentDTO(Comments comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setMessage(comment.getMessage());

        return commentDTO;
    }

}
