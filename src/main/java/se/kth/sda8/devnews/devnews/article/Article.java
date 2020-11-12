package se.kth.sda8.devnews.devnews.article;

import se.kth.sda8.devnews.devnews.like.Like;
import se.kth.sda8.devnews.devnews.topic.Topic;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    private List<Topic> topics;

    @ManyToMany
    private List<Like> likes;


    public Article(Long id, String title, String body, String authorName) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Article addTopic(Topic topic) {
        this.topics.add(topic);
        return this;
    }

    public void deleteTopic(Topic topic) {
        System.out.println("Deleting " + topic.getId());
        System.out.println(this.topics.remove(topic));
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
