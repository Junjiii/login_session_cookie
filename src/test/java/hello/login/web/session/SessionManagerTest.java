package hello.login.web.session;

import hello.login.domain.member.Member;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    public void sessionTest() throws Exception
    {
        // given
        MockHttpServletResponse response = new MockHttpServletResponse();
        // when
        Member member = new Member();
        sessionManager.createSession(member,response);
        // 생성
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }
}