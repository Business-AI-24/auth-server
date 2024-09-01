package com.sparta.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Table(name = "p_user")
@SQLDelete(sql = "UPDATE p_user SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
public class User extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_public", columnDefinition = "boolean default true")
    private Boolean is_public = true;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Notice> noticeList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Complaint> complaintList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Store> storeList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();


}

