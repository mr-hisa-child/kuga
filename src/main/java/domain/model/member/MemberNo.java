package domain.model.member;

import domain.KugaDomainException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberNo {
    private int value;

    public MemberNo(int value) {
        this.value = value;
    }

    public MemberNo(String value) throws KugaDomainException {
        try {
            this.value = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new KugaDomainException("背番号が数値ではありません。", value);
        }
    }
}
