package domain.model.member;

import org.springframework.util.StringUtils;

import domain.KugaDomainException;
import lombok.Getter;

public class MemberName {
    @Getter
    private final String value;

    public MemberName(String value) throws KugaDomainException {
        if (StringUtils.isEmpty(value)) {
            throw new KugaDomainException("氏名が空です", value);
        }
        this.value = value;
    }
}
