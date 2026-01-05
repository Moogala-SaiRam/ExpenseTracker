package com.springboot.ExpenseTracker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@Table(name = "expense_track")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExpenseTrack {
    @Id
    private int id;
    private double amount;
    @Column(name = "date")
    private Date date;
    private String description;
}
