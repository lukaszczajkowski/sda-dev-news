package se.kth.sda8.devnews.devnews.article;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.http.MockHttpOutputMessage;
import se.kth.sda8.devnews.devnews.like.LikeService;
import se.kth.sda8.devnews.devnews.topic.Topic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ArticleControllerTest {

    @Autowired
    ArticleController articleController;

    @MockBean
    ArticleService articleService;

    @Test
    void getAllArticlesTest() {
        List<Article> expected = arrangeList();

        Mockito.when(articleService.getAll())
                .thenReturn(List.of(expected.get(0), expected.get(1)));

        List<Article> actual = articleController.getAllArticles(null, null);

        assertEquals(expected, actual);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    void getArticleTest(){

        Mockito.when(articleService.getById(1L)).thenReturn(
                Optional.of(arrangeList().get(0))
        );
        Mockito.when(articleService.getById(2L)).thenReturn(
                Optional.of(arrangeList().get(1))
        );

        Article actual1 = articleController.getArticle(1L);
        Article actual2 = articleController.getArticle(2L);

        assertEquals(arrangeList().get(0).getId(), actual1.getId());
        assertEquals(arrangeList().get(1).getId(), actual2.getId());


    }

    @Test
    void getAllByTopicId() {
        Topic topic = arrangeTopic();
        Article article = arrangeArticle();
        article.setTopics(List.of(topic));


    }

    @Test
    void createTest() {
        Article expected = arrangeArticle();
        Mockito.when(articleService.create(expected)).thenReturn(
               expected
        );

        Article actual = articleController.create(expected);

        assertEquals(expected, actual);
    }



    List<Article> arrangeList(){
        Article article1 = new Article(1L, "test article 1", "test body 1", "test name 1");
        Article article2 = new Article(2L, "test article 2", "test body 2", "test name 2");

       return List.of(article1, article2);
    }

    Article arrangeArticle() {
        return new Article(1L, "test article 1", "test body 1", "test author 1");
    }

    Topic arrangeTopic() {
        return new Topic(1L, "test topic 1");
    }

}
