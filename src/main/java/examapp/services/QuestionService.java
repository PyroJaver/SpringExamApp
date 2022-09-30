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


   @Value("${questions.sizeOfTest}")
    public int sizeOfTest;

    private List<Question> answeredQuestions = new ArrayList<>();
    private List <Question> testQuestions = new ArrayList<>();

    private final QuestionRepo questionRepo;
    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }
    @Transactional
    public void saveQuestion(Question question){
        questionRepo.save(question);
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
    public List<Question> findTestQuestions(int sizeOfTest){

        List<Question> allQuestions = questionRepo.findAll();
        //если размер теста больше, чем вопросов в базе данных, возвращаем пустой список
        if(sizeOfTest > allQuestions.size()){
            return testQuestions;
        }
        //обеспечение генерации нового теста при обновлении страницы, без перезапуска приложения
        if(testQuestions.size() == sizeOfTest){
            testQuestions.clear();
        }
        //добавление вопроса в список на основе случайного числа
        while(testQuestions.size() != sizeOfTest) {
            int indexOfRandomQuestion = (int) (Math.random() * allQuestions.size());
            //если элемент уже есть в списке, добавления элемента не происходит
            if (!testQuestions.contains(allQuestions.get(indexOfRandomQuestion))){
                testQuestions.add(allQuestions.get(indexOfRandomQuestion));
            }
        }
        return testQuestions;
    }


  public List<Question> getNewTestQuestions() {
          testQuestions = findTestQuestions(sizeOfTest);
      return testQuestions;
  }

  public  List<Question> getTestQuestions(){
        return testQuestions;
  }

    public List<Question> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<Question> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }
}
