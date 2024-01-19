package es.deusto.ingenieria.sd.auctions.server.services;


import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;
import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.gateway.GatewayFactory;

//TODO: Implement Singleton Pattern
public class LoginAppService {
	
	private static LoginAppService instance;
		
	public boolean login(String email, String password, String account) {
		//TODO: Get User using DAO and check 
		User user = UserDAO.getInstance().find(email);		
		if (user != null) {
			IGateway g = GatewayFactory.createGateway(account);
			return g.passwordValidation(email,password);
		} else {
			return false;
		}
		
	}
	
	public boolean register(String email, String account) {		
		IGateway g = GatewayFactory.createGateway(account);
		return g.userValidation(email);
	}
	
	public static LoginAppService getInstance() {

		if(instance == null) {
			instance = new LoginAppService();
		}
		return instance;
	}
}