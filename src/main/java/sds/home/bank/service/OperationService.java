package sds.home.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import sds.home.bank.component.SelfLinkToEntityConverter;
import sds.home.bank.entity.Account;
import sds.home.bank.entity.AccountLine;
import sds.home.bank.repository.AccountLineRepository;
import sds.home.bank.repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class OperationService {

    @Autowired
    private AccountLineRepository accountLineRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SelfLinkToEntityConverter selfLinkToEntityConverter;

    public void importFileContent(String fileContent, String url) {
        var opt = selfLinkToEntityConverter.findBySelfLink(Link.of(url), Account.class);
        opt.ifPresent(account -> {
            fileContent.lines()
                    .peek(System.out::println)
                    .map(line -> map(line, account.getId()))
                    .forEach(accountLineRepository::save);
        });
    }
    private AccountLine map(String line, Long accountId) {
        String[] row = splitCSVLine(line);
        var account = new Account();
        account.setId(accountId);
        var accountLine = new AccountLine();
        accountLine.setDate(LocalDate.parse(row[1], DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        accountLine.setAmount(new BigDecimal(sanitizeAmount(row[5])));
        accountLine.setAccount(account);
        accountLine.setDetails(row[3]);
        return accountLine;
    }
    private String[] splitCSVLine(String line) {
        char[] cTab = line.toCharArray();
        String[] row = new String[9];
        int index = 0;
        boolean quoted = false;
        String content = "";
        for(char c : cTab){
            if(c == '"') {
                quoted = !quoted;
                continue;
            }
            if(quoted){
                content += c;
                continue;
            }
            if(c == ',') {
                row[index++] = content;
                content = "";
                continue;
            }
            content += c;
        }
        return row;
    }
    private String sanitizeAmount(String amount){
        return amount
                .replaceAll(" ", "")
                .replaceAll("€", "")
                .replaceAll("\"", "")
                .replaceAll(" ", "")
                .replaceAll(",", ".");
    }
}
