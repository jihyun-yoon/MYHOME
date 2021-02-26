package com.myhome.contractor.mypage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myhome.DBUtil;

/**
 * 
 * @ 중개인 & 임대인 전체매물 DAO 매칭여부 확인 가능
 *
 **/
public class PropertyDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	// 생성자
	public PropertyDAO() {

		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ContractorDAO()");
			e.printStackTrace();
		}

	}

	// 1. 내가 올린 매물리스트
	public ArrayList<PropertyDTO> list(int seqContractor) {

		try {

			String sql = "select * from vuploadProperty where seqContractor = ?";

			// 중개인매물 번호 넘겨주기
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, seqContractor);

			rs = pstat.executeQuery();

			// ResultSet -> ArrayList<DTO>
			ArrayList<PropertyDTO> list = new ArrayList<PropertyDTO>();

			while (rs.next()) {
				// 레코드 1줄 -> DTO 1개
				PropertyDTO dto = new PropertyDTO();

				// 모든매물 seq
				dto.setSeqProperty(rs.getInt("seqProperty_lp"));
				dto.setSeqLessorProperty(rs.getInt("seqLessorProperty"));
				dto.setSeqContractorProperty(rs.getInt("seqContractorProperty"));

				// 중개인 seq
				dto.setSeqContractor(rs.getInt("seqContractor"));

				// 1. 방옵션
				dto.setRoomType(rs.getString("roomType"));
				dto.setSpacing(rs.getString("spacing"));

				System.out.println(dto.getRoomType());
				System.out.println(dto.getSpacing());

				// 2. 계약형태
				dto.setContractTypeDetail(rs.getString("contractType"));
				System.out.println(dto.getContractTypeDetail());

				// 보증금
				String deposit = String.format("%,d" + "원", rs.getInt("deposit"));
				dto.setDeposit(deposit);
				System.out.println(dto.getDeposit());

				// 월세
				String monthlyRent = String.format("%,d" + "원", rs.getInt("monthlyPay"));
				dto.setMonthlyRent(monthlyRent);

				// 매매

				String temp;
				String dealing = "";

				temp = rs.getString("dealing");

				System.out.println(temp);
				if (temp == null) {
					dealing = "";
					dto.setDealing(dealing);
				}

				else {
					dealing = String.format("%,d" + "원", Integer.parseInt(temp));
					dto.setDealing(dealing);
				}

				// System.out.println(dto.getDealing());

				// 기간
				dto.setContractPeriod(rs.getString("contractPeriod") + "개월");

				System.out.println(dto.getContractPeriod());

				// 3. 관리비
				dto.setInternet(rs.getInt("internet"));
				dto.setCable(rs.getInt("cabletv"));

				dto.setCleaning(rs.getInt("cleaning"));
				dto.setWater(rs.getInt("water"));
				dto.setGas(rs.getInt("gas"));
				dto.setElectric(rs.getInt("electric"));

				String serviceCharge = String.format("%,d" + "원", (rs.getInt("serviceCharge")));
				dto.setServiceCharge(serviceCharge);

				System.out.println(dto.getInternet());

				// 4. 건물옵션
				dto.setParking(rs.getInt("parking"));
				dto.setElevator(rs.getInt("elevator"));
				dto.setPet(rs.getInt("pet"));
				dto.setWindow(rs.getInt("window"));
				dto.setFloor(rs.getString("floor"));

				// 5. 방옵션 12개
				dto.setAirconditional(rs.getInt("airconditional"));
				dto.setWasher(rs.getInt("washer"));
				dto.setBed(rs.getInt("bed"));
				dto.setDesk(rs.getInt("desk"));
				dto.setCloset(rs.getInt("closet"));
				dto.setTv(rs.getInt("tv"));
				dto.setShoebox(rs.getInt("shoebox"));
				dto.setRefrigerator(rs.getInt("refrigerator"));
				dto.setStove(rs.getInt("stove"));
				dto.setInduction(rs.getInt("induction"));
				dto.setMicrowave(rs.getInt("microwave"));
				dto.setBidet(rs.getInt("bidet"));

				// 6. 지역
				dto.setLocation(rs.getString("location"));

				// System.out.println(dto.getLocation());
				// 7. 이미지
				dto.setPath(rs.getString("imagePath"));

				// System.out.println(dto.getPath());

				// 8. 입주 가능 or 계약완료
				dto.setAvailable(rs.getString("available"));

				// System.out.println(dto.getAvailable());

				// 9. 중개인 정보
				dto.setCompanyName(rs.getString("companyName"));
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("nameC"));

				// 최종넣기
				dto.setSeqContractor(rs.getInt("seqContractor"));

				// System.out.println(dto.getSeqContractor());
				dto.setOccupancyDate(rs.getString("occupancyDate"));
				// System.out.println(dto.getOccupancyDate());

				dto.setTitle(rs.getString("title"));
				// System.out.println(dto.getTitle());

				dto.setContent(rs.getString("content"));
				// System.out.println(dto.getContent());

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("list: " + e);
		}

		return null;
	}

	// 2. 매물 상세보기 - 중개인
	public ArrayList<PropertyDTO> propertyDetail(int seqContratorProperty) {

		try {

			String sql = "select * from vuploadProperty where seqContractorProperty = ?";

			// 중개인매물 번호 넘겨주기
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, seqContratorProperty);

			rs = pstat.executeQuery();

			// ResultSet -> ArrayList<DTO>
			ArrayList<PropertyDTO> list = new ArrayList<PropertyDTO>();

			while (rs.next()) {
				// 레코드 1줄 -> DTO 1개
				PropertyDTO dto = new PropertyDTO();

				// 모든매물 seq
				dto.setSeqProperty(rs.getInt("seqProperty_lp"));
				dto.setSeqLessorProperty(rs.getInt("seqLessorProperty"));
				dto.setSeqContractorProperty(rs.getInt("seqContractorProperty"));

				// 중개인 seq
				dto.setSeqContractor(rs.getInt("seqContractor"));

				// 1. 방옵션
				dto.setRoomType(rs.getString("roomType"));
				dto.setSpacing(rs.getString("spacing"));

				System.out.println(dto.getRoomType());
				System.out.println(dto.getSpacing());

				// 2. 계약형태
				dto.setContractTypeDetail(rs.getString("contractType"));
				System.out.println(dto.getContractTypeDetail());

				// 보증금
				String deposit = String.format("%,d" + "원", rs.getInt("deposit"));
				dto.setDeposit(deposit);
				System.out.println(dto.getDeposit());

				// 월세
				String monthlyRent = String.format("%,d" + "원", rs.getInt("monthlyPay"));
				dto.setMonthlyRent(monthlyRent);

				// 매매

				String temp;
				String dealing = "";

				temp = rs.getString("dealing");

				System.out.println(temp);
				if (temp == null) {
					dealing = "";
					dto.setDealing(dealing);
				}

				else {
					dealing = String.format("%,d" + "원", Integer.parseInt(temp));
					dto.setDealing(dealing);
				}

				// System.out.println(dto.getDealing());

				// 기간
				dto.setContractPeriod(rs.getString("contractPeriod") + "개월");

				System.out.println(dto.getContractPeriod());

				// 3. 관리비
				dto.setInternet(rs.getInt("internet"));
				dto.setCable(rs.getInt("cabletv"));

				dto.setCleaning(rs.getInt("cleaning"));
				dto.setWater(rs.getInt("water"));
				dto.setGas(rs.getInt("gas"));
				dto.setElectric(rs.getInt("electric"));

				String serviceCharge = String.format("%,d" + "원", (rs.getInt("serviceCharge")));
				dto.setServiceCharge(serviceCharge);

				System.out.println(dto.getInternet());

				// 4. 건물옵션
				dto.setParking(rs.getInt("parking"));
				dto.setElevator(rs.getInt("elevator"));
				dto.setPet(rs.getInt("pet"));
				dto.setWindow(rs.getInt("window"));
				dto.setFloor(rs.getString("floor"));

				// 5. 방옵션 12개
				dto.setAirconditional(rs.getInt("airconditional"));
				dto.setWasher(rs.getInt("washer"));
				dto.setBed(rs.getInt("bed"));
				dto.setDesk(rs.getInt("desk"));
				dto.setCloset(rs.getInt("closet"));
				dto.setTv(rs.getInt("tv"));
				dto.setShoebox(rs.getInt("shoebox"));
				dto.setRefrigerator(rs.getInt("refrigerator"));
				dto.setStove(rs.getInt("stove"));
				dto.setInduction(rs.getInt("induction"));
				dto.setMicrowave(rs.getInt("microwave"));
				dto.setBidet(rs.getInt("bidet"));

				// 6. 지역
				dto.setLocation(rs.getString("location"));

				// System.out.println(dto.getLocation());
				// 7. 이미지
				dto.setPath(rs.getString("imagePath"));

				// System.out.println(dto.getPath());

				// 8. 입주 가능 or 계약완료
				dto.setAvailable(rs.getString("available"));

				// System.out.println(dto.getAvailable());

				// 9. 중개인 정보
				dto.setCompanyName(rs.getString("companyName"));
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("nameC"));

				// 최종넣기
				dto.setSeqContractor(rs.getInt("seqContractor"));

				// System.out.println(dto.getSeqContractor());
				dto.setOccupancyDate(rs.getString("occupancyDate"));
				// System.out.println(dto.getOccupancyDate());

				dto.setTitle(rs.getString("title"));
				// System.out.println(dto.getTitle());

				dto.setContent(rs.getString("content"));
				// System.out.println(dto.getContent());

				list.add(dto);
			}

			return list;

		} catch (Exception e) {

			System.out.println("list: " + e);
		}

		return null;
	}
	

	// 2. 매물 상세보기 - 임대인
		public ArrayList<PropertyDTO> LessorPropertyDetail(int seqLessorProperty) {

			try {

				String sql = "select * from vmatchingTocontractor where seqLessorProperty = ?";

				//임대인 매물번호 넘겨주기
				pstat = conn.prepareStatement(sql);
				pstat.setInt(1, seqLessorProperty);

				rs = pstat.executeQuery();

				// ResultSet -> ArrayList<DTO>
				ArrayList<PropertyDTO> list = new ArrayList<PropertyDTO>();

				while (rs.next()) {
					// 레코드 1줄 -> DTO 1개
					PropertyDTO dto = new PropertyDTO();

					// 매물 seq
					dto.setSeqLessorProperty(rs.getInt("seqLessorProperty"));

					// 중개인 seq
					dto.setSeqContractor(rs.getInt("seqContractor"));

					// 1. 방옵션
					dto.setRoomType(rs.getString("roomType"));
					dto.setSpacing(rs.getString("spacing"));

					System.out.println(dto.getRoomType());
					System.out.println(dto.getSpacing());

					// 2. 계약형태
					dto.setContractTypeDetail(rs.getString("contractType"));
					System.out.println(dto.getContractTypeDetail());

					// 보증금
					String deposit = String.format("%,d" + "원", rs.getInt("deposit"));
					dto.setDeposit(deposit);
					System.out.println(dto.getDeposit());

					// 월세
					String monthlyRent = String.format("%,d" + "원", rs.getInt("monthlyPay"));
					dto.setMonthlyRent(monthlyRent);

					// 매매

					String temp;
					String dealing = "";

					temp = rs.getString("dealing");

					System.out.println(temp);
					if (temp == null) {
						dealing = "";
						dto.setDealing(dealing);
					}

					else {
						dealing = String.format("%,d" + "원", Integer.parseInt(temp));
						dto.setDealing(dealing);
					}

					// System.out.println(dto.getDealing());

					// 기간
					dto.setContractPeriod(rs.getString("contractPeriod") + "개월");

					System.out.println(dto.getContractPeriod());

					// 3. 관리비
					dto.setInternet(rs.getInt("internet"));
					dto.setCable(rs.getInt("cabletv"));

					System.out.println(dto.getCable());
					
					dto.setCleaning(rs.getInt("cleaning"));
					dto.setWater(rs.getInt("water"));
					dto.setGas(rs.getInt("gas"));
					dto.setElectric(rs.getInt("electric"));
					
					
					String serviceCharge = String.format("%,d" + "원", (rs.getInt("serviceCharge")));
					dto.setServiceCharge(serviceCharge);

					System.out.println(dto.getInternet());

					// 4. 건물옵션
					dto.setParking(rs.getInt("parking"));
					dto.setElevator(rs.getInt("elevator"));
					dto.setPet(rs.getInt("pet"));
					dto.setWindow(rs.getInt("window"));
					dto.setFloor(rs.getString("floor"));

					// 5. 방옵션 12개
					dto.setAirconditional(rs.getInt("airconditional"));
					dto.setWasher(rs.getInt("washer"));
					dto.setBed(rs.getInt("bed"));
					dto.setDesk(rs.getInt("desk"));
					dto.setCloset(rs.getInt("closet"));
					dto.setTv(rs.getInt("tv"));
					dto.setShoebox(rs.getInt("shoebox"));
					dto.setRefrigerator(rs.getInt("refrigerator"));
					dto.setStove(rs.getInt("stove"));
					dto.setInduction(rs.getInt("induction"));
					dto.setMicrowave(rs.getInt("microwave"));
					dto.setBidet(rs.getInt("bidet"));

					// 6. 지역
					dto.setLocation(rs.getString("location"));

					// System.out.println(dto.getLocation());
					// 7. 이미지
					dto.setPath(rs.getString("imagePath"));

					// System.out.println(dto.getPath());

					// 8. 입주 가능 or 계약완료
					//dto.setAvailable(rs.getString("available"));

					// System.out.println(dto.getAvailable());

					// System.out.println(dto.getSeqContractor());
					dto.setOccupancyDate(rs.getString("occupancyDate"));
					// System.out.println(dto.getOccupancyDate());

					dto.setTitle(rs.getString("title"));
					// System.out.println(dto.getTitle());

					dto.setContent(rs.getString("content"));
					// System.out.println(dto.getContent());

					list.add(dto);
				}

				return list;

			} catch (Exception e) {

				System.out.println("list: " + e);
			}

			return null;
		}
	

}
