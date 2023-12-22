package sds.home.bank.entity.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import sds.home.bank.entity.Account;

import java.math.BigDecimal;

@Projection(name = "sumAccount", types = Account.class)
public interface AccountProjection {

    @Value("#{@accountLineRepository.sumAccountLinesByAccount(target)}")
    BigDecimal getSum();
}
