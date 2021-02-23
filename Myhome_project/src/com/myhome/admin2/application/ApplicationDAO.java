package com.myhome.admin2.application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.myhome.DBUtil;
import com.sun.glass.ui.Pixels.Format;

public class ApplicationDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	private CallableStatement ct;

	public ApplicationDAO() {
		conn = DBUtil.open();
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("close : " + e);
		}
	}

	public ArrayList<ApplicationDTO> list(HashMap<String, String> map) {
		// TODO Auto-generated method stub

		try {

			String where = "";

			if (map.get("search") != null) {
				where = String.format(" where title like '%%%s%%' or " + "content like '%%%s%%'", map.get("search"),
						map.get("search"));
			}

			String sql = String.format("select * from "
					+ "(select a.*, rownum as rnum from tblApplication a %s order by seqApplication desc) "
					+ "where rnum between %s and %s ", where, map.get("begin"),map.get("end"));

			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ArrayList<ApplicationDTO> list = new ArrayList<ApplicationDTO>();

			while (rs.next()) {
				ApplicationDTO dto = new ApplicationDTO();

				dto.setSeqApplication(rs.getString("seqApplication"));
				dto.setSeqAdmin(rs.getString("seqAdmin"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getString("viewcount"));
				dto.setSavefile(rs.getString("savefile"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {

			System.out.println("lsit : " + e);
		}
		return null;
	}

	public int write(ApplicationDTO dto) {
		// 이미지 부터 삽입하기 쿼리가 2개 이다. 흠.. 어떻게 하지?.. proc?.

		try {

			String sql = "{ call procaddApplication ( ?,?,?,?) }";

			if (dto.getSavefile() == null) {
				dto.setSavefile(" ");
			}

			ct = conn.prepareCall(sql);
			ct.setString(1, dto.getSeqAdmin());
			ct.setString(2, dto.getTitle());
			ct.setString(3, dto.getContent());
		
			ct.setString(4, dto.getSavefile());
			
			
			
			int result = ct.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("write : " + e);

		}

		return 0;
	}

	// 삭제요 리스
	public ArrayList<ApplicationDTO> list(String[] seqApplication) {

		try {

			int count = seqApplication.length;
			String in = "";

			if (seqApplication.length != 1) {
				for (int i = 0; i < count - 1; i++) {
					in += seqApplication[i] + ",";
				}

				in += seqApplication[count - 1];
			} else {
				in = seqApplication[0];
			}

			String sql = String.format(
					"select * from tblApplication where seqApplication in ( %s ) order by seqApplication desc", in);

			System.out.println(sql);
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			ArrayList<ApplicationDTO> list = new ArrayList<ApplicationDTO>();

			while (rs.next()) {
				ApplicationDTO dto = new ApplicationDTO();
				dto.setSeqApplication(rs.getString("seqApplication"));
				dto.setSeqAdmin(rs.getString("seqAdmin"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getString("viewcount"));
				dto.setSavefile(rs.getString("savefile"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {

			System.out.println("listde : " + e);
		}

		return null;
	}

	// 배열 받아ㅓ 삭제
	public int delete(String[] seqApplication) {

		int count = seqApplication.length;
		String in = "";

		if (seqApplication.length != 1) {
			for (int i = 0; i < count - 1; i++) {
				in += seqApplication[i] + ",";
			}

			in += seqApplication[count - 1];
		} else {
			in = seqApplication[0];
		}

		try {
			String sql = String.format("delete tblApplication where seqApplication in ( %s ) ", in);

			ps = conn.prepareStatement(sql);

			return ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("delete : " + e);
		}

		// TODO Auto-generated method stub
		return 0;
	}

	// 상세 출력
	public ApplicationDTO view(String seq) {
		try {

			String sql = String
					.format("select * from tblApplication where seqApplication = %s order by seqApplication desc", seq);
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				ApplicationDTO dto = new ApplicationDTO();
				dto.setSeqApplication(rs.getString("seqApplication"));
				dto.setSeqAdmin(rs.getString("seqAdmin"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWritedate(rs.getString("writedate"));
				dto.setViewcount(rs.getString("viewcount"));
				dto.setSavefile(rs.getString("savefile"));

				return dto;

			}

		} catch (Exception e) {

			System.out.println("view : " + e);
		}

		return null;
	}

	public void count(String seq) {
		// TODO Auto-generated method stub

		String sql = "update tblApplication set viewcount = viewcount+1" + " where seqApplication =" + seq;

		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("count : " + e);
		}

	}

	public int totalCount(HashMap<String, String> map) {

		try {

			String where = "";

			if (map.get("search") != null) {
				where = String.format(" where title like '%%%s%%' or " + "content like '%%%s%%'", map.get("search"),
						map.get("search"));
			}

			String sql = String.format("select count(*) as count from tblApplication %s " + " order by seqApplication desc", where);

			st = conn.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("count");
			}

		} catch (Exception e) {
			System.out.println( "total : "+ e);
		}

		return 0;
	}

	
	//수정
	public int edit(ApplicationDTO dto) {
		try {

			String sql = "{ call procEditApplication ( ?,?,?,?) }";

			if (dto.getSavefile() == null) {
				dto.setSavefile(" ");
			}

			ct = conn.prepareCall(sql);
			
			ct.setString(1, dto.getSeqApplication());
			ct.setString(2, dto.getTitle());
			ct.setString(3, dto.getContent());
			ct.setString(4, dto.getSavefile());

			
			int result = ct.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("edit : " + e);

		}

		return 0;
	}

}
