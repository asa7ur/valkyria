package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderResponseDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.CampingMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.OrderMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.TicketMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.OrderRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final CampingTypeRepository campingTypeRepository;
    private final OrderMapper orderMapper;
    private final TicketMapper ticketMapper;
    private final CampingMapper campingMapper;

    /**
     * Obtiene todos los pedidos con paginación y búsqueda (Panel de Control).
     */
    public Page<OrderResponseDTO> getAllOrders(String searchTerm, Pageable pageable) {
        Page<Order> orderPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? orderRepository.searchOrders(searchTerm, pageable)
                : orderRepository.findAll(pageable);
        return orderPage.map(orderMapper::toResponseDTO);
    }

    /**
     * Devuelve el historial de pedidos de un usuario específico.
     */
    public List<OrderResponseDTO> getOrdersByUser(String email) {
        List<Order> orders = orderRepository.findByUserEmailOrderByOrderDateDesc(email);
        return orderMapper.toResponseDTOList(orders);
    }

    /**
     * Busca un pedido por ID y lo devuelve como entidad (uso interno).
     */
    public Order getOrderEntityById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.error.order-not-found", id));
    }

    /**
     * PROCESO DE COMPRA PRINCIPAL
     * Valida stock, asocia al usuario, calcula precios y genera QRs.
     */
    @Transactional
    public Order executeOrder(OrderRequestDTO request, User user) {
        logger.info("Iniciando procesamiento de pedido para: {}", user.getEmail());

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal totalPrice = BigDecimal.ZERO;

        // 1. Procesar Tickets (Entradas)
        if (request.getTickets() != null && !request.getTickets().isEmpty()) {
            for (TicketCreateDTO tDto : request.getTickets()) {
                TicketType type = ticketTypeRepository.findById(tDto.getTicketTypeId())
                        .orElseThrow(() -> new AppException("msg.error.ticket-type-not-found"));

                if (type.getStockAvailable() <= 0) {
                    throw new AppException("msg.error.no-stock", type.getName());
                }

                // Generar QR y mapear usando TicketMapper
                String qr = "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                Ticket ticket = ticketMapper.toEntityFromOrder(tDto, type, order, qr);

                order.getTickets().add(ticket);
                totalPrice = totalPrice.add(type.getPrice());

                // Actualizar stock
                type.setStockAvailable(type.getStockAvailable() - 1);
                ticketTypeRepository.save(type);
            }
        }

        // 2. Procesar Campings (Reservas)
        if (request.getCampings() != null && !request.getCampings().isEmpty()) {
            for (CampingCreateDTO cDto : request.getCampings()) {
                CampingType type = campingTypeRepository.findById(cDto.getCampingTypeId())
                        .orElseThrow(() -> new AppException("msg.error.camping-type-not-found"));

                if (type.getStockAvailable() <= 0) {
                    throw new AppException("msg.error.no-stock", type.getName());
                }

                // Generar QR y mapear usando CampingMapper
                String qr = "CMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                Camping camping = campingMapper.toEntityFromOrder(cDto, type, order, qr);

                order.getCampings().add(camping);
                totalPrice = totalPrice.add(type.getPrice());

                // Actualizar stock
                type.setStockAvailable(type.getStockAvailable() - 1);
                campingTypeRepository.save(type);
            }
        }

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        logger.info("Pedido #{} creado con éxito. Total: {} €", savedOrder.getId(), savedOrder.getTotalPrice());

        return savedOrder;
    }

    /**
     * Confirma el pago de un pedido tras recibir la notificación de Stripe.
     */
    @Transactional
    public Order confirmPayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException("msg.error.order-not-found", orderId));

        order.setStatus(OrderStatus.PAID);
        logger.info("Pedido #{} marcado como PAGADO.", orderId);

        return orderRepository.save(order);
    }
}