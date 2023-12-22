package sds.home.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sds.home.bank.entity.Bank;

@RepositoryRestResource(path = "banks")
public interface BankRepository extends JpaRepository<Bank, Long> {

}
