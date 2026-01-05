package com.springboot.ExpenseTracker;

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

    @PostMapping("/expense")
    public ExpenseTrack addExpenses(@RequestBody ExpenseTrack expenseTrack){
        return service.addExpense(expenseTrack);
    }

}
