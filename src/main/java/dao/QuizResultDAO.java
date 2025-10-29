package dao;

import java.sql.*;
import java.util.*;
import Model.Quiz;
import Model.QuizResult;
import util.DBUtil;

public class QuizResultDAO {

    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    public List<QuizResult> getRecentResults(User user, int limit) {
        List<QuizResult> results = new ArrayList<>();
        String sql = "SELECT r.id, r.quiz_id, r.score, r.attempt_date, q.title " +
                     "FROM quiz_results r " +
                     "JOIN quiz q ON r.quiz_id = q.id " +
                     "WHERE r.user_id = ? " +
                     "ORDER BY r.attempt_date DESC LIMIT ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, user.getId());
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("quiz_id"));
                quiz.setTitle(rs.getString("title"));

                QuizResult qr = new QuizResult();
                qr.setId(rs.getInt("id"));
                qr.setUser(user);
                qr.setQuiz(quiz);
                qr.setScore(rs.getInt("score"));
                qr.setAttemptDate(rs.getTimestamp("attempt_date"));

                results.add(qr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
