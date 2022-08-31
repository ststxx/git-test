package com.example.demo.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Member {
  
  @Id @GeneratedValue
  @Column(name="member_id")
  private Long id;
  
  private String name;

  protected Member(){}

  public Member(String name){
    this.name = name;
  }

  public void updateName(String name){
    this.name = name;
  }
}