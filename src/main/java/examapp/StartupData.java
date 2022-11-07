package examapp;

import examapp.models.Question;
import examapp.models.User;
import examapp.services.QuestionService;
import examapp.services.TestingService;
import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupData implements CommandLineRunner {
    @Value("${questions.sizeOfTest}")
    private int sizeOfTest;

    private final QuestionService questionService;
    private final UserDetailService userDetailService;
    private final TestingService testingService;
    List<Question> startupQuestions = new ArrayList<>();

    @Autowired
    public StartupData(QuestionService questionService, UserDetailService userDetailService, TestingService testingService) {
        this.questionService = questionService;
        this.userDetailService = userDetailService;
        this.testingService = testingService;
    }

    @Override
    public void run(String... args) {
        //если в базе данных недостаточно вопросов для теста (смотреть логику findTestQuestions()),
        //в неё добавляется 15 вопросов
        // если в sizeOfTest передать число больше 15, стартовых вопросов не хватит на запуск тестирования
        //TODO: реализовать передачу sizeOfTest не через properties. Может быть, в базе данных хранить?
        if(testingService.findTestQuestions(sizeOfTest).isEmpty()){
            startupQuestions();
        }
        if(userDetailService.loadAllUsers().isEmpty()){
            startupUsers();
        }
    }
    private void startupUsers(){
        User user = new User();
        user.setRole("ROLE_USER");
        user.setPassword("useruser");
        user.setName("User");
        user.setSurname("User");
        user.setUsername("user");
        user.setYearOfBirth(2000);
        userDetailService.registerUser(user);

        User admin = new User();
        admin.setRole("ROLE_ADMIN");
        admin.setPassword("adminadmin");
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setUsername("admin");
        admin.setYearOfBirth(2000);
        userDetailService.registerUser(admin);
    }
    private void startupQuestions(){

        Question question1 = new Question();
        question1.setQuestionText("Из предложенного перечня выберите все вещества, при взаимодействии которых " +
                "с раствором перманганата калия будет наблюдаться изменение окраски раствора." +

                " 1)циклогексан" +

                " 2)бензол" +

                " 3)толуол" +

                " 4)пропан" +

                " 5)пропилен");
        question1.setAnswer("35");
        startupQuestions.add(question1);

        Question question2 = new Question();
        question2.setQuestionText("Из предложенного перечня выберите все вещества, из которых можно получить бутан в одну стадию.\n" +

                " 1)бутанол-1" +

                " 2)бутановая кислота" +

                " 3)бутен-1" +

                " 4)бутен-2" +

                " 5)бутанол-2");
        question2.setAnswer("34");
        startupQuestions.add(question2);

        Question question3 = new Question();
        question3.setQuestionText("Из предложенного перечня выберите все вещества, с которыми взаимодействует пентан." +

                " 1)хлор на свету" +

                " 2)раствор КМnO4" +

                " 3)бром на свету" +

                " 4)бромная вода" +

                " 5)раствор КОН");
        question3.setAnswer("13");
        startupQuestions.add(question3);

        Question question4 = new Question();
        question4.setQuestionText("Из предложенного перечня выберите все вещества, которые могут реагировать с каждым из веществ: водой, бромоводородом, водородом.\n" +

                " 1)пропан" +

                " 2)этилен" +

                " 3)бутен-1" +

                " 4)этан" +

                " 5)хлорметан");
        question4.setAnswer("23");
        startupQuestions.add(question4);

        Question question5 = new Question();
        question5.setQuestionText("Из предложенного перечня выберите две пары веществ, которые реагируют с бромной водой при обычных условиях.\n" +

                " 1)бензол и толуол" +

                " 2)циклогексан и пропен" +

                " 3)бутен-2 и аминобензол" +

                " 4)фенол и ацетилен" +

                " 5)бензол и этилен");
        question5.setAnswer("34");
        startupQuestions.add(question5);

        Question question6 = new Question();
        question6.setQuestionText("Из предложенного перечня выберите все вещества, с которыми бензол вступает в реакцию." +

                " 1)хлорметан" +

                " 2)этан" +

                " 3)хлор" +

                " 4)соляная кислота" +

                " 5)гидроксид натрия" +

                " ");
        question6.setAnswer("13");
        startupQuestions.add(question6);

        Question question7 = new Question();
        question7.setQuestionText("Из предложенного перечня выберите две реакции, характерные для алканов." +

                " 1)присоединения" +

                " 2)замещения" +

                " 3)полимеризации" +

                " 4)горения" +

                " 5)этерификации");
        question7.setAnswer("24");
        startupQuestions.add(question7);

        Question question8 = new Question();
        question8.setQuestionText("Из предложенного перечня выберите все вещества, которые " +
                "могут реагировать с каждым из веществ: водой, хлороводородом, водородом." +

                " 1)бутан" +

                " 2)хлорэтан" +

                " 3)бензол" +

                " 4)пентен-2" +

                " 5)пентадиен-1,3");
        question8.setAnswer("45");
        startupQuestions.add(question8);

        Question question9 = new Question();
        question9.setQuestionText("Из предложенного перечня выберите все вещества, с которыми реагирует этан." +

                " 1)хлороводородная кислота" +

                " 2)азотная кислота" +

                " 3)гидроксид меди (II)" +

                " 4)оксид меди (II)" +

                " 5)кислород");
        question9.setAnswer("25");
        startupQuestions.add(question9);

        Question question10 = new Question();
        question10.setQuestionText("Из предложенного перечня выберите все вещества, в молекулах " +
                "которых все атомы углерода находятся в состоянии sp2-гибридизации." +

                " 1)бензол" +

                " 2)гексан" +

                " 3)гексен" +

                " 4)этан" +

                " 5)этен");
        question10.setAnswer("15");
        startupQuestions.add(question10);

        Question question11 = new Question();
        question11.setQuestionText("Из предложенного перечня выберите все вещества, " +
                "в молекулах которых все атомы углерода находятся в состоянии sp3-гибридизации." +

                " 1)гексен-2" +

                " 2)бензол" +

                " 3)пропан" +

                " 4)толуол" +

                " 5)этан");
        question11.setAnswer("35");
        startupQuestions.add(question11);

        Question question12 = new Question();
        question12.setQuestionText("Из предложенного перечня выберите все вещества, которые " +
                "могут вступать в реакцию полимеризации." +

                " 1)бутен-2" +

                " 2)толуол" +

                " 3)этилен" +

                " 4)циклогексан" +

                " 5)метан");
        question12.setAnswer("13");
        startupQuestions.add(question12);

        Question question13 = new Question();
        question13.setQuestionText("Из предложенного перечня выберите все вещества, " +
                "с которыми пропан вступает в реакцию." +

                " 1)металлический натрий" +

                " 2)хлор на свету" +

                " 3)разбавленная азотная кислота при нагревании" +

                " 4)вода" +

                " 5)бромная вода");
        question13.setAnswer("23");
        startupQuestions.add(question13);

        Question question14 = new Question();
        question14.setQuestionText("Из предложенного перечня выберите все вещества " +
                "которые не обесцвечивают бромную воду." +

                " 1)этилен" +

                " 2)ацетилен" +

                " 3)бензол" +

                " 4)бутадиен-1,3" +

                " 5)пропан");
        question14.setAnswer("35");
        startupQuestions.add(question14);

        Question question15 = new Question();
        question15.setQuestionText("Из предложенного перечня выберите две пары углеводородов, " +
                " с которыми в кислой среде реагирует перманганат калия." +

                " 1)метан и этан" +

                " 2)бензол и толуол" +

                " 3)пропен и циклогексан" +

                " 4)гексен и бутадиен" +

                " 5)этилен и ацетилен" +

                " ");
        question15.setAnswer("45");
        startupQuestions.add(question15);

        for (Question question: startupQuestions)
        {
            questionService.saveQuestion(question);
        }
    }
}
