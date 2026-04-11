package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.DashboardStatsDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.OrderStatus;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderRepository orderRepository;
    private final ArtistRepository artistRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public DashboardStatsDTO getAdminDashboardStats() {
        // Ingresos de pedidos completados
        BigDecimal totalRevenue = orderRepository.findAll().stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .map(Order::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long artistsCount = artistRepository.count();
        long ticketsCount = ticketRepository.count();
        long usersCount = userRepository.count();

        // Cálculo de capacidad basado en tickets vendidos
        double capacity = (ticketsCount / 2000.0) * 100;

        return DashboardStatsDTO.builder()
                .totalRevenue(totalRevenue)
                .totalArtists(artistsCount)
                .totalTicketsSold(ticketsCount)
                .totalActiveUsers(usersCount)
                .ticketCapacityPercentage(Math.min(capacity, 100.0))
                .salesTrend(new ArrayList<>()) // Lo rellenaremos en el siguiente paso de gráficas
                .build();
    }
}