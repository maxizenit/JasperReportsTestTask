package ru.maxizenit;

import java.io.IOException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import ru.maxizenit.model.Ticket;
import ru.maxizenit.report.TicketsReportGenerator;
import ru.maxizenit.util.FileUtils;
import ru.maxizenit.util.TicketsUtils;

public class Main {

  public static void main(String[] args) throws IOException, JRException {
    List<Ticket> tickets = TicketsUtils.getTicketsFromJsonFile(FileUtils.getTicketsFile());
    TicketsReportGenerator.generateReport(tickets);
  }
}
