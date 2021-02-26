package com.myhome.admin.moveclean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.myhome.DBUtil;

public class CleanDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public CleanDAO() {
		
		//DB 연결
		conn = DBUtil.open();
	}
	
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<CleanDTO> list(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			
			if(map.get("search") != null) {
				
				where = String.format("where name like '%%%s%%'", map.get("search"));
			}
			
			//String sql = "select * from vwClean order by seq asc";
			String sql = String.format("select * from (select a.*, rownum as rnum from (select * from vwClean %s order by seq desc) a) where rnum between %s and %s"
					, where
					, map.get("begin")
					, map.get("end"));
			
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<CleanDTO> list = new ArrayList<CleanDTO>();
			
			while (rs.next()) {
				//레코드 1줄 -> DTO 1개
				
				CleanDTO dto = new CleanDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setLocation(rs.getString("location"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setBusinessnum(rs.getString("businessnum"));
				dto.setPrice(rs.getString("price"));
				dto.setIntroduce(rs.getString("introduce"));
				
				list.add(dto);
				
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {

		try {
			
			String where = "";
			
			if(map.get("search") != null) {
				
				where = String.format("where name like '%%%s%%'", map.get("search"));
			}
			
			String sql = String.format("select count(*) as cnt from vwClean %s", where);
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}



