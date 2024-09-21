package net.prashant.banking_app.repository;

import net.prashant.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
