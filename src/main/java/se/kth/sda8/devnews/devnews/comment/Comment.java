package se.kth.sda8.devnews.devnews.comment;

import org.hibernate.annotations.ManyToAny;
import se.kth.sda8.devnews.devnews.article.Article;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "authorName")
    private String authorName;

    @ManyToOne
    private Article article;

    //TODO: restore the Article parameter
    public Comment(Long id, String body, String authorName, Article article) {
        this.id = id;
        this.body = body;
        this.authorName = authorName;
        this.article = article;
    }

    public Comment()  {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
       this.article = article;
    }
}
