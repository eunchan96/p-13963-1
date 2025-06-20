package com.back.global.initData;

import com.back.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final WiseSayingService wiseSayingService;

    @Bean
    ApplicationRunner baseinitApplicationRunner() {
        return args -> {
            if(wiseSayingService.count() > 0) return;

            wiseSayingService.write("인생은 짧고 예술은 길다.", "히포크라테스");
            wiseSayingService.write("행복은 습관이다. 그것을 몸에 지니라.", "허버트 조지 웰스");
            wiseSayingService.write("성공은 최후의 결과가 아니라 과정이다.", "존 우든");
            wiseSayingService.write("우리는 우리가 반복하는 것이다. 그러므로 탁월함은 행위가 아니라 습관이다.", "아리스토텔레스");
        };
    }
}