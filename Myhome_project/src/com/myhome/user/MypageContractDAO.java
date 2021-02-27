package com.myhome.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myhome.DBUtil;

public class MypageContractDAO {


	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private CallableStatement cstat;

	public MypageContractDAO() {
		// DB 연결
		conn = DBUtil.open();
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//리스트 요청
	public ArrayList<MypageContractDTO> contractList(int seqUser) {
		ArrayList<MypageContractDTO> list = new ArrayList<MypageContractDTO>();
		
		try {
			
			String sql = "select * from vwContractDetail where sSeqUser = ? or bSeqUser = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, seqUser);
			pstat.setInt(2, seqUser);
			
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				MypageContractDTO dto = new MypageContractDTO();
				
				dto.setSeqContract(rs.getInt("seqContract"));
				dto.setsName(rs.getString("sName"));
				dto.setbName(rs.getString("bName"));
				dto.setContractorName(rs.getString("contractorName"));
				dto.setContractState(rs.getString("contractState"));
				
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	//전자계약 세부내용 요청!
	public MypageContractDTO getContractDetail(int seqUser, int seqContract) {
		MypageContractDTO dto = new MypageContractDTO();
		
		try {
			
			String sql = "select * from vwContractDetail where seqContract = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, seqContract);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				dto.setSeqContract(rs.getInt("seqContract"));
				dto.setContractState(rs.getString("contractState"));
				dto.setContractorSign(rs.getString("contractorSign"));
				dto.setLessorSign(rs.getString("lessorSign"));
				dto.setTenantSign(rs.getString("tenantSign"));
				dto.setContractDate(rs.getDate("contractDate"));
				dto.setContractContent(rs.getString("contractContent"));
				dto.setSeqContractorProperty(rs.getInt("seqContractorProperty"));
				
				dto.setbSeqUser(rs.getInt("bSeqUser"));
				dto.setbName(rs.getString("bName"));
				dto.setbPhoneNumber(rs.getNString("bPhoneNumber"));
				dto.setbAddress(rs.getString("bAddress"));
				dto.setbIdNumber(rs.getString("bIdNumber"));
				
				dto.setsName(rs.getString("sName"));
				dto.setsPhoneNumber(rs.getNString("sPhoneNumber"));
				dto.setsAddress(rs.getString("sAddress"));
				dto.setsIdNumber(rs.getString("sIdNumber"));
				
				dto.setContractorName(rs.getString("contractorName"));
				dto.setcPhoneNumber(rs.getString("cPhoneNumber"));
				dto.setcAddress(rs.getString("cAddress"));
				dto.setCompanyNumber(rs.getString("companyNumber"));
				
				dto.setDeposit(rs.getInt("deposit"));
				dto.setMonthlyRent(rs.getInt("monthlyRent"));
				dto.setDealing(rs.getInt("dealing"));
				dto.setContractPeriod(rs.getInt("contractPeriod"));
				
				dto.setContractType(rs.getString("contractType"));
				dto.setLocation(rs.getString("location"));
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}

	// 계약 파기하기 버튼 클릭 ..
	public int cancelContract(int seqContract) {
		
		int result = 0;
		
		try {
			
			String sql = "update tblContract set contractState = '취소' where seqContract = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, seqContract);
			
			result = pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	
	}

	// 계약 채결
	public int contract(int seqContractorProperty, int seqContract) {
		
		int result = 0;

		try {
			
			String sql = "{call procContract(?,?)}";
			
			cstat = conn.prepareCall(sql);
			cstat.setInt(1, seqContractorProperty);
			cstat.setInt(2, seqContract);
			
			result = cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}







