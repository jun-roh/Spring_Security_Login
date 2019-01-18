package com.jun.login.controller;

import com.jun.login.domain.Member.Member;
import com.jun.login.repository.Member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model){
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
        memberRepository.save(member);
        return "/joinResult";
    }

    @GetMapping("/accessDenied")
    public void accessDenied() {
    }

    @PostMapping("/logout")
    public void logout() {
    }

    @GetMapping("/basic")
    public String basicView(Model model){
        model.addAttribute("user", getUserDetails());
        return "/basic";
    }

    @GetMapping("/manager")
    public String managerView(Model model){
        model.addAttribute("user", getUserDetails());
        return "/manager";
    }

    @GetMapping("/admin")
    public String adminView(Model model){
        model.addAttribute("user", getUserDetails());
        return "/admin";
    }

    public User getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
}
