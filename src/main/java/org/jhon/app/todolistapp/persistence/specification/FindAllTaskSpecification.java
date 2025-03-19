package org.jhon.app.todolistapp.persistence.specification;

import jakarta.persistence.criteria.*;
import org.jhon.app.todolistapp.dto.request.TaskSearchCriteria;
import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.util.State;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FindAllTaskSpecification implements Specification<Task> {

    private TaskSearchCriteria searchCriteria;
    public FindAllTaskSpecification(TaskSearchCriteria searchCriteria) {
        this.searchCriteria= searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Task, Category> taskCategoryJoin = root.join("category", JoinType.LEFT);

        if (StringUtils.hasText(this.searchCriteria.category())){
            Predicate categoryLike = criteriaBuilder.like(taskCategoryJoin.get("genre"),"%"+this.searchCriteria.category()+"%");
            predicates.add(categoryLike);
        }
        if (StringUtils.hasText(this.searchCriteria.state())){
            Predicate stateEqual = criteriaBuilder.equal(root.get("state") ,this.searchCriteria.state().toLowerCase());
            predicates.add(stateEqual);
        }
        if (this.searchCriteria.releaseYear() != null ){

            LocalDateTime startOfDay = this.searchCriteria.releaseYear().atStartOfDay();
            Predicate releaseYearGreaterThanEqual = criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startOfDay);
            predicates.add(releaseYearGreaterThanEqual);
        }

        if (StringUtils.hasText(this.searchCriteria.title())){
            Predicate titleLiKE = criteriaBuilder.like(root.get("title"),"%"+this.searchCriteria.title()+"%");
            predicates.add(titleLiKE);
        }

            
        Predicate [] predicatesAsArray = new Predicate[0];
        return criteriaBuilder.and(predicates.toArray(predicatesAsArray));
//        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
