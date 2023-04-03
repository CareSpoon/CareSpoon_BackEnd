package com.carespoon.UserInfo.repository;

import com.carespoon.user.domain.User;
import com.carespoon.UserInfo.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUser(User user);
}
