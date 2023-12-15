package sds.home.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "account")
    private List<AccountLine> accountLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountLine> getAccountLines() {
        return accountLines;
    }

    public void setAccountLines(List<AccountLine> accountLines) {
        this.accountLines = accountLines;
    }
}
