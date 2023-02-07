package UserDetails.UserDetails.entity;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class QuestionStatus {
    private String questionId;
    private Integer score;
}
