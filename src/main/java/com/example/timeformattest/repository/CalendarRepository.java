package com.example.timeformattest.repository;

import com.example.timeformattest.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findByStartDateBetween(ZonedDateTime start, ZonedDateTime end);
}
