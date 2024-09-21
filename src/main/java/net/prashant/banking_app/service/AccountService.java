package net.prashant.banking_app.service;

import net.prashant.banking_app.dto.AccountDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(  Long id);
    AccountDto deposite(Long id, double amount);
    AccountDto withDraw(Long id, double amount);
    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);
}
