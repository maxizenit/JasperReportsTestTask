package ru.maxizenit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Data;

@Data
public class Ticket {

  private String origin;

  private String originName;

  private String destination;

  private String destinationName;

  @JsonFormat(pattern = "dd.MM.yy")
  private LocalDate departureDate;

  @JsonFormat(pattern = "H:mm")
  private LocalTime departureTime;

  @JsonFormat(pattern = "dd.MM.yy")
  private LocalDate arrivalDate;

  @JsonFormat(pattern = "H:mm")
  private LocalTime arrivalTime;

  private String carrier;

  private Integer stops;

  private Integer price;

  public LocalDateTime getDepartureDateTime() {
    return LocalDateTime.of(departureDate, departureTime);
  }

  public LocalDateTime getArrivalDateTime() {
    return LocalDateTime.of(arrivalDate, arrivalTime);
  }

  public Duration getFlightTime() {
    return Duration.between(getDepartureDateTime(), getArrivalDateTime());
  }
}
