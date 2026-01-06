package com.springboot.ExpenseTracker.service;

import com.springboot.ExpenseTracker.model.ExpenseTrack;
import com.springboot.ExpenseTracker.model.dto.ExpenseRequestDTO;
import com.springboot.ExpenseTracker.model.dto.ExpenseResponseDTO;
import com.springboot.ExpenseTracker.repo.ExpenseTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseTrackService {

    @Autowired
    private ExpenseTrackRepo repo;

    public List<ExpenseResponseDTO> getAllExpenses() {
        List<ExpenseTrack> expensesList = repo.findAll();
        List<ExpenseResponseDTO> expenseResponseDTOList = new ArrayList<>();

        for(ExpenseTrack track : expensesList){
            ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                    track.getId(),
                    track.getAmount(),
                    track.getDate(),
                    track.getDescription()
            );
            expenseResponseDTOList.add(responseDTO);
        }

        return expenseResponseDTOList;
    }
    public ExpenseResponseDTO getById(int id) {
        ExpenseTrack expense = repo.findById(id).orElse(null);
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getDate(),
                expense.getDescription()
        );
        return responseDTO;
    }

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO expenseRequest) {
        // ExpenseRequest --> Entity
        ExpenseTrack expense = new ExpenseTrack();
        expense.setAmount(expenseRequest.amount());
        expense.setDate(expenseRequest.date());
        expense.setDescription(expenseRequest.description());
        ExpenseTrack savedExpense = repo.save(expense);
        //Entity --> ExpenseResponse
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getDate(),
                savedExpense.getDescription()
        );
        return responseDTO;
    }


    public String deleteExpense(int id) {
        repo.deleteById(id);
        return "Deleted";
    }

    public List<ExpenseResponseDTO> searchByKey(String keyword) {
        List<ExpenseTrack> expensesList = repo.findByDescriptionContaining(keyword);
        List<ExpenseResponseDTO> expenseResponseDTOList = new ArrayList<>();

        for(ExpenseTrack track : expensesList){
            ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                    track.getId(),
                    track.getAmount(),
                    track.getDate(),
                    track.getDescription()
            );
            expenseResponseDTOList.add(responseDTO);
        }

        return expenseResponseDTOList;
    }

    public List<ExpenseTrack> saveAll(List<ExpenseTrack> expenses) {
        return repo.saveAll(expenses);
    }

    public List<ExpenseResponseDTO> searchBetweenDate(LocalDate from, LocalDate to) {
        List<ExpenseTrack> expensesList = repo.findByDateBetween(from,to);
        List<ExpenseResponseDTO> expenseResponseDTOList = new ArrayList<>();

        for(ExpenseTrack track : expensesList){
            ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                    track.getId(),
                    track.getAmount(),
                    track.getDate(),
                    track.getDescription()
            );
            expenseResponseDTOList.add(responseDTO);
        }

        return expenseResponseDTOList;
    }

    public List<ExpenseResponseDTO> searchByDate(LocalDate date) {
        List<ExpenseTrack> expensesList = repo.findByDate(date);
        List<ExpenseResponseDTO> expenseResponseDTOList = new ArrayList<>();

        for(ExpenseTrack track : expensesList){
            ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                    track.getId(),
                    track.getAmount(),
                    track.getDate(),
                    track.getDescription()
            );
            expenseResponseDTOList.add(responseDTO);
        }

        return expenseResponseDTOList;
    }
    public ExpenseResponseDTO updateById(ExpenseRequestDTO requestDTO,int id){
        ExpenseTrack expense = repo.findById(id).orElse(null);

        expense.setAmount(requestDTO.amount());
        expense.setDate(requestDTO.date());
        expense.setDescription(requestDTO.description());

        ExpenseTrack savedExpense = repo.save(expense);
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getDate(),
                savedExpense.getDescription()
        );
        return responseDTO;

    }
}
