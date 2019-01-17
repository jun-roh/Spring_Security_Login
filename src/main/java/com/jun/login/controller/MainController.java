package com.jun.login.controller;

import com.jun.login.domain.Member.Member;
import com.jun.login.repository.Member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/join")
    public String join(){
        return "/join";
    }

    @Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member){
        String encryptPw = passwordEncoder.encode(member.getMpw());

        member.setMpw(encryptPw);
        System.out.println(member.getMid());
        System.out.println(member.getMname());
        System.out.println(member.getServiceType());
        System.out.println(member.getMemberRoles().size());
        System.out.println(member.getMpw());

        memberRepository.save(member);
        return "/joinResult";
    }
}
