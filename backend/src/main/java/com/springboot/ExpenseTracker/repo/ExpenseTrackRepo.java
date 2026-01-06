package com.springboot.ExpenseTracker.repo;

import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseTrackRepo extends JpaRepository<ExpenseTrack,Integer> {
    List<ExpenseTrack> findByDescriptionContaining(String keyword);
    List<ExpenseTrack> findByDateBetween(LocalDate from,LocalDate to);

    List<ExpenseTrack> findByDate(LocalDate date);
}
