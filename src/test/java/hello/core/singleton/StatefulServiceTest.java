package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSringleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        int userA = statefulService1.order("userA", 10000);

        //ThreadA: B사용자가 20000원 주문
        int userB = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        System.out.println("userA: " + userA);
        System.out.println("userB: " + userB);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}