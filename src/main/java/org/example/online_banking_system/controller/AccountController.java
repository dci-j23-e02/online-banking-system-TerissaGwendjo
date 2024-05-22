package org.example.online_banking_system.controller;

import org.example.online_banking_system.dto.TransferForm;
import org.example.online_banking_system.model.Account;
import org.example.online_banking_system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
/**
 * This class handles incoming requests related to account management and transactions.
 * It interacts with the `AccountService` to perform operations on accounts.
 */
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    /**
     * Handles the GET request for the home page.
     * @return The logical view name for the home page template ("homePage").
     */
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/accounts/new")
    //Handles the GET request to display the new account form.
    public String showNewAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "new-account"; // Corresponding HTML file
    }

    @PostMapping("/accounts/new")
    //Handles the POST request to submit the new account form.
    public String openNewAccount(@RequestParam("accountNumber") Long accountNumber,
                                 @RequestParam("accountType") String accountType,
                                 @RequestParam("initialDeposit") Double initialDeposit) {
        accountService.openNewAccount(accountNumber, accountType, initialDeposit);
        return "redirect:/";
    }


    @GetMapping("/transactions/deposit")
    /**
     * Handles the GET request to display the deposit form.
     * @param model A Spring MVC model object used to pass data to the template.
     * @return The logical view name for the deposit form template ("deposit").
     */
    public String showDepositForm(Model model) {
        model.addAttribute("accountId",0); // Dummy value
        return "deposit"; // Corresponding  deposit HTML file
    }

    @PostMapping("/transactions/deposit")
    // Handles the POST request to submit the deposit form.
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
    //Handles the GET request to show the withdraw form.
    public String showWithdrawForm(Model model) {
        model.addAttribute("accountId", 0); // Dummy value
        return "withdraw"; // Corresponding HTML file
    }

    @PostMapping("/transactions/withdraw")
    // Handles the POST request to submit the withdraw form.
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
    // Handles the GET request to show the transfer form.
    public String showTransferForm(Model model) {
        model.addAttribute("transferForm", new TransferForm());
        return "transfer"; // Corresponding transfer HTML file
    }

    @PostMapping("/transactions/transfer")
    // Handles the POST request to submit the transfer form.
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
