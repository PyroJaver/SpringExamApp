package examapp.services;

import examapp.models.Question;
import examapp.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionService {




    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }




    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public void updateQuestion(int id, Question updatedQuestion) {
        Question questionToBeUpdated = findById(id);

        questionToBeUpdated.setQuestionText(updatedQuestion.getQuestionText());
        questionToBeUpdated.setAnswer(updatedQuestion.getAnswer());

        questionRepo.save(questionToBeUpdated);
    }

    public void deleteQuestionById(int id) {
        questionRepo.deleteById(id);
    }

    @Transactional
    public void saveQuestion(Question question) {
        questionRepo.save(question);
    }

    public Question findById(int id) {
        Optional<Question> question = questionRepo.findById(Math.toIntExact(id));
        //возвращает пустой вопрос, если id не найден
        if (!question.isPresent()) {
            return new Question();
        }
        return question.get();
    }
}
