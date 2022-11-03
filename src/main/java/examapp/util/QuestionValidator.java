package examapp.util;

import examapp.models.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuestionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Question.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    Question question = (Question) target;
    if (question.getQuestionText().length()<10){
        errors.rejectValue("questionText", "question.error.question_text.less_10");
    }
    if (question.getQuestionText().length()>1000){
            errors.rejectValue("questionText", "question.error.question_text.over_1000");
    }
    if (question.getAnswer().length()<10){
            errors.rejectValue("answer", "question.error.answer.less_10");
    }
    if (question.getAnswer().length()>1000) {
            errors.rejectValue("answer", "question.error.answer.over_1000");
    }
    }
}
