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

    @ManyToOne
    private Article article;

    public Like(Long id, String name, Article article) {
        this.id = id;
        this.name = name;
        this.article = article;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
