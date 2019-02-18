package ua.kharkov.khpi.belozerov.db;

import ua.kharkov.khpi.belozerov.db.entity.User;
import ua.kharkov.khpi.belozerov.db.entity.UserA;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

/**
 * Role entity.
 * 
 * @author D.Kolesnikov
 * 
 */

public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public static Role getRole(UserFull user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	
	
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
