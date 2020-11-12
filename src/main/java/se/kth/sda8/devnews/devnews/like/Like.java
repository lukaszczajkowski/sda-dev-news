package se.kth.sda8.devnews.devnews.like;

import org.hibernate.annotations.ManyToAny;
import se.kth.sda8.devnews.devnews.article.Article;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reactions")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "likes")
    private List<Article> articles;

    public Like(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
