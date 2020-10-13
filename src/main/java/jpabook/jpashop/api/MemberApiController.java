package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JongHyun Lee on 2020-10-06
 */
@RestController
@RequiredArgsConstructor
public class MemberApiController {

  // TODO : 절대 Entity 외부 노출은 안된다.
  // TODO : 반드시 Entity -> Dto 변환이 필요하다.
  private final MemberService memberService;

  @GetMapping("/api/v1/members")
  public List<Member> getMembersV1() {
    return memberService.findMembers();
  }

  @GetMapping("/api/v2/members")
  public Result<List<MemberDto>> getMemberV2() {
    List<MemberDto> collect = memberService.findMembers()
      .stream().map(m -> new MemberDto(m.getName())).collect(Collectors.toList());
    return new Result<>(collect.size(), collect);
  }

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Long id = memberService.join(new Member(request.getName()));
    return new CreateMemberResponse(id);
  }

  @PutMapping("/api/v2/members/{id}")
  public UpdateMemberResponse updateMemberV2(@PathVariable Long id,
                                             @RequestBody @Valid UpdateMemberRequest request) {
    memberService.update(id, request);
    Member findMember = memberService.findOne(id);
    return new UpdateMemberResponse(findMember.getId(), findMember.getName());
  }

  @Getter @Setter
  @AllArgsConstructor
  static class Result<T> {
    private int count;
    private T data;
  }

  @Getter @Setter
  @AllArgsConstructor
  static class MemberDto {
    private String name;
  }

  @Getter @Setter
  public static class CreateMemberRequest {

    @NotEmpty
    private String name;
  }

  @Getter @Setter
  public static class UpdateMemberRequest {

    @NotEmpty
    private String name;
  }

  @Getter @Setter
  @AllArgsConstructor
  public static class CreateMemberResponse {
    private Long id;
  }

  @Getter @Setter
  @AllArgsConstructor
  public static class UpdateMemberResponse {
    private Long id;
    private String name;
  }

}
