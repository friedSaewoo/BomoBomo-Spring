package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterBoardVo {

   private Long sitterBoardNumber;
   private Long userNumber;
   private Long empNumber;
   private String userId;
   private String sitterBoardContent;
   private String sitterBoardRegisterDate;
   private String sitterBoardModifyDate;
   private Integer rating;
   private Integer sitterBoardCount;
   private String empName;
   private Long rnum;
//   private String empImgName;
//   private String empImgUuid;
//   private String empImgUploadPath;

}
