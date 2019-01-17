package com.jun.login.config.users;

import com.jun.login.config.security.SecurityUser;
import com.jun.login.domain.Member.Member;
import com.jun.login.repository.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member users = repo.findByMid(username);
        return
                repo.findById(users.getMidx())
                        .filter(m -> m != null)
                        .map(m -> new SecurityUser(m)).get();

    }
}
