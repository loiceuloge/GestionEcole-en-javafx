package dao.Interfaces;

import dao.models.User;

public interface IUser {
    public User getUser(String email,String password);
}
