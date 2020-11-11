package se.kth.sda8.devnews.devnews.article;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
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
}
