package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional  // 테스트에 있으면 기본적으로 롤백을 한다.
    @Rollback(false) // 테스트에서 실행한 쿼리를 저장하고 싶을때
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        // jar파일 만들어 배포하는 역할

        // ./gradlew clean build
        // ./gradlew clean build --stacktrace
        // ./gradlew clean build --debug  >> Connect refused
        /*
            wsl에서 윈도우 로컬포트가 뚤려있지 않음
            h2포트 포워딩 해주어야 함
         */

    }
}