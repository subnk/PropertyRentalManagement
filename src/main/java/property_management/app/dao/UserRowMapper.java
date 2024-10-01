package property_management.app.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import property_management.app.entities.User;
import property_management.utility.ByteArrayMultipartFile;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();

		user.setUserId(rs.getInt("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmailId(rs.getString("email_id"));
		user.setMobileNo(rs.getString("mobile_no"));
		user.setDateOfBirth(rs.getDate("date_of_birth"));
		user.setUsername(rs.getString("username"));
		user.setPasswordSalt(rs.getString("passwordSalt"));
		user.setPasswordHash(rs.getString("passwordHash"));

		Blob profileImageBlob = rs.getBlob("profile_image");

		// Convert blob to MultipartFile
		byte[] imageBytes = profileImageBlob.getBytes(1, (int) profileImageBlob.length());
		MultipartFile profileImage = new ByteArrayMultipartFile(imageBytes, "profileImage.jpg", "image/jpeg");

		user.setProfileImage(profileImage);

		return user;

	}

}
