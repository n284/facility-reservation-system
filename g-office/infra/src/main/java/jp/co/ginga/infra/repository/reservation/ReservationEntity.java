package jp.co.ginga.infra.repository.reservation;

import java.sql.Timestamp;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
	private int reservationId;
	private Timestamp startTime;
	private Timestamp endTime;
	private FacilityEntity facilityEntity;
	private Timestamp insertDate;
	private UserEntity userEntity;
	private Timestamp updateDate;
	private UserEntity updateUserEntity;

	public String toString() {
		String str =
				   this.reservationId
				+ "," + (this.startTime == null ? "null" : this.startTime.toString())
				+ "," + (this.endTime == null ? "null" : this.endTime.toString())
				+ "," + (this.facilityEntity == null ? "null" : this.facilityEntity.toString())
				+ "," + (this.insertDate == null ? "null" : this.insertDate.toString())
				+ "," + (this.userEntity == null ? "null" : this.userEntity.toString())
				+ "," + (this.updateDate == null ? "null" : this.updateDate.toString())
				+ "," + (this.updateUserEntity == null ? "null" : this.updateUserEntity.toString())
				;
		return str;
	}
}
