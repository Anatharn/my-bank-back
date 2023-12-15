package sds.home.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sds.home.bank.entity.Account;

@RepositoryRestResource(path = "accounts")
public interface AccountRepository extends JpaRepository<Account, Long> {
}
