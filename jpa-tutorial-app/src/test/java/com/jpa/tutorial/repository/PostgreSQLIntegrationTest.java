package com.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("postgres")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//To tell Spring boot not to do the AutoConfiguration of Database to H2
public class PostgreSQLIntegrationTest {
    @Autowired
    CourseRepository repository;

    @Test
    void testPostgresql(){
        long countBefore = repository.count();
        assertThat(countBefore).isEqualTo(4);
    }

}
