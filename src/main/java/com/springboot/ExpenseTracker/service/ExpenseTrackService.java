package com.springboot.ExpenseTracker.service;

import com.springboot.ExpenseTracker.model.ExpenseTrack;
import com.springboot.ExpenseTracker.repo.ExpenseTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseTrackService {

    @Autowired
    private ExpenseTrackRepo repo;

    public List<ExpenseTrack> getAllExpenses() {
        return repo.findAll();
    }
    public ExpenseTrack getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ExpenseTrack addOrUpdateExpense(ExpenseTrack expenseTrack) {
        ExpenseTrack track = new ExpenseTrack();
        track.setId(expenseTrack.getId());
        track.setAmount(expenseTrack.getAmount());
        track.setDate(expenseTrack.getDate());
        track.setDescription(expenseTrack.getDescription());
        repo.save(track);
        return track;
    }


    public String deleteExpense(int id) {
        repo.deleteById(id);
        return "Deleted";
    }

    public List<ExpenseTrack> searchByKey(String keyword) {
        return repo.findByDescriptionContaining(keyword);
    }

    public List<ExpenseTrack> saveAll(List<ExpenseTrack> expenses) {
        return repo.saveAll(expenses);
    }

    public List<ExpenseTrack> searchBetweenDate(LocalDate from, LocalDate to) {
        return repo.findByDateBetween(from,to);
    }

    public List<ExpenseTrack> searchByDate(LocalDate date) {
        return repo.findByDate(date);
    }
    public ExpenseTrack updateById(ExpenseTrack expenseTrack,int id){
        ExpenseTrack track = repo.findById(id).get();
        track.setAmount(expenseTrack.getAmount());
        track.setDate(expenseTrack.getDate());
        track.setDescription(expenseTrack.getDescription());
        repo.save(track);
        return track;
    }
}
