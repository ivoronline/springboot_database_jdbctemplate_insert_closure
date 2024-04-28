package com.ivoronline.springboot_database_jdbctemplate_insert_closure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.PreparedStatement;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // HELLO
  //=========================================================================================================
  @ResponseBody
  @GetMapping("/hello")
  public int hello() {
    int    insertedRecords = insert("Jill", 40);
    return insertedRecords;
  }

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
