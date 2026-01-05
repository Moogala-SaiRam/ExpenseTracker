package com.springboot.ExpenseTracker.repo;

import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTrackRepo extends JpaRepository<ExpenseTrack,Integer> {
}
