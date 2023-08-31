package ru.maxizenit.report;

import lombok.Data;

@Data
public class TicketDataBean {

  private String origin;

  private String destination;

  private String carrier;

  private Integer price;

  private String flightTime;
}
