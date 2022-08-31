package com.example.demo.Member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional
  public Long save(Member member){
    memberRepository.save(member);
    return member.getId();
  }

  public Member findById(Long memberId){
    Member member = memberRepository.findById(memberId);
    return member;
  }

  public List<Member> findAll(){
    List<Member> members = memberRepository.findAll();
    return members;
  }

  public List<Member> findByName(String name){
    List<Member> members = memberRepository.findByName(name);
    return members;
  }

  @Transactional
  public void updateName(Long memberId, String name){
    Member member = memberRepository.findById(memberId);
    member.updateName(name);
  }
}
