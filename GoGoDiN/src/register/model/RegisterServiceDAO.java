package register.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;
import com.shop.Model.ShopVO;

public class RegisterServiceDAO implements RegisterService_interface {
	private static Map<String, MemberListVO> membermap = new TreeMap<>();
	private static Map<String, ShopVO> shopmap = new TreeMap<>();
	public RegisterServiceDAO() throws SQLException {
	loginService ls = new loginService();
		if (membermap.isEmpty()) {
			if(shopmap.isEmpty()) {
				shopmap = ls.getShopList();
			}
			membermap = ls.getMemberList();
		}
	}
	@Override
	synchronized public boolean idExists(String id) throws IOException, SQLException {
			if (membermap.containsKey(id.trim().toLowerCase()) 
					|| shopmap.containsKey(id.trim().toLowerCase())) {
				return true;
			}		
		return false;
	}
}
