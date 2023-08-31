package ru.maxizenit.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import ru.maxizenit.model.Ticket;
import ru.maxizenit.util.FileUtils;
import ru.maxizenit.util.TicketsUtils;

public class TicketsReportGenerator {

  private static final String REPORT_FILE_NAME = "report.html";

  private static TicketDataBean convertTicketToDataBean(Ticket ticket) {
    TicketDataBean ticketDataBean = new TicketDataBean();

    ticketDataBean.setOrigin(ticket.getOriginName());
    ticketDataBean.setDestination(ticket.getDestinationName());
    ticketDataBean.setCarrier(ticket.getCarrier());
    ticketDataBean.setPrice(ticket.getPrice());
    ticketDataBean.setFlightTime(
        TicketsUtils.convertFlightTimeToHoursAndMinutes(ticket.getFlightTime()));

    return ticketDataBean;
  }

  public static void generateReport(List<Ticket> tickets) throws JRException {
    JRBeanCollectionDataSource dataSource =
        new JRBeanCollectionDataSource(
            tickets.stream().map(TicketsReportGenerator::convertTicketToDataBean).toList());
    Map<String, Object> parameters = new HashMap<>();

    parameters.put("MAX_PRICE", TicketsUtils.getMaxPriceByTickets(tickets));
    parameters.put("MAX_FLIGHT_TIME", TicketsUtils.getMaxFlightTimeByTickets(tickets));

    JasperDesign jasperDesign = JRXmlLoader.load(FileUtils.getReportPatternFile());
    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

    JasperExportManager.exportReportToHtmlFile(jasperPrint, REPORT_FILE_NAME);
  }
}
