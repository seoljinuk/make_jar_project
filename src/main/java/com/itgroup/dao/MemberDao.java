package com.itgroup.dao;

import com.itgroup.bean.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 데이터 베이스와 직접 연동하여 CRUD 작업을 수행해주는 DAO 클래스
public class MemberDao extends SuperDao{
    public MemberDao() {

    }

    public int updateData(Member bean) {
        // 수정된 나의 정보 bean를 사용하여 데이터 베이스에 수정합니다.
        int cnt = -1 ;

        String sql = "update members set name = ?, password = ?, gender = ?, birth = ?, marriage = ?, salary = ?, address = ?, manager = ?" ;
        sql += " where id = ? " ;

        Connection conn = null ;
        PreparedStatement pstmt = null ;

        try{
            conn = super.getConnection() ;
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bean.getName());
            pstmt.setString(2, bean.getPassword());
            pstmt.setString(3, bean.getGender());
            pstmt.setString(4, bean.getBirth());
            pstmt.setString(5, bean.getMarriage());
            pstmt.setInt(6, bean.getSalary());
            pstmt.setString(7, bean.getAddress());
            pstmt.setString(8, bean.getManager());
            pstmt.setString(9, bean.getId());

            cnt = pstmt.executeUpdate() ;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }finally {
            try{
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cnt ;
    }

    public int insertData(Member bean) {
        // 웹 페이지에서 회원 정보를 입력하고 '가입' 버튼을 눌렀습니다.
        int cnt = -1 ;

        String sql = "insert into members(id, name, password, gender, birth, marriage, salary, address, manager)" ;
        sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?)" ;

        Connection conn = null ;
        PreparedStatement pstmt = null ;

        try{
            conn = super.getConnection() ;
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bean.getId());
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getPassword());
            pstmt.setString(4, bean.getGender());
            pstmt.setString(5, bean.getBirth());
            pstmt.setString(6, bean.getMarriage());
            pstmt.setInt(7, bean.getSalary());
            pstmt.setString(8, bean.getAddress());
            pstmt.setString(9, bean.getManager());

            cnt = pstmt.executeUpdate() ;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }finally {
            try{
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cnt ;
    }

    public int getSize() {
        String sql = "select count(*) as cnt from members  ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int cnt = 0; // 검색된 회원 명수
        try {
            conn = super.getConnection(); // 접속 객체 구하기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public Member getMemberOne(String id) {
        // 로그인 id 정보를 이용하여 해당 사용자의 정보를 bean 형태로 반환해줍니다.
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member bean = null; // 찾고자하는 회원의 정보

        String sql = "select * from members where id = ? ";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 1건 발견됨
                bean = new Member();
                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBirth(String.valueOf(rs.getDate("birth")));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return bean;
    }

    public int deleteData(String id) { // 기본키를 사용하여 회원 탈퇴를 시도합니다.
        int cnt = -1 ;
        String sql = "delete from members where id = ? " ;

        PreparedStatement pstmt = null;
        Connection conn = null;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            cnt = pstmt.executeUpdate() ;

            conn.commit();
        }catch (Exception ex){
            try{
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
            ex.printStackTrace();
        }finally{
            try{
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt ;
    }

    public List<Member> selectAll() {
        List<Member> members = new ArrayList<Member>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select * from members order by name asc ";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getInt(7));
//                System.out.println(rs.getString("id"));
//                System.out.println(rs.getString("gender"));

                Member bean = new Member();
                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBirth(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                members.add(bean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return members;
    }

    public List<Member> findByGender(String gender) {
        // 성별 컬럼 gender을 사용하여 특정 성별의 회원들만 조회합니다.
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select * from members where gender = ? ";

        List<Member> members = new ArrayList<Member>();

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gender);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Member bean = new Member();
                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBirth(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                members.add(bean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return members;
    }


}