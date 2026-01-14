package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingOrderDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderResponseDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketOrderDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.OrderRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final CampingTypeRepository campingTypeRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Page<Order> getAllOrders(String searchTerm, Pageable pageable) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return orderRepository.searchOrders(searchTerm, pageable);
        }
        return orderRepository.findAll(pageable);
    }

    public List<Order> getOrdersByUser(String email) {
        return orderRepository.findByUserEmailOrderByOrderDateDesc(email);
    }

    public BigDecimal calculateTotal(OrderRequestDTO request) {
        BigDecimal total = BigDecimal.ZERO;

        if (request.getTickets() != null) {
            for (var t : request.getTickets()) {
                total = total.add(ticketTypeRepository.findById(t.getTicketTypeId())
                        .map(TicketType::getPrice).orElse(BigDecimal.ZERO));
            }
        }

        if (request.getCampings() != null) {
            for (var c : request.getCampings()) {
                total = total.add(campingTypeRepository.findById(c.getCampingTypeId())
                        .map(CampingType::getPrice).orElse(BigDecimal.ZERO));
            }
        }
        return total;
    }

    @Transactional
    public Order executeOrder(OrderRequestDTO request, User user) {
        logger.info("Iniciando creación de pedido para el usuario: {}", user.getEmail());
        boolean hasTickets = request.getTickets() != null && !request.getTickets().isEmpty();
        boolean hasCampings = request.getCampings() != null && !request.getCampings().isEmpty();

        if (!hasTickets && !hasCampings) {
            throw new AppException("msg.validation.atLeastOne");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        BigDecimal totalPrice = BigDecimal.ZERO;

        if (hasTickets) {
            for (TicketOrderDTO tDto : request.getTickets()) {
                TicketType type = ticketTypeRepository.findById(tDto.getTicketTypeId())
                        .orElseThrow(() -> new AppException("msg.error.ticketTypeNotFound"));

                if (type.getStockAvailable() <= 0) throw new AppException("msg.error.noStock", type.getName());

                Ticket ticket = new Ticket();
                ticket.setFirstName(tDto.getFirstName());
                ticket.setLastName(tDto.getLastName());
                ticket.setDocumentType(tDto.getDocumentType());
                ticket.setDocumentNumber(tDto.getDocumentNumber());
                ticket.setBirthDate(tDto.getBirthDate());
                ticket.setTicketType(type);
                ticket.setQrCode(UUID.randomUUID().toString());
                ticket.setOrder(order);

                order.getTickets().add(ticket);
                totalPrice = totalPrice.add(type.getPrice());

                type.setStockAvailable(type.getStockAvailable() - 1);
                ticketTypeRepository.save(type);
            }
        }

        if (hasCampings) {
            for (CampingOrderDTO cDto : request.getCampings()) {
                CampingType type = campingTypeRepository.findById(cDto.getCampingTypeId())
                        .orElseThrow(() -> new AppException("msg.error.campingTypeNotFound"));

                if (type.getStockAvailable() <= 0) {
                    throw new AppException("msg.error.noStock", type.getName());
                }

                Camping camping = new Camping();
                camping.setFirstName(cDto.getFirstName());
                camping.setLastName(cDto.getLastName());
                camping.setDocumentType(cDto.getDocumentType());
                camping.setDocumentNumber(cDto.getDocumentNumber());
                camping.setBirthDate(cDto.getBirthDate());
                camping.setCampingType(type);
                camping.setQrCode(UUID.randomUUID().toString());
                camping.setOrder(order);

                order.getCampings().add(camping);
                totalPrice = totalPrice.add(type.getPrice());

                type.setStockAvailable(type.getStockAvailable() - 1);
                campingTypeRepository.save(type);
            }
        }

        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    /**
     * Método para confirmar el pago.
     * Al ser @Transactional, asegura que el cambio de estado se guarde inmediatamente.
     */
    @Transactional
    public Order confirmPayment(Long orderId) {
        logger.info("Confirmando pago para el pedido #{}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + orderId));
        order.setStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    public List<OrderResponseDTO> getOrdersByUserDTO(String email) {
        List<Order> orders = orderRepository.findByUserEmailOrderByOrderDateDesc(email);
        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private OrderResponseDTO convertToDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());

        dto.setTickets(order.getTickets().stream().map(t -> {
            TicketOrderDTO tDto = new TicketOrderDTO();
            tDto.setFirstName(t.getFirstName());
            tDto.setLastName(t.getLastName());
            tDto.setDocumentType(t.getDocumentType());
            tDto.setDocumentNumber(t.getDocumentNumber());
            tDto.setBirthDate(t.getBirthDate());
            tDto.setTicketTypeId(t.getTicketType().getId());
            return tDto;
        }).toList());

        dto.setCampings(order.getCampings().stream().map(c -> {
            CampingOrderDTO cDto = new CampingOrderDTO();
            cDto.setFirstName(c.getFirstName());
            cDto.setLastName(c.getLastName());
            cDto.setDocumentType(c.getDocumentType());
            cDto.setDocumentNumber(c.getDocumentNumber());
            cDto.setBirthDate(c.getBirthDate());
            cDto.setCampingTypeId(c.getCampingType().getId());
            return cDto;
        }).toList());

        return dto;
    }
}