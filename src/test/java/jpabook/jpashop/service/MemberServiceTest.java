package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

//    기본적으로 Test의 Transactional은 Rollback이다.
//    @Rollback(false) < 쿼리 rollback 안 됨 로 두거나 em.flush() < 쿼리 rollback됨 로 쿼리를 볼수있다.
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

        @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

//        em.flush(); // 쿼리를 볼수 있고, 테스트 종료후에 rollback된다.
        assertEquals(member, memberRepository.findOne(savedId));
    }

//    IllegalStateException
    @Test(expected = IllegalArgumentException.class)
    public void 중복회원예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2);

        fail("에외가 발생해야한다.");
    }

}