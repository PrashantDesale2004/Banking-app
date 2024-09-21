package net.prashant.banking_app.service.impl;

import net.prashant.banking_app.dto.AccountDto;
import net.prashant.banking_app.entity.Account;
import net.prashant.banking_app.mapper.AccountMapper;
import net.prashant.banking_app.repository.AccountRepository;
import net.prashant.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account =accountRepository
               .findById(id)
               .orElseThrow(()->new RuntimeException("Account Doesn't exist") );
        AccountDto accountDto = AccountMapper.mapToAccountDTO(account);
        return accountDto;
    }

    @Override
    public AccountDto deposite(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doesn't exist"));
        double total =account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }
    @Override
    public AccountDto withDraw(Long id, double amount){
        Account account = accountRepository.
                findById(id).
                orElseThrow(()->new RuntimeException("Account doesn't exist"));
        if(account.getBalance() < amount){
            System.out.println("insufficient balanced");
           throw new RuntimeException("Insufficient Amount");
        }
        double newAmount = account.getBalance()-amount;
        account.setBalance(newAmount);
        Account save = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(save);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account>  list = accountRepository.findAll();
        return list.stream().map((account -> AccountMapper.mapToAccountDTO(account)))
                .collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }

}
