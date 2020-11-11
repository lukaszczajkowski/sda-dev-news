package se.kth.sda8.devnews.devnews.article;

import com.fasterxml.jackson.annotation.JsonBackReference;
import se.kth.sda8.devnews.devnews.topic.Topic;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "authorName")
    private String authorName;

    @ManyToOne
    private Topic topic;

    public Article(Long id, String title, String body, String authorName, Topic topic) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
        this.topic = topic;
    }

    public Article() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
