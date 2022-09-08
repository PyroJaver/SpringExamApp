package examapp.repositories;

import examapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
