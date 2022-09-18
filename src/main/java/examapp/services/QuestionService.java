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

    public final QuestionRepo questionRepo;
  /*  @Value("${questions.sizeOfTest}")
            int sizeOfTest;*/

  //  private List <Question> someQuestions = findSomeQuestions(sizeOfTest);

    @Autowired
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


    public Question findById(Long id){
        Optional<Question> question = questionRepo.findById(Math.toIntExact(id));
        //возвращает пустой вопрос, если id не найден
        if(!question.isPresent()) {
            return new Question();
        }
        return question.get();
    }

    //метод выгружает все вопросы из базы данных, а затем генерирует из них тест со случайными вопросами
    public List<Question> findSomeQuestions(@Value("${questions.sizeOfTest}") int sizeOfTest){
        List<Question> someQuestions = new LinkedList<>();
        List<Question> allQuestions = questionRepo.findAll();
        //если размер теста больше, чем вопросов в базе данных, возвращаем пустой список
        if(sizeOfTest > allQuestions.size()){
            return someQuestions;
        }
        //добавление вопроса в список на основе случайного числа
        while(someQuestions.size() != sizeOfTest) {
            int indexOfRandomQuestion = (int) (Math.random() * allQuestions.size());
            //если элемент уже есть в списке, добавления элемента не происходит
            if (!someQuestions.contains(allQuestions.get(indexOfRandomQuestion))){
                someQuestions.add(allQuestions.get(indexOfRandomQuestion));
            }
        }
        return someQuestions;
    }

  /*  public int performTestLogic(LinkedHashSet<Question> answeredQuestions, LinkedHashSet<Question> notAnsweredQuestions){
        int count;

             for (int i = 0; i < answeredQuestions.size()-1; i++ ){
                 if(answeredQuestions.)
             }
        return resultOfTest;
    }*/
 /* public List<Question> getSomeQuestions() {
      return someQuestions;
  }*/
}
