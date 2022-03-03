package com.example.timeformattest.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Configuration
public class BeanConfig {

//    @Bean
//    public ObjectMapper registryObjectMapper(){
//        return new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .registerModule(new JavaTimeModule())
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    }

    @Bean
    public ModelMapper modelMapper(){

//        Converter<LocalDateTime, ZonedDateTime> localDateTimeZonedDateTimeConverter = new Converter<>() {
//            @Override
//            public ZonedDateTime convert(MappingContext<LocalDateTime, ZonedDateTime> context) {
//                return ZonedDateTime.of(context.getSource(), ZoneOffset.UTC);
//            }
//        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        modelMapper.addConverter((context) -> ZonedDateTime.of(context.getSource(), ZoneOffset.UTC), LocalDateTime.class, ZonedDateTime.class);
        modelMapper.addConverter((context) -> context.getSource().toLocalDateTime(), ZonedDateTime.class, LocalDateTime.class);

        return modelMapper;
    }
}
