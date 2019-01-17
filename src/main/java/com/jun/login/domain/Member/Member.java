package com.jun.login.domain.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "members")
@EqualsAndHashCode(of = "mid")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long midx;

    @Column(columnDefinition = "varchar(255) COMMENT '로그인 아이디'")
    private String mid;

    @Column(columnDefinition = "varchar(255) COMMENT '로그인 패스워드'")
    private String mpw;

    @Column(columnDefinition = "varchar(255) COMMENT '유저 이름(센터대표명 or 회원이름)'")
    private String mname;

    @Column(columnDefinition = "integer COMMENT '회원 상태 정보(0: 탈퇴, 1: 서비스중)'")
    private int serviceType = 1;

    @Column(columnDefinition = "datetime COMMENT '등록 날짜 시간'")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(columnDefinition = "datetime COMMENT '업데이트 날짜 시간'")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member")
    private List<MemberRole> memberRoles;
}
