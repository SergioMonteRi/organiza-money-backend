package com.organizaMoney.service.expenses.domain;

import com.organizaMoney.service.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tb_expense_type")
@Entity
@Getter
@Setter
public class ExpenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
