package se.kth.sda8.devnews.devnews.like;

import org.hibernate.annotations.ManyToAny;
import se.kth.sda8.devnews.devnews.article.Article;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Like (Long id, String name) {
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

    public Article getArticle() {
        return article;
    }


    public void setArticle(Article article) {
        this.article = article;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, article);
    }
}
