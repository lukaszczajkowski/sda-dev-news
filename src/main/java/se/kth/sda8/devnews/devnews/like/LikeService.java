package se.kth.sda8.devnews.devnews.like;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LikeService {

    private LikeRepository repository;

    public LikeService(LikeRepository repository) {
        this.repository = repository;
    }

    public List<Like> getAll() {
        return repository.findAll();
    }

    public Like getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Like create(Like like) {
        return repository.save(like);
    }

    public Like update(Like updatedLike) {
        return repository.save(updatedLike);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

    public List<Like> getAllByArticleId(Long articleId) {
        System.out.println("Article id: " + articleId);
        return repository.findAllByArticleId(articleId);
    }
}
