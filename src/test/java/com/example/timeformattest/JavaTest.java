package com.example.timeformattest;

import com.example.timeformattest.dto.TimeTestZoned;
import com.example.timeformattest.dto.TimeTestLocal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;

public class JavaTest {

    private static ModelMapper modelMapper;

    @BeforeAll
    static void setting(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        modelMapper.addConverter((context) -> ZonedDateTime.of(context.getSource(), ZoneOffset.UTC), LocalDateTime.class, ZonedDateTime.class);
        modelMapper.addConverter((context) -> context.getSource().toLocalDateTime(), ZonedDateTime.class, LocalDateTime.class);
    }


    @Test
    void dateTest(){
        TimeTestLocal before1 = TimeTestLocal.builder()
                .name("WTF")
                .dateTime(LocalDateTime.now())
                .build();

        TimeTestZoned after1 = modelMapper.map(before1, TimeTestZoned.class);
        System.out.println("after1 = " + after1);

        assertThat(after1.getName()).isNotNull();
        assertThat(after1.getDateTime()).isEqualTo(ZonedDateTime.of(before1.getDateTime(), ZoneOffset.UTC));

        //

        TimeTestZoned before2 = TimeTestZoned.builder()
                .name("FUCK")
                .dateTime(ZonedDateTime.now())
                .build();

        TimeTestLocal after2 = modelMapper.map(before2, TimeTestLocal.class);
        System.out.println(after2);

        assertThat(after2.getName()).isNotNull();
        assertThat(after2.getDateTime()).isEqualTo(before2.getDateTime().toLocalDateTime());

        System.out.println("========stop==========");


    }
}
