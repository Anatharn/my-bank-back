package sds.home.bank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sds.home.bank.entity.Account;
import sds.home.bank.entity.AccountLine;

import java.math.BigDecimal;

@RepositoryRestResource(path="account-lines")
public interface AccountLineRepository extends JpaRepository<AccountLine, Long> {

    Page<AccountLine> findByAccount(Account account, Pageable pageable);

    @Query("SELECT sum(amount) FROM AccountLine al WHERE al.account = ?1")
    BigDecimal sumAccountLinesByAccount(Account account);
}
