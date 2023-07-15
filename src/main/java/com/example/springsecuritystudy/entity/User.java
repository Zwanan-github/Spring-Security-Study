package com.example.springsecuritystudy.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    // 自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long uid;

    //用户登录凭证
    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "pass_word")
    private String password;

    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )

    //多方默认懒加载, 在Service层加上@Transactional保持session
    @ManyToMany(cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getUid() != null && Objects.equals(getUid(), user.getUid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
