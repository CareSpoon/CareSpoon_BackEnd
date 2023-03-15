package com.carespoon.domain.user;

import com.carespoon.Repository.UserInfoRepository;
import com.carespoon.domain.UserInfo;
import com.carespoon.dto.UserInfoSaveRequestDto;
import com.carespoon.dto.UserInfoUpdateRequestDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.lang.Long.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class userInfoApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @After
    public void tearDown() throws Exception{
        userInfoRepository.deleteAll();
    }
    
    @Test
    public void UserInfo_등록된다() throws Exception{
        //given
        Long userId = valueOf(1);
        int age = 20;
        int sex = 1;
        double height = 172.2;
        double weight = 56.3;
        double metabolicRate = 447.6 + (9.25*weight)+ 3.1*height - 4.33*age;

        UserInfoSaveRequestDto userInfoSaveRequestDto = UserInfoSaveRequestDto.builder()
                .userId(userId)
                .age(age)
                .height(height)
                .sex(sex)
                .weight(weight)
                .build();
        
        String url = "http://localhost:" + port + "/userinfo";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, userInfoSaveRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<UserInfo> all= userInfoRepository.findAll();

        AssertionsForClassTypes.assertThat(all.get(0).getUserId()).isEqualTo(userId);
        AssertionsForClassTypes.assertThat(all.get(0).getWeight()).isEqualTo(weight);
        AssertionsForClassTypes.assertThat(all.get(0).getSex()).isEqualTo(sex);
        AssertionsForClassTypes.assertThat(all.get(0).getHeight()).isEqualTo(height);
        AssertionsForClassTypes.assertThat(all.get(0).getAge()).isEqualTo(age);
        AssertionsForClassTypes.assertThat(all.get(0).getMetabolicRate()).isEqualTo(metabolicRate);
    }

    @Test
    public void userInfo_수정된다() throws Exception{
        //given
        UserInfo savedUserInfo = userInfoRepository.save(UserInfo.builder()
                        .height(170)
                        .weight(56)
                        .sex(1)
                        .age(20)
                        .userId(1)
                .build());

        Long updatedId = savedUserInfo.getUserId();
        int grownHeight = 172;
        int grownWeight = 54;

        UserInfoUpdateRequestDto userInfoUpdateRequestDto = UserInfoUpdateRequestDto.builder()
                .height(grownHeight)
                .weight(grownWeight)
                .build();

        String url = "http://localhost:" + port +"/userinfo/update/" + updatedId;

        HttpEntity<UserInfoUpdateRequestDto> requestEntity = new HttpEntity<>(userInfoUpdateRequestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<UserInfo> all= userInfoRepository.findAll();

        assertThat(all.get(0).getWeight()).isEqualTo(grownWeight);
        assertThat(all.get(0).getHeight()).isEqualTo(grownHeight);
    }
} 
