package com.ivoronline.springboot_database_jdbctemplate_insert_closure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // INSERT
  //=========================================================================================================
  public int insert(String name, Integer age) {

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(
      connection -> {
        String            sql               = "INSERT INTO PERSON (NAME, AGE) VALUES ( ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] { "ID" });
                          preparedStatement.setString(1, name);
                          preparedStatement.setLong  (2, age );
        return preparedStatement;
      }
      , keyHolder
    );

    return keyHolder.getKey().intValue();

  }

}
