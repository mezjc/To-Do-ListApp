package org.jhon.app.todolistapp.persistence.entity;

import jakarta.persistence.*;

@Entity(name = "categorias")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String genre;

    @OneToOne( mappedBy = "category",cascade = CascadeType.PERSIST)
    private Task task;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
