package se.kth.sda8.devnews.devnews.topic;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic create(Topic topic) {
        topicRepository.save(topic);
        return topic;
    }

    public Topic update(Topic updatedTopic) {
        return topicRepository.save(updatedTopic);
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    public List<Topic> getAllByArticleId(Long articleId) {
        return topicRepository.findAllByArticleId(articleId);
    }
}
