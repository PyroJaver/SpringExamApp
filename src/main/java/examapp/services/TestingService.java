package examapp.services;

import examapp.dto.TestDto;
import examapp.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TestingService {

    private List<Question> answeredQuestions = new ArrayList<>();
    private List<Question> testQuestions = new ArrayList<>();

    @Value("${questions.sizeOfTest}")
    public int sizeOfTest;

    private final QuestionService questionService;
    private double testGrade;

    @Autowired
    public TestingService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void calculateTestingResults(TestDto testDto){

        testDto.setQuestions(testQuestions);
        for (int i = 0; i < testQuestions.size(); i++) {
            //ответ считается правильным, если поле answer для двух вопросов полностью совпадает
            //регистр букв игнорируется
            if (Objects.equals(testQuestions.get(i).getAnswer().toLowerCase(),
                    answeredQuestions.get(i).getAnswer().toLowerCase())) {
                testGrade++;
            }

        }
        testDto.setRate(testGrade);
        testDto.setMaxRate(testQuestions.size());
        testDto.setPercentageOfCompletion(100*testGrade/testDto.getMaxRate());

    }
    //метод выгружает все вопросы из базы данных, а затем генерирует из них тест со случайными вопросами
    public List<Question> findTestQuestions(int sizeOfTest) {

        List<Question> allQuestions = questionService.getAllQuestions();
        //если размер теста больше, чем вопросов в базе данных, возвращаем пустой список
        if (sizeOfTest > allQuestions.size()) {
            return testQuestions;
        }
        //обеспечение генерации нового теста при обновлении страницы, без перезапуска приложения
        if (testQuestions.size() == sizeOfTest) {
            testQuestions.clear();
        }
        //добавление вопроса в список на основе случайного числа
        while (testQuestions.size() != sizeOfTest) {
            int indexOfRandomQuestion = (int) (Math.random() * allQuestions.size());
            //если элемент уже есть в списке, добавления элемента не происходит
            if (!testQuestions.contains(allQuestions.get(indexOfRandomQuestion))) {
                testQuestions.add(allQuestions.get(indexOfRandomQuestion));
            }
        }
        return testQuestions;
    }


    //сортировка необходима для правильного вычисления результатов тестирования
    public List<Question> setNewTestQuestions() {
        testQuestions = findTestQuestions(sizeOfTest);
        Collections.sort(testQuestions);
        return testQuestions;
    }

    public List<Question> getTestQuestions() {
        return testQuestions;
    }

    public List<Question> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<Question> answeredQuestions) {
        Collections.sort(answeredQuestions);
        this.answeredQuestions = answeredQuestions;
        ;
    }
}
