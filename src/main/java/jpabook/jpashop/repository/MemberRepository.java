package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final EntityManager em;

  public Long save(Member member) {
    em.persist(member);
    return member.getId();
  }

  public Member findById(Long id) {
    return em.find(Member.class, id);
  }

  public Member findByName(String name) {
    return em.createQuery("select m from Member m where m.name = :name", Member.class)
      .setParameter("name", name)
      .getResultList().stream().findFirst().orElse(null);
  }

  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class)
      .getResultList();
  }

  public List<Member> findAllByName(String name) {
    return em.createQuery("select m from Member m where m.name = :name", Member.class)
      .setParameter("name", name)
      .getResultList();
  }

}
