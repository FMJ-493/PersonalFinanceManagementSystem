package com.example.FinanceManagementApp.Repository;

import com.example.FinanceManagementApp.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query(value="SELECT `transactionid`, `amount`, `category`, `notes`, `trans_date`, `trans_type`,u.name FROM `transactions` t JOIN users u ON t.userid = u.user_id",nativeQuery = true)
    public List<Map<String,String>> viewAllTransactions();

    @Query(value="SELECT `transactionid`, `amount`, `category`, `notes`, `trans_date`, `trans_type`,u.name FROM `transactions` t JOIN users u ON t.userid = u.user_id WHERE t.userid = ?1",nativeQuery = true)
    public List<Map<String,String>> viewMyTransactions(int userid);

    @Query(value = "SELECT category, SUM(amount) as total_amount FROM transactions WHERE userid = ?1 AND trans_type = 'Expense' GROUP BY category", nativeQuery = true)
    List<Map<String, Object>> getExpenseSummaryByCategory(int userid);

    @Query(value = "SELECT SUM(amount) as total_expense FROM transactions WHERE userid = ?1 AND trans_type = 'Expense'", nativeQuery = true)
    Map<String, Object> getTotalExpense(int userid);

}