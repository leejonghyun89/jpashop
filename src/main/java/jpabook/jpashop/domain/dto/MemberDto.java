package jpabook.jpashop.domain.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by JongHyun Lee on 2020-09-27
 */
public class MemberDto {

  @Getter @Setter
  public static class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipCode;
  }

}
