package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.dto.MemberDto;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by JongHyun Lee on 2020-09-27
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/members/new")
  public String createMemberForm(Model model) {
    model.addAttribute("memberForm", new MemberDto.MemberForm());
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String createMember(@Valid MemberDto.MemberForm memberForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "members/createMemberForm";
    }

    Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipCode());

    Member member = new Member(memberForm.getName(), address);
    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String memberList(Model model) {
    // TODO : Entity 보다는 Dto 변환해서 보내주는 것이 제일 좋다. (API 개발 시 Entity 절대 넘기면 안됨)
    model.addAttribute("members", memberService.findMembers());
    return "members/memberList";
  }


}
