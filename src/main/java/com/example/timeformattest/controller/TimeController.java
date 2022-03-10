package com.example.timeformattest.controller;

import com.example.timeformattest.dto.TimeTestZoned;
import com.example.timeformattest.dto.TimeTestLocal;
import com.example.timeformattest.dto.TimeTestDto;
import com.example.timeformattest.entity.Calendar;
import com.example.timeformattest.repository.CalendarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TimeController {

    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    private final InitData initData;
    private final CalendarRepository calendarRepository;


    @PostMapping("/time")
    public TimeTestDto timeTest(@RequestBody TimeTestDto dto){
        Date date = dto.getDate();
        LocalTime localTime = dto.getLocalTime();
        LocalDate localDate = dto.getLocalDate();
        LocalDateTime localDateTime = dto.getLocalDateTime();

        ZonedDateTime zonedDateTime = dto.getZonedDateTime();

        try {
            String s = objectMapper.writeValueAsString(dto);
            System.out.println("s = " + s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("===================stop====================");

        return dto;
    }

    @GetMapping("/time")
    public TimeTestDto timeTest2(){
        TimeTestDto build = TimeTestDto.builder()
                .date(new Date())
                .localTime(LocalTime.now())
                .localDate(LocalDate.now())
                .localDateTime(LocalDateTime.now())
                .zonedDateTime(ZonedDateTime.now())
                .build();
        return build;
    }

    @GetMapping("/convert")
    public TimeTestZoned timeConvert(){
        TimeTestLocal before = TimeTestLocal.builder()
                .name("WTF")
                .dateTime(LocalDateTime.now())
                .build();

        TimeTestZoned after = modelMapper.map(before, TimeTestZoned.class);
        return after;
    }

    @GetMapping("/param")
    public List<Calendar> paramTest(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        return calendarRepository.findByStartDateBetween(start, end);
    }

    @GetMapping("/param2")
    public List<Calendar> paramTest2(ZonedDateTime start, ZonedDateTime end){
        return calendarRepository.findByStartDateBetween(start, end);
    }

    @PostConstruct
    public void init(){
        initData.initData();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    public static class InitData {
        private final CalendarRepository calendarRepository;

        public void initData(){
            calendarRepository.save(Calendar.builder().title("test1").startDate(ZonedDateTime.now()).build());
        }
    }

}
