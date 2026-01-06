package com.springboot.ExpenseTracker.controller;

import com.springboot.ExpenseTracker.model.dto.ExpenseRequestDTO;
import com.springboot.ExpenseTracker.model.dto.ExpenseResponseDTO;
import com.springboot.ExpenseTracker.service.ExpenseTrackService;
import com.springboot.ExpenseTracker.model.ExpenseTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class ExpenseTrackController {

    @Autowired
    private ExpenseTrackService service;

    @GetMapping("/hello")
    public String healthCheck(){
        return "Welcome";
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpenses(){
        return new ResponseEntity<>(service.getAllExpenses(), HttpStatus.OK);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        ExpenseResponseDTO responseDTO = null;
        try{
            responseDTO = service.getById(id);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/expenses/search")
    public ResponseEntity<?> searchByKeyword(@RequestParam String keyword){
        List<ExpenseResponseDTO> searchList = null;
        try {
            searchList = service.searchByKey(keyword);
            return new ResponseEntity<>(searchList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/expenses/search/betweenDates")
    public ResponseEntity<?> searchByDate(@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy")
                                                               LocalDate from,
                                                           @RequestParam(required = false)
                                                           @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to){
        List<ExpenseResponseDTO> searchList = null;
        try {
            searchList = service.searchBetweenDate(from,to);
            return new ResponseEntity<>(searchList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expenses/search/date")
    public ResponseEntity<?> searchByDate(@RequestParam(required = false)
                                                               @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        List<ExpenseResponseDTO> searchList = null;
        try {
            searchList = service.searchByDate(date);
            if(searchList.isEmpty()){
                throw new NullPointerException();
            }
            return new ResponseEntity<>(searchList,HttpStatus.OK);

        }catch (NullPointerException ne){
            return new ResponseEntity<>(ne.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/expense")
    public ResponseEntity<?> addExpenses(@RequestBody ExpenseRequestDTO expenseRequest){
        ExpenseResponseDTO responseDTO = null;
        try{
            responseDTO = service.addExpense(expenseRequest);
            return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/expenses/bulk")
    public ResponseEntity<List<ExpenseTrack>> addAll(@RequestBody List<ExpenseTrack> expenses){
        return new ResponseEntity<>(service.saveAll(expenses),HttpStatus.OK);
    }
    @PutMapping("/expense/{id}")
    public ResponseEntity<?> updateExpenses(@RequestBody ExpenseRequestDTO requestDTO,@PathVariable("id") int id){
        ExpenseResponseDTO  responseDTO = null;
        try{
            responseDTO = service.updateById(requestDTO,id);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id){
        try{
            return new ResponseEntity<>(service.deleteExpense(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
