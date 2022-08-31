package com.example.demo.Member;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
  private final EntityManager em;

  public void save(Member member){
    em.persist(member);
  }

  public Member findById(Long memberId){
    return em.find(Member.class, memberId);
  }

  public List<Member> findAll(){
    return em.createQuery("select m from Member m").getResultList();
  }

  public List<Member> findByName(String name){
    return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
  }

  public void delete(Member member){
    em.remove(member);
  }
}
