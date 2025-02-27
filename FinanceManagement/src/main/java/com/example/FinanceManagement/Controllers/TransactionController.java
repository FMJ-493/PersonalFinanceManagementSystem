package com.example.FinanceManagementApp.Controllers;

import com.example.FinanceManagementApp.Models.Transactions;
import com.example.FinanceManagementApp.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepo;

    @PostMapping("/addTransaction")
    public ResponseEntity<Map<String,String>> addTransaction(@RequestBody Transactions transaction){
        Transactions transObj = transactionRepo.save(transaction);

        Map<String,String> response = new HashMap<>();

        if(transObj.getTransactionID() != 0){
            response.put("Status","Transaction successful");
        }
        else{
            response.put("Status","Transaction failed");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/viewAllTransactions")
    public ResponseEntity<List<Map<String,String>>> viewAllTransactions(){
        List<Map<String,String>> transObj = transactionRepo.viewAllTransactions();

        return ResponseEntity.ok(transObj);
    }

    @PostMapping("/viewMyTransactions")
    public ResponseEntity<List<Map<String,String>>> viewMyTransactions(@RequestBody Transactions transaction){
        List<Map<String,String>> transObj = transactionRepo.viewMyTransactions(transaction.getUserID());

        return ResponseEntity.ok(transObj);
    }
    @GetMapping("/expenseSummary")
    public ResponseEntity<Map<String, Object>> getExpenseSummary(@RequestBody Transactions transaction) {
        int userId = transaction.getUserID();
        List<Map<String, Object>> categoryExpenses = transactionRepo.getExpenseSummaryByCategory(userId);
        Map<String, Object> totalExpense = transactionRepo.getTotalExpense(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("categoryExpenses", categoryExpenses);
        response.put("totalExpense", totalExpense.get("total_expense")); // Extract total amount

        return ResponseEntity.ok(response);
    }

}