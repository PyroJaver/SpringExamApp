package examapp.services;

import examapp.models.Question;
import examapp.repositories.QuestionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {
    public final QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }
    @Transactional
    public void saveQuestion(Question question){
        questionRepo.save(question);
    }

    public List<Question> findAll() {
        List<Question> allQuestions = questionRepo.findAll();
        return allQuestions;
    }
}
