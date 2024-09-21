package net.prashant.banking_app.controller;

import net.prashant.banking_app.dto.AccountDto;
import net.prashant.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account REST API.
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
        // get account rest-Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id ){
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }

    //Deposite REST API
    @PutMapping("/{id}/deposite")
    public ResponseEntity<AccountDto> deposite(@PathVariable  Long id, @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.deposite(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        AccountDto  accountDto = accountService.withDraw(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAcount(){
        List<AccountDto> allAccount = accountService.getAllAccount();
        return new ResponseEntity<>(allAccount ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return  ResponseEntity.ok("Account is deleted sucessfully" );
    }
}

