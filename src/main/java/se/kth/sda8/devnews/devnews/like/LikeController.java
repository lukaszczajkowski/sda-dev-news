package se.kth.sda8.devnews.devnews.like;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("")
    public List<Like> getAll(@RequestParam(required = false) Long articleId) {
        if(articleId == null) {
            return likeService.getAll();
        } else {
            return likeService.getAllByArticleId(articleId);
        }
    }

    @GetMapping("/{id}")
    public Like getById (@PathVariable Long id) {
        return likeService.getById(id);
    }

    @PostMapping("")
    public Like create(@RequestBody Like like) {
        return likeService.create(like);
    }

    @PutMapping("")
    public Like update(@RequestBody Like updatedLike) {
        return likeService.update(updatedLike);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        likeService.delete(id);
    }

}
