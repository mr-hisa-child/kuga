package presentation.controller.score;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreRequestBody {
    private List<String> memberList;
}