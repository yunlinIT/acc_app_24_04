package com.koreait.exam.acc_app_2024_04.cotroller;

import com.koreait.exam.acc_app_2024_04.app.member.controller.MemberController;
import com.koreait.exam.acc_app_2024_04.app.member.service.MemberService;
import com.koreait.exam.acc_app_2024_04.app.order.controller.OrderController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class OrderControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("주문 상세화면")
    @WithUserDetails("user1") ////////// 로그인 한 회원
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/order/3"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(OrderController.class))
                .andExpect(handler().methodName("showDetail"))
                .andExpect(content().string(containsString("주문")));
    }


}
