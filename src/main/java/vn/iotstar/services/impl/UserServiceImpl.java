package vn.iotstar.services.impl;

import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService{

	//Lay toan bo ham trong tang Dao cua user
	UserDao userDap = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
	    UserModel user = this.FindByUserName(username);
	    if (user == null) {
	        System.out.println(">>> Không tìm thấy user: " + username);
	        return null;
	    }
	    System.out.println(">>> Input password = '" + password + "'");
	    System.out.println(">>> DB password    = '" + user.getPassword() + "'");
	    if (password.equals(user.getPassword())) {
	        return user;
	    } else {
	        System.out.println(">>> Không khớp mật khẩu!");
	    }
	    return null;
	}


	@Override
	public UserModel FindByUserName(String username) {
		return userDap.findByUserName(username);
	}

}
