package se.kth.sda8.devnews.devnews.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam(required = false) String sort) {
        if(sort == null) {
            sort = "author";
            return articleService.getAll(sort);
        } else {
            return articleService.getAll(sort);
        }

    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id){
        return articleService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Article create(@RequestBody Article newArticle) {
        return articleService.create(newArticle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

    @PutMapping("")
    public Article update(@RequestBody Article updatedArticle) {
        return articleService.update(updatedArticle);
    }
}
