package ru.makhmudov.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "VARCHAR2(2000)", nullable = false)
    private String message;
    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Comments() {
    }


    /*Аннотация позволяет вызвать метод до отправки объекта в БД*/
    @PrePersist
    protected void onCreate()
    {
        this.createdDate = LocalDateTime.now();
    }
}
