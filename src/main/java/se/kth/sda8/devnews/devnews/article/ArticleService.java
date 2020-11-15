package se.kth.sda8.devnews.devnews.article;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.kth.sda8.devnews.devnews.like.Like;
import se.kth.sda8.devnews.devnews.like.LikeService;
import se.kth.sda8.devnews.devnews.topic.Topic;
import se.kth.sda8.devnews.devnews.topic.TopicRepository;
import se.kth.sda8.devnews.devnews.topic.TopicService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final LikeService likeService;
    private final TopicRepository topicRepository;

    public ArticleService(ArticleRepository repository, LikeService likeService, TopicRepository topicRepository) {
        this.repository = repository;
        this.likeService = likeService;
        this.topicRepository = topicRepository;
    }

    public List<Article> getAll(String sort) {
        return repository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(sort.equals("author") ? Article::getAuthorName
                            : Article::getTitle))
                    .collect(Collectors.toList());
    }

    public Optional<Article> getById(Long id) {
        return repository.findById(id);
    }

    public Article create(Article newArticle) {
        repository.save(newArticle);
        return newArticle;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Article update(Article updatedArticle) {
        return repository.save(updatedArticle);
    }

    public List<Article> getAll() {
        return repository.findAll();
    }

    public List<Article> getAllByTopicsId(Long topicId) {
        return repository.findAllByTopics_id(topicId);
    }

    public Article addTopic(Long id, Topic topic) {
        if(topicRepository.findById(topic.getId()) == null){
            new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return repository.save((findArticleById(id)
        ).addTopic(topic));

    }

    public void removeTopic(Long id, Topic topic) {
       Article article = findArticleById(id);

       article.deleteTopic(topic);
       repository.save(article);
    }

    public void removeTopic(Topic topic) {
        Long topicId = topic.getId();
        List<Article> articlesContainTopic = repository.findAllByTopics_id(topicId);
        articlesContainTopic.forEach(a -> {
            a.deleteTopic(topic);
        });
        articlesContainTopic.forEach(article -> {
            repository.save(article);
        });
    }

    public Long getLikesCount(Long id) {
        Article article = repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return (long) likeService.getAllByArticleId(id).size();
    }

    public Article addLike(Long id, Like like) {
        Article article = findArticleById(id);
        like.setArticle(article);
        System.out.println("Like name: " + like.getName());
        System.out.println("Like id: " + like.getId());
        System.out.println("Article id: " + article.getId());
        article.addLike(like);
        return repository.save(article);
    }


    private Article findArticleById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public void deleteLike(Long id, Like like) {
        Article article = findArticleById(id);
        article.removeLike(like);
        likeService.delete(like.getId());
        repository.save(article);
    }

}
