package com.springboot.ExpenseTracker.controller;

import com.springboot.ExpenseTracker.service.ExpenseTrackService;
import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
    @GetMapping("/expenses/search")
    public ResponseEntity<List<ExpenseTrack>> searchByKeyword(@RequestParam String keyword){
        return new ResponseEntity<>(service.searchByKey(keyword),HttpStatus.OK);
    }

    @GetMapping("/expenses/search/betweenDates")
    public ResponseEntity<List<ExpenseTrack>> searchByDate(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy")
                                                               LocalDate from,
                                                           @RequestParam(required = false)
                                                           @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to){
        return new ResponseEntity<>(service.searchBetweenDate(from,to),HttpStatus.OK);
    }

    @GetMapping("/expenses/search/date")
    public ResponseEntity<List<ExpenseTrack>> searchByDate(@RequestParam(required = false)
                                                               @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        return new ResponseEntity<>(service.searchByDate(date),HttpStatus.OK);
    }
    @PostMapping("/expense")
    public ResponseEntity<ExpenseTrack> addExpenses(@RequestBody ExpenseTrack expenseTrack){
        return new ResponseEntity<>(service.addOrUpdateExpense(expenseTrack),HttpStatus.CREATED);
    }

    @PostMapping("/expenses/bulk")
    public ResponseEntity<List<ExpenseTrack>> addAll(@RequestBody List<ExpenseTrack> expenses){
        return new ResponseEntity<>(service.saveAll(expenses),HttpStatus.OK);
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
