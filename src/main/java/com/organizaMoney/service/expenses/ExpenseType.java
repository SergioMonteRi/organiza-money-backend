package com.organizaMoney.service.expenses;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_expense_type")
@Entity
@Getter
@Setter
public class ExpenseType {
    @Id
    Long id;
    String name;
}
