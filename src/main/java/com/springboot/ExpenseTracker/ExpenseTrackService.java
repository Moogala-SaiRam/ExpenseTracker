package com.springboot.ExpenseTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTrackService {

    @Autowired
    private ExpenseTrackRepo repo;

    public List<ExpenseTrack> getAllExpenses() {
        return repo.findAll();
    }

    public ExpenseTrack addExpense(ExpenseTrack expenseTrack) {
        ExpenseTrack track = new ExpenseTrack();
        track.setId(expenseTrack.getId());
        track.setAmount(expenseTrack.getAmount());
        track.setDate(expenseTrack.getDate());
        track.setDescription(expenseTrack.getDescription());
        repo.save(track);
        return track;
    }
}
