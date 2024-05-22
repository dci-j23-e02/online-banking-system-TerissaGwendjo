package org.example.online_banking_system.controller;

import org.example.online_banking_system.dto.TransferForm;
import org.example.online_banking_system.model.Account;
import org.example.online_banking_system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/accounts") //specifies the base URL path for all request mapping methods inside the controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/accounts/new")
    public String showNewAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "new-account"; // Corresponding HTML file
    }

    @PostMapping("/accounts/new")
    public String openNewAccount(@RequestParam("accountNumber") Long accountNumber,
                                 @RequestParam("accountType") String accountType,
                                 @RequestParam("initialDeposit") Double initialDeposit) {
        accountService.openNewAccount(accountNumber, accountType, initialDeposit);
        return "redirect:/";
    }


    @GetMapping("/transactions/deposit")
    public String showDepositForm(Model model) {
        model.addAttribute("accountId",0); // Dummy value
        return "deposit"; // Corresponding HTML file
    }

    @PostMapping("/transactions/deposit")
    public String depositMoney(@RequestParam Long accountNumber, @RequestParam Double amount, Model model) {

        try {
            accountService.depositMoney(accountNumber, amount);
            return "redirect:/"; // Redirect to home page
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "deposit"; // Return to deposit form with error message
        }
    }

    @GetMapping("/transactions/withdraw")
    public String showWithdrawForm(Model model) {
        model.addAttribute("accountId", 0); // Dummy value
        return "withdraw"; // Corresponding HTML file
    }

    @PostMapping("/transactions/withdraw")
    public String withdrawMoney(@RequestParam Long accountNumber, @RequestParam Double amount, Model model) {

        try {
            accountService.withdrawMoney(accountNumber, amount);
            return "redirect:/"; // Redirect to home page
        }  catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "withdraw"; // Return to withdraw form with error message
        }
    }

    @GetMapping("/transactions/transfer")
    public String showTransferForm(Model model) {
        model.addAttribute("transferForm", new TransferForm());
        return "transfer"; // Corresponding transfer HTML file
    }

    @PostMapping("/transactions/transfer")
    public String transferFunds(@ModelAttribute TransferForm transferForm, Model model) {
        try {
            accountService.transferFunds(transferForm.getFromAccountNumber(), transferForm.getToAccountNumber(), transferForm.getAmount());
            return "redirect:/"; // Redirect to home page on success
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "transfer"; // Return to transfer form with error message
        }
    }
}
