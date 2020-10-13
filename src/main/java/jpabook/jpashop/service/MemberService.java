package jpabook.jpashop.service;

import jpabook.jpashop.api.MemberApiController;
import jpabook.jpashop.domain.Member;

import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-17
 */
public interface MemberService {

  Long join(Member member);

  List<Member> findMembers();

  Member findOne(Long id);

  Member update(Long id,
                MemberApiController.UpdateMemberRequest request);

}
