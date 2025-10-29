package dao;

import java.sql.*;
import java.util.*;
import Model.Quiz;
import util.DBUtil;

public class QuizDAO {

    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection(); // your existing DB connection utility
    }

    // Since Quiz.java uses creatorId (Long), we just set that instead of User object
    public List<Quiz> findByCreator(User creator) {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quiz WHERE creator_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, creator.getId()); 
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getLong("id"));
                quiz.setTitle(rs.getString("title"));
                quiz.setDescription(rs.getString("description"));
                quiz.setTimeLimit(rs.getInt("time_limit"));
                quiz.setCreatorId(rs.getLong("creator_id")); 
                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }
}
