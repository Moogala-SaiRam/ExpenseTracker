package com.springboot.ExpenseTracker.controller;

import com.springboot.ExpenseTracker.service.ExpenseTrackService;
import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseTrackController {

    @Autowired
    private ExpenseTrackService service;

    @GetMapping("/hello")
    public String healthCheck(){
        return "Welcome";
    }

    @GetMapping("/expenses")
    public List<ExpenseTrack> getExpenses(){
        return service.getAllExpenses();
    }

    @GetMapping("/expense/{id}")
    public ExpenseTrack getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/expense")
    public ExpenseTrack addExpenses(@RequestBody ExpenseTrack expenseTrack){
        return service.addOrUpdateExpense(expenseTrack);
    }

    @PutMapping("/expense/{id}")
    public ExpenseTrack updateExpenses(@RequestBody ExpenseTrack expenseTrack,@PathVariable("id") int id){
        return service.addOrUpdateExpense(expenseTrack);
    }
    @DeleteMapping("/expense/{id}")
    public String deleteExpense(@PathVariable int id){
        return service.deleteExpense(id);
    }

}
