package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by JongHyun Lee on 2020-09-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private EntityManager em;

  @Test
  public void 회원가입() {
    // given
    Member member = new Member("이름", new Address("서울시","동대문구","123456"));

    // when
    Long saveId = memberService.join(member);

    // then
    em.flush();
    assertEquals(member, memberService.findOne(saveId));
  }

  @Test(expected = IllegalStateException.class)
  public void 중복_회원_예외() throws IllegalStateException {
    // given
    Member oneMember = new Member("이름1", new Address("서울시","동대문구","123456"));
    Member twoMember = new Member("이름1", new Address("서울시","동대문구","123456"));

    // when
    memberService.join(oneMember);
    memberService.join(twoMember);

    // then
    fail("예외가 발생해야 한다.");
  }

}