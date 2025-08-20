package com.itgroup;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;
import com.itgroup.dao.BoardDao;
import com.itgroup.dao.MemberDao;

import java.util.List;
import java.util.Scanner;

// 메인 클래스 대신 실제 모든 업무를 총 책임지는 매니저 클래스
public class DataManager {
    private MemberDao mdao = null ; // 실제 데이터 베이스와 연동하는 다오 클래스
    private BoardDao bdao = null ;
    private Scanner scan = null ; // 회원 정보 입력 받기 위한 스캐너 장치

    public DataManager() {
        this.mdao = new MemberDao() ;
        this.bdao = new BoardDao();
        this.scan = new Scanner(System.in);
    }


    public void updateData() {
        int cnt = -1 ;

        System.out.println("수정하고자 하는 회원 id 입력 : ");
        String findId = scan.next() ; // yusin 입력

        // 여기서 bean은 이전에 입력했던 나의 정보입니다.
        Member bean = mdao.getMemberOne(findId) ;

        // 편의상 내 이름과 결혼 여부를 변경해 보겠습니다.
        System.out.println("이름 입력 : ");
        String name = scan.next();

        System.out.println("결혼 여부 입력 : ");
        String marriage = scan.next();

        bean.setName(name);
        bean.setMarriage(marriage);

        cnt = mdao.updateData(bean);

        if(cnt == -1){
            System.out.println("업데이트 실패");

        }else if(cnt == 1){
            System.out.println("업데이트 성공");

        }else{

        }
    }


    public void insertData() {
        Member bean = new Member();
        int cnt = -1 ;

        // 편의상 2~3개만 입력 받도록 합니다.
        System.out.println("id 입력 : ");
        String id = scan.next() ;

        System.out.println("이름 입력 : ");
        String name = scan.next() ;

        // 다음은 회원 가입 페이지에서 기입한 내용입니다.
        bean.setId(id);
        bean.setName(name);
        bean.setPassword("abc123");
        bean.setGender("남자");
        bean.setBirth("2025/08/20");
        bean.setMarriage("결혼");
        bean.setSalary(100);
        bean.setAddress("서대문");
        bean.setManager(null);

        cnt = mdao.insertData(bean);

        if(cnt == -1){
            System.out.println("회원 가입 실패");

        }else if(cnt == 1){
            System.out.println("회원 아이디 " +  id + "로 가입 성공");

        }else{

        }
    }

    public void deleteData() { // 나의 id를 사용한 탈퇴
        String id = "yusin";
        int cnt = - 1 ;
        cnt = mdao.deleteData(id);

        if(cnt == -1){
            System.out.println("회원 탈퇴 실패(접속, 네트워크 오류)");
        }else if(cnt == 0){
            System.out.println("탈퇴할 회원이 존재하지 않습니다.");
        }else if(cnt > 0){
            System.out.println("회원 탈퇴 완료");
        }else{

        }
    }

    public void getMemberOne() {
        String findId = "xx" ; // 찾고자 하는 회원
        Member someone = mdao.getMemberOne(findId);

        if(someone == null){
            System.out.println("찾으시는 회원이 존재하지 않습니다.");
        }else{
            String id = someone.getId() ;
            String name = someone.getName();;
            String gender = someone.getGender();
            String message = id + "\t" + name + "\t" + gender  ;
            System.out.println(message);
        }
    }



    public void selectAll() { // 모든 회원 정보 조회
        List<Member> members = mdao.selectAll();
        System.out.println("이름\t급여\t주소");
        for(Member bean:members){
            String name = bean.getName();
            int salary = bean.getSalary();
            String address = bean.getAddress() ;
            String message = name + "\t" + salary + "\t" + address ;
            System.out.println(message);
        }
    }

    public void findByGender() {
        String tempgender = "여자" ;
        List<Member> mydata = mdao.findByGender(tempgender);
        System.out.println("이름\t급여\t주소\t성별");
        for(Member bean:mydata){
            String name = bean.getName();
            int salary = bean.getSalary();
            String address = bean.getAddress() ;
            String gender = bean.getGender();
            String message = name + "\t" + salary + "\t" + address + "\t" + gender  ;
            System.out.println(message);
        }
    }

    public void getSize() { // 몇 명의 회원인지 조회하는 구문입니다.
        int cnt = mdao.getSize();
        String message ;
        if(cnt == 0){
            message = "검색된 회원이 존재하지 않습니다.";
        }else{
            message = "검색된 회원은 총 " + cnt + "명입니다.";
        }
        System.out.println(message);
    }

    public void selectAllBoard() {
        List<Board> boardList = bdao.selectAll();

        System.out.println("글번호\t작성자\t글제목\t글내용");
        for(Board bean : boardList){
            int no = bean.getNo() ;
            String writer = bean.getWriter();
            String subject = bean.getSubject();
            String content = bean.getContent();
            String message = no + "\t" + writer + "\t" + subject + "\t" + content ;
            System.out.println(message);
        }
    }

    public void selectEvenData() {
        // 게시물 번호가 짝수인 항목들만 조회해 봅니다.
        List<Board> boardList = bdao.selectEvenData();

        System.out.println("글번호\t작성자\t글제목\t글내용");
        for(Board bean : boardList){
            int no = bean.getNo() ;
            String writer = bean.getWriter();
            String subject = bean.getSubject();
            String content = bean.getContent();
            String message = no + "\t" + writer + "\t" + subject + "\t" + content ;
            System.out.println(message);
        }
    }
}
