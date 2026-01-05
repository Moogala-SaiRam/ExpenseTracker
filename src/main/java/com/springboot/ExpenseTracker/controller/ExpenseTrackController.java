package com.springboot.ExpenseTracker.controller;

import com.springboot.ExpenseTracker.service.ExpenseTrackService;
import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ExpenseTrack>> getExpenses(){
        return new ResponseEntity<>(service.getAllExpenses(), HttpStatus.OK);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<ExpenseTrack> getById(@PathVariable int id){
        ExpenseTrack track = service.getById(id);;
        if(track != null){
            return new ResponseEntity<>(track,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(track,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/expense")
    public ResponseEntity<ExpenseTrack> addExpenses(@RequestBody ExpenseTrack expenseTrack){
        return new ResponseEntity<>(service.addOrUpdateExpense(expenseTrack),HttpStatus.CREATED);
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<ExpenseTrack> updateExpenses(@RequestBody ExpenseTrack expenseTrack,@PathVariable("id") int id){
        return new ResponseEntity<>(service.addOrUpdateExpense(expenseTrack),HttpStatus.OK);
    }
    @DeleteMapping("/expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id){
        return new ResponseEntity<>(service.deleteExpense(id),HttpStatus.OK);
    }

}
