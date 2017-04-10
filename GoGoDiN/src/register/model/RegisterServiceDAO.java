package register.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;
import com.shop.Model.ShopVO;

public class RegisterServiceDAO implements RegisterService_interface {
	private List<MemberListVO> memberList;
	private List<ShopVO> shopList;
	public RegisterServiceDAO() throws SQLException {
		loginService ls = new loginService();
		memberList = ls.getMemberList();
		shopList = ls.getShopList();
	}
	@Override
	synchronized public boolean idExists(String id) throws IOException, SQLException {
		for (MemberListVO me : memberList) {
			if (me.getMemAccount().equals(id.trim())) {
				return true;
			}		
		}
		for(ShopVO sb : shopList){
			if(sb.getShopAccount().equals(id.trim())){
				return true;
			}
		}
		return false;
	}

}
