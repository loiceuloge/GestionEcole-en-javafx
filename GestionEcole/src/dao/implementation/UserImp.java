package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBConection;
import dao.Interfaces.IUser;
import dao.models.User;

public class UserImp implements IUser {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection conn = DBConection.conDB();

    @Override
    public User getUser(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

}
