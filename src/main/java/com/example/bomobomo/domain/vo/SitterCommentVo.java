package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterCommentVo {

       private Long sitterCommentNumber;
       private String sitterCommentContent;
       private String sitterCommentRegisterDate;
       private String sitterCommentModifyDate;
       private Long sitterBoardNumber;
       private Long userNumber;
       private String userId;
       private Long sitterCommentXo;

}
