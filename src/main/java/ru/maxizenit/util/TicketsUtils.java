package ru.maxizenit.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import ru.maxizenit.model.Ticket;

public class TicketsUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();

  public static List<Ticket> getTicketsFromJsonFile(File file) throws IOException {
    return OBJECT_MAPPER.readValue(file, new TypeReference<>() {});
  }

  public static String convertFlightTimeToHoursAndMinutes(Duration flightTime) {
    long hours = flightTime.toHours();
    long minutes = flightTime.toMinutes() % 60;

    return String.format("%d:%d", hours, minutes);
  }

  public static Integer getMaxPriceByTickets(List<Ticket> tickets) {
    return tickets.stream().mapToInt(Ticket::getPrice).max().getAsInt();
  }

  public static String getMaxFlightTimeByTickets(List<Ticket> tickets) {
    return convertFlightTimeToHoursAndMinutes(
        tickets.stream().max(Comparator.comparing(Ticket::getFlightTime)).get().getFlightTime());
  }
}
