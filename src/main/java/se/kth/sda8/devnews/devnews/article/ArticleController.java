package se.kth.sda8.devnews.devnews.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.kth.sda8.devnews.devnews.like.Like;
import se.kth.sda8.devnews.devnews.like.LikeController;
import se.kth.sda8.devnews.devnews.like.LikeService;
import se.kth.sda8.devnews.devnews.topic.Topic;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final LikeService likeService;

    public ArticleController(ArticleService articleService, LikeService likeService) {
        this.articleService = articleService;
        this.likeService = likeService;
    }

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam(required = false) String sort,
                                        @RequestParam(required = false) Long topicId) {
        if(sort != null) {
            sort = "author";
            return articleService.getAll(sort);
        }
        if(topicId != null){
            return articleService.getAllByTopicsId(topicId);
        }
        return articleService.getAll();

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

    @PutMapping("{id}/attach_topic")
    public Article attachTopic(@PathVariable Long id, @RequestBody Topic topic) {
        return articleService.addTopic(id, topic);
    }

    @DeleteMapping("{id}/remove_topic")
    public void removeTopic(@PathVariable Long id, @RequestBody Topic topic) {
        articleService.removeTopic(id, topic);
    }

    @GetMapping("{id}/likes")
    public Long getLikesCount (@PathVariable Long id) {
        return articleService.getLikesCount(id);
    }

    //post or put?
    @PostMapping("{id}/add_like")
    public Article addLike(@PathVariable Long id, @RequestBody Like like) {
        likeService.create(like);
        return articleService.addLike(id, like);
    }

    /**
     * Takes an ID of an article that we want to remove the like from and
     * the id of this like and removes it not only from the list of likes
     * of the article, but also from the likes repository
     * @param id - ID of the article that we want to delete the like from
     * @param like - only ID of the like required
     */
    @DeleteMapping("{id}/remove_like")
    public void deleteLike(@PathVariable Long id, @RequestBody Like like) {
        likeService.delete(like.getId());
    }
}
