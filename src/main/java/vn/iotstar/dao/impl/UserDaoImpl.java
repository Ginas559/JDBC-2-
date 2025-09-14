package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnection implements UserDao{
	
	public Connection conn = null;
	public PreparedStatement ps =  null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		
	    String sql = "SELECT * FROM users";
	    
	    List<UserModel> list = new ArrayList<>(); // Tạo list để chứa dữ liệu

	    try {
	        conn = super.getConnection(); // Kết nối database
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) { // Duyệt từng dòng kết quả
	            list.add(
	            		new UserModel(
			                rs.getInt("id"),
			                rs.getString("username"),
			                rs.getString("password"),
			                rs.getString("email"),
			                rs.getString("fullname"),
			                rs.getString("images")
	            ));
	        }
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace(); // In lỗi ra nếu có
	    }
	    return null;
	}


	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(UserModel user) {
	    String sql = "INSERT INTO users(id, username, email, password, images, fullname) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";
	    try {
	        conn = super.getConnection(); // Kết nối database
	        ps = conn.prepareStatement(sql);      // Ném câu SQL vào cho thực thi

	        ps.setInt(1, user.getId());
	        ps.setString(2, user.getUsername());
	        ps.setString(3, user.getEmail());
	        ps.setString(4, user.getPassword());
	        ps.setString(5, user.getImages());
	        ps.setString(6, user.getFullname());

	        ps.executeUpdate(); // Thực thi câu lệnh insert
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		
		userDao.insert(new UserModel(2, "abc2", "abc2@gmail.com", "123", "abcdef", "abc.jpg"));
		
		List<UserModel> list = userDao.findAll();
		
		for (UserModel user : list) {
			System.out.println(user);
		}
	}

}
