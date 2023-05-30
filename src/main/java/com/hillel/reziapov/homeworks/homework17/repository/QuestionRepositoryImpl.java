package com.hillel.reziapov.homeworks.homework17.repository;

import com.hillel.reziapov.homeworks.homework17.connectionSingleton.ConnectionSingleton;
import com.hillel.reziapov.homeworks.homework17.questionarium.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRepositoryImpl implements QuestionRepository {
    private static final String selectAll = "SELECT * FROM question WHERE id = ?";

    private static final String SAVE =
            """
                    INSERT INTO public."question " (text,topic_id)
                    VALUES (?, ?)
                    """;
    private static final String REMOVE =
            """
                    DELETE FROM public."question "
                    WHERE id = ?
                    """;
    private static final String UPDATE =
            """
                    UPDATE public."question "
                    SET text = ?, topic_id = ?
                    WHERE id = ?
                    """;
    private static final String GET =
            """
                    SELECT q.id AS question_id, text, topic_id AS topic_id, name
                    FROM public."question " AS q
                    JOIN topic AS t ON q.topic_id = t.id
                    WHERE t.id = %s
                                        
                    """;
    private final Connection connection = ConnectionSingleton.getConnection();


    @Override
    public boolean save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setString(1, question.getText());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t save question " + question, e);
        }
    }

    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Question.builder()
                    .text(resultSet.getString("name"))
                    .id(resultSet.getInt("id"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get question " + id, e);
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t remove question " + id, e);
        }
    }

    @Override
    public int update(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setInt(2, question.getTopic_id());
            preparedStatement.setInt(3, question.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t update question " + question, e);
        }
    }
}
