package webapp.kuga.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountTeam {
    private String accountId;
    private String teamId;
}