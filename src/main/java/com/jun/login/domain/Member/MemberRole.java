package com.jun.login.domain.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member_roles")
@EqualsAndHashCode(of = "fno")
@ToString
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @Column(columnDefinition = "varchar(100) COMMENT '역할 종류 (BASIC, MANAGER, ADMIN)'")
    private String roleName;
}
