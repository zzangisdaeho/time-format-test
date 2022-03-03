package com.example.timeformattest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeTestDto {

    private Date date;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;

    private ZonedDateTime zonedDateTime;

}
