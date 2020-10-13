package jpabook.jpashop.service.impl;

import jpabook.jpashop.api.MemberApiController;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


/**
 * Created by JongHyun Lee on 2020-09-17
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  /**
   * 회원 가입
   * @param member 사용자 정보
   * @return long
   */
  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member);
    return memberRepository.save(member);
  }

  /**
   * 회원 전체 조회
   * @return 검색 된 회원 정보 목록
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 회원 정보 상세 조회
   * @param id 회원 정보 id
   * @return 검색 된 회원 정보
   */
  public Member findOne(Long id) {
    return memberRepository.findById(id);
  }

  @Override
  @Transactional
  public Member update(Long id,
                       MemberApiController.UpdateMemberRequest request) {
    Member member = memberRepository.findById(id);
    member.setName(request.getName());
    return member;
  }

  /**
   * 회원 정보 저장시 사용자명 중복 체크
   * @param member 검색 된 사용자 정보
   */
  private void validateDuplicateMember(Member member) {
    if (!ObjectUtils.isEmpty(memberRepository.findByName(member.getName()))) {
      throw new IllegalStateException("이미 존재하는 회원 입니다.");
    }

  }

}
