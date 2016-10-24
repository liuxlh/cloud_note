package org.tarena.note.dao;

import org.tarena.note.entity.User;

public interface UserMapperDao {
	public User findByName(String name);
	public int save(User user);

}
