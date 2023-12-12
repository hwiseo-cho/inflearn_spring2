package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memeberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memeberService.join(member);

        Member findMember = memeberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find member: " + findMember.getName());
    }
}
