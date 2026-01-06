package com.springboot.ExpenseTracker.model.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;

public record ExpenseResponseDTO(
        Integer id,
        Double amount,
        LocalDate date,
        String description
) {

}
