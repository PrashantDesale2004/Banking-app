package net.prashant.banking_app.mapper;

import net.prashant.banking_app.dto.AccountDto;
import net.prashant.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account =new Account(accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }
    public static AccountDto mapToAccountDTO(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }


}
