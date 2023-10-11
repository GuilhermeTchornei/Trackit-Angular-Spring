package com.trackit.api.progress.repository;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.trackit.api.habits.model.Habits;
import com.trackit.api.history.model.History;
import com.trackit.api.progress.model.HabitsWithLastHistory;
import com.trackit.api.progress.model.MonthHabits;
import com.trackit.api.user.model.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ProgressRepository {
    private final EntityManager entityManager;

    public ProgressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Long todayDoneHabits(Users user, Date date) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteria.createQuery(Long.class);

        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<History, Habits> habitsJoin = historyRoot.join("habit");

        Predicate dayPredicate = criteria.equal(historyRoot.get("day"), date);
        Predicate userPredicate = criteria.equal(habitsJoin.get("user"), user);
        Predicate donePredicate = criteria.equal(historyRoot.get("done"), true);
        Predicate doneHabitsPredicate = criteria.and(dayPredicate, userPredicate, donePredicate);

        criteriaQuery.select(criteria.count(historyRoot)).where(doneHabitsPredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Long todayTotalHabits(Users user, Date date) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteria.createQuery(Long.class);

        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<History, Habits> habitsJoin = historyRoot.join("habit");

        Predicate dayPredicate = criteria.equal(historyRoot.get("day"), date);
        Predicate userPredicate = criteria.equal(habitsJoin.get("user"), user);

        Predicate totalHabitsPredicate = criteria.and(dayPredicate, userPredicate);

        criteriaQuery.select(criteria.count(historyRoot))
                .where(totalHabitsPredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<MonthHabits> monthTotalHabits(Users user, YearMonth yearMonth) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<MonthHabits> criteriaQuery = criteria.createQuery(MonthHabits.class);

        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<History, Habits> habitsJoin = historyRoot.join("habit");
        Path<String> dayPath = historyRoot.get("day");

        Predicate monthPredicate = criteria.between(historyRoot.get("day"), yearMonth.atDay(1),
                yearMonth.atEndOfMonth());
        Predicate userPredicate = criteria.equal(habitsJoin.get("user"), user);

        Predicate totalHabitsPredicate = criteria.and(monthPredicate, userPredicate);

        criteriaQuery.multiselect(dayPath, criteria.count(historyRoot)).where(totalHabitsPredicate)
                .orderBy(criteria.asc(dayPath));
        criteriaQuery.groupBy(dayPath);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<MonthHabits> monthDoneHabits(Users user, YearMonth yearMonth) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<MonthHabits> criteriaQuery = criteria.createQuery(MonthHabits.class);

        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<History, Habits> habitsJoin = historyRoot.join("habit");
        Path<String> dayPath = historyRoot.get("day");

        Predicate monthPredicate = criteria.between(historyRoot.get("day"), yearMonth.atDay(1),
                yearMonth.atEndOfMonth());
        Predicate userPredicate = criteria.equal(habitsJoin.get("user"), user);
        Predicate donePredicate = criteria.equal(historyRoot.get("done"), true);

        Predicate doneHabitsPredicate = criteria.and(monthPredicate, userPredicate,
                donePredicate);

        criteriaQuery.multiselect(dayPath, criteria.count(historyRoot)).where(doneHabitsPredicate)
                .orderBy(criteria.asc(dayPath));
        criteriaQuery.groupBy(dayPath);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<HabitsWithLastHistory> findHabitsWithLastHistory(Users user) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<HabitsWithLastHistory> criteriaQuery = criteria.createQuery(HabitsWithLastHistory.class);

        Root<History> historyRoot = criteriaQuery.from(History.class);
        Join<Habits, History> habitJoin = historyRoot.join("habit");

        Predicate userPredicate = criteria.equal(habitJoin.get("user"), user);

        criteriaQuery.multiselect(habitJoin.get("id"), habitJoin.get("days"), criteria.max(historyRoot.get("day")))
                .where(userPredicate)
                .groupBy(habitJoin.get("id"), habitJoin.get("days"));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
