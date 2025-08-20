package com.itgroup.bean;

public class Board {
    private int no ;
    private String writer ;
    private String password ;
    private String subject ;
    private String content ;
    private int readhit ;
    private String regdate ;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadhit() {
        return readhit;
    }

    public void setReadhit(int readhit) {
        this.readhit = readhit;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "no=" + no +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", readhit=" + readhit +
                ", regdate='" + regdate + '\'' +
                '}';
    }

    public Board() {
    }

    public Board(int no, String writer, String password, String subject, String content, int readhit, String regdate) {
        this.no = no;
        this.writer = writer;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.readhit = readhit;
        this.regdate = regdate;
    }
}
