package com.example.demo.Member;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping("api/v1/members")
  public CreateMemberResponse save(@RequestBody @Valid CreateMemberRequest request){
    Member member = new Member(request.getName());
    memberService.save(member);
    return new CreateMemberResponse(member.getId());
  }
  
  @GetMapping("api/v1/members")
  public Result findAll(){
    List<Member> members = memberService.findAll();
    List<MemberDto> memberDtos = members.stream().map(m -> new MemberDto(m)).collect(Collectors.toList());
    return new Result(memberDtos);
  }

  @GetMapping("api/v1/members/{id}")
  public MemberDto findById(@PathVariable("id") Long id){
    Member member = memberService.findById(id);
    MemberDto memberDto = new MemberDto(member);
    return memberDto;
  }

  @GetMapping("api/v1/members/query")
  public Result findByName(@RequestParam(value = "name", defaultValue = "") String name){
    List<Member> members = memberService.findByName(name);
    List<MemberDto> memberDtos = members.stream().map(m -> new MemberDto(m)).collect(Collectors.toList());
    return new Result(memberDtos);
  }

  @PutMapping("api/v1/members/{id}")
  public UpdateMemberResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){
    memberService.updateName(id, request.getName());
    Member member = memberService.findById(id);
    return new UpdateMemberResponse(member);
  }

  @Data
  @AllArgsConstructor
  static class MemberDto{
    private Long id;
    private String name;

    public MemberDto(Member member){
      this.id = member.getId();
      this.name = member.getName();
    }
  }

  @Data
  @AllArgsConstructor
  static class Result<T>{
    private T data;
  }

  @Data
  @AllArgsConstructor
  static class CreateMemberResponse{
    private Long memberId;
  }

  @Data
  static class CreateMemberRequest{
    private String name;
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberResponse{
    private Long id;
    private String name;
    public UpdateMemberResponse(Member member){
      this.id = member.getId();
      this.name = member.getName();
    }
  }

  @Data
  static class UpdateMemberRequest{
    private String name;
  }
}
