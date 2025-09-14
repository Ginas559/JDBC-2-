package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnection implements UserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
	    String sql = "SELECT * FROM users";
	    List<UserModel> list = new ArrayList<>();
	    try {
	        conn = new DBConnection().getConnection();
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(new UserModel(
	                rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("email"),
	                rs.getString("password"),
	                rs.getString("fullname"),
	                rs.getString("images"),
	                rs.getString("phone"),
	                rs.getInt("roleid"),
	                rs.getDate("createDate")
	            ));
	        }

	        return list; // ✅ trả về sau khi duyệt hết result set
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return new ArrayList<>(); // ✅ tránh null
	}


	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images")); // ✅ sửa avatar -> images
				user.setRoledid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	@Override
	public void insert(UserModel user) {
	    String sql = "INSERT INTO users(username, email, password, fullname, images, roleid, phone, createDate) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try {
	        conn = new DBConnection().getConnection(); // ✅ thiếu dòng này
	        ps = conn.prepareStatement(sql);

	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getFullname());
	        ps.setString(5, user.getImages()); 
	        ps.setInt(6, user.getRoledid());
	        ps.setString(7, user.getPhone());
	        ps.setDate(8, new java.sql.Date(user.getCreateDate().getTime()));

	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();

		// Thêm user mới
		UserModel newUser = new UserModel();
		newUser.setUsername("abc3");
		newUser.setEmail("abc2@gmail.com");
		newUser.setPassword("123");
		newUser.setFullname("Nguyen Van B");
		newUser.setImages("abc2.jpg");
		newUser.setRoledid(1);
		newUser.setPhone("0123456789");
		newUser.setCreateDate(new java.sql.Date(System.currentTimeMillis()));

		userDao.insert(newUser);

		// In ra tất cả user để kiểm tra
		List<UserModel> list = userDao.findAll();
		for (UserModel user : list) {
			System.out.println(user);
		}
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images")); // ✅ sửa avatar -> images
				user.setRoledid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

}
