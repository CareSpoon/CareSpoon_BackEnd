package com.carespoon.domain.user;


import com.carespoon.UserInfo.repository.UserInfoRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Long.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userInfoRepositoryTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @After
    public void cleanup(){
        userInfoRepository.deleteAll();
    }

//    @Test
//    public void 사용자정보_불러오기(){
//        long userId = valueOf(1);
//        int age = 20;
//        int sex = 1;
//        double height = 172.2;
//        double weight = 56.3;
//        double metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;
//        userInfoRepository.save(UserInfo.builder()
//                        .userId(userId)
//                        .age(age)
//                        .height(height)
//                        .sex(sex)
//                        .weight(weight)
//                        .build());
//
//        List<UserInfo> userInfoList = userInfoRepository.findAll();
//
//        UserInfo userInfo = userInfoList.get(0);
//
//        assertThat(userInfo.getUserId()).isEqualTo(userId);
//        assertThat(userInfo.getWeight()).isEqualTo(weight);
//        assertThat(userInfo.getSex()).isEqualTo(sex);
//        assertThat(userInfo.getHeight()).isEqualTo(height);
//        assertThat(userInfo.getAge()).isEqualTo(age);
//        assertThat(userInfo.getMetabolicRate()).isEqualTo(metabolicRate);
//    }
}
