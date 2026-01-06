package com.springboot.ExpenseTracker.model.dto;

import java.time.LocalDate;

public record ExpenseRequestDTO(
        Double amount,
        LocalDate date,
        String description
) {

}
