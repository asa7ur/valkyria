package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {
    private BigDecimal totalRevenue;
    private long totalArtists;
    private long totalTicketsSold;
    private long totalActiveUsers;
    private double ticketCapacityPercentage;

    private List<RevenuePoint> salesTrend;
    private List<SalesBreakdownPoint> salesBreakdown;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RevenuePoint {
        private String date;
        private BigDecimal amount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SalesBreakdownPoint {
        private String label;
        private long count;
    }
}