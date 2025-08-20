package com.itgroup.dao;

import com.itgroup.bean.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends SuperDao{
    public BoardDao() {
        super();
    }

    public List<Board> selectAll() {
        // 전체 게시물을 최신 항목부터 조회하여 반환합니다.
        List<Board> boardList = new ArrayList<Board>();

        Connection conn = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select * from boards order by no desc " ;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql) ;
            rs = pstmt.executeQuery() ;

            while(rs.next()){
                Board bean = this.makeBean(rs);
                boardList.add(bean) ;
            }

        }catch (Exception ex){
               ex.printStackTrace();
        }finally {
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return boardList ;
    }

    private Board makeBean(ResultSet rs) {
        // ResultSet에서 데이터를 읽어 와서 Bean 객체에 담아 반환합니다.
        Board bean = null ;

        try {
            bean = new Board() ;
            bean.setNo(rs.getInt("no"));
            bean.setWriter(rs.getString("writer"));
            bean.setPassword(rs.getString("password"));
            bean.setSubject(rs.getString("subject"));
            bean.setContent(rs.getString("content"));
            bean.setReadhit(rs.getInt("readhit"));
            bean.setRegdate(rs.getString("regdate"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean ;
    }

    public List<Board> selectEvenData() {
        // 전체 게시물을 최신 항목부터 조회하여 반환합니다.
        List<Board> boardList = new ArrayList<Board>();

        Connection conn = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select * from boards where mod(no, 2) = 0 order by no desc " ;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql) ;
            rs = pstmt.executeQuery() ;

            while(rs.next()){
                Board bean = this.makeBean(rs);
                boardList.add(bean) ;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return boardList ;
    }
}
