package sds.home.bank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sds.home.bank.entity.AccountLine;

@RepositoryRestResource(path="account-lines")
public interface AccountLineRepository extends JpaRepository<AccountLine, Long> {

    Page<AccountLine> findByAccountId(Long id, Pageable pageable);
}
