package UserDetails.UserDetails.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "user_details")
public class User {
    @Id
    private  String userId ;
    private  String contestId;
    private List<QuestionStatus> questionStatusList;
    private Integer totalScore;


}
