package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.DashboardStatsDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.OrderStatus;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderRepository orderRepository;
    private final ArtistRepository artistRepository;
    private final TicketRepository ticketRepository;
    private final CampingRepository campingRepository;
    private final UserRepository userRepository;

    public DashboardStatsDTO getAdminDashboardStats() {
        // Ingresos de pedidos completados (filtrando nulls de forma segura)
        BigDecimal totalRevenue = orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .map(Order::getTotalPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long artistsCount = artistRepository.count();
        long ticketsCount = ticketRepository.count();
        long usersCount = userRepository.count();

        // Cálculo de capacidad basado en tickets vendidos
        double capacity = (ticketsCount / 2000.0) * 100;

        List<DashboardStatsDTO.RevenuePoint> salesTrend = orderRepository.findDailyRevenue().stream()
                .map(row -> new DashboardStatsDTO.RevenuePoint(
                        row[0].toString(),
                        (BigDecimal) row[1]
                ))
                .collect(Collectors.toList());

        List<DashboardStatsDTO.SalesBreakdownPoint> salesBreakdown = new java.util.ArrayList<>();
        ticketRepository.countByType().stream()
                .map(row -> new DashboardStatsDTO.SalesBreakdownPoint(row[0].toString(), (Long) row[1]))
                .forEach(salesBreakdown::add);
        campingRepository.countByType().stream()
                .map(row -> new DashboardStatsDTO.SalesBreakdownPoint(row[0].toString(), (Long) row[1]))
                .forEach(salesBreakdown::add);

        return DashboardStatsDTO.builder()
                .totalRevenue(totalRevenue)
                .totalArtists(artistsCount)
                .totalTicketsSold(ticketsCount)
                .totalActiveUsers(usersCount)
                .ticketCapacityPercentage(Math.min(capacity, 100.0))
                .salesTrend(salesTrend)
                .salesBreakdown(salesBreakdown)
                .build();
    }
}