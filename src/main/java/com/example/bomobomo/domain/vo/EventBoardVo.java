package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EventBoardVo {

       private Long eventBoardNumber;
       private Long userNumber;
       private String userId;
       private Long eventNumber;
       private String eventBoardContent;
       private String eventBoardRegisterDate;
       private String eventBoardModifyDate;
       private Integer rating;
       private Integer eventBoardCount;
       private String eventName;
       private Long rnum;

}
