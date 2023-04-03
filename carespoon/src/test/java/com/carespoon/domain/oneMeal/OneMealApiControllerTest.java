package com.carespoon.domain.oneMeal;

import com.carespoon.oneMeal.repository.OneMealRepository;
import com.carespoon.oneMeal.domain.OneMeal;
import com.carespoon.oneMeal.dto.OneMealSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static java.lang.Long.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OneMealApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OneMealRepository oneMealRepository;

    @After
    public void tearDown() throws Exception{
        oneMealRepository.deleteAll();
    }
    @Test
    public void OneMeal_등록된다() throws Exception {
        //given
        int kcal = 170;
        int carbon = 13;
        int fat = 20;
        int protein = 10;

       OneMealSaveRequestDto oneMealSaveRequestDto = OneMealSaveRequestDto.builder()
                .meal_Kcal(kcal)
                .meal_Carbon(carbon)
                .meal_Fat(fat)
                .meal_Protein(protein)
                .build();

        String url = "http://localhost:" + port + "/onemeal";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, oneMealSaveRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<OneMeal> all = oneMealRepository.findAll();
        assertThat(all.get(0).getMeal_Carbon()).isEqualTo(carbon);
        assertThat(all.get(0).getMeal_Fat()).isEqualTo(fat);
        assertThat(all.get(0).getMeal_Protein()).isEqualTo(protein);
        assertThat(all.get(0).getMeal_Kcal()).isEqualTo(kcal);
    }
}
