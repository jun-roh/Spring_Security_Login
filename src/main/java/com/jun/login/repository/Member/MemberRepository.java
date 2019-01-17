package com.jun.login.repository.Member;

import com.jun.login.domain.Member.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByMid(String username);
    Member findByMidx(Long midx);
}
