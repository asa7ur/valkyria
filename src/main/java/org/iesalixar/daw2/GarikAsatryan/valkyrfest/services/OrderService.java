package org.iesalixar.daw2.GarikAsatryan.valkyrfest.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.CampingRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.OrderRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.TicketRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.*;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.CampingTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.OrderRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.TicketTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final CampingTypeRepository campingTypeRepository;

    /**
     * Crea un pedido completo procesando entradas y camping.
     */
    @Transactional
    public Order createOrder(OrderRequestDTO request) {
        // Validar que el usuario existe
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("msg.error.userNotFound"));

        // Crear la instancia del pedido
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Procesar los tickets
        for (TicketRequestDTO ticketDto : request.getTickets()) {
            TicketType type = ticketTypeRepository.findById(ticketDto.getTicketTypeId())
                    .orElseThrow(() -> new AppException("msg.error.ticketTypeNotFound"));

            if (type.getStockAvailable() <= 0) {
                throw new AppException("msg.error.noStock", type.getName());
            }

            // Actualizar el stock
            type.setStockAvailable(type.getStockAvailable() - 1);
            ticketTypeRepository.save(type);

            // Crear entidad Ticket
            Ticket ticket = new Ticket();
            ticket.setFirstName(ticketDto.getFirstName());
            ticket.setLastName(ticketDto.getLastName());
            ticket.setDocumentType(ticketDto.getDocumentType());
            ticket.setDocumentNumber(ticketDto.getDocumentNumber());
            ticket.setBirthDate(ticketDto.getBirthDate());
            ticket.setQrCode(UUID.randomUUID().toString()); // Genero un código único
            ticket.setTicketType(type);
            ticket.setOrder(order);
            ticket.setStatus(TicketStatus.ACTIVE);

            // Añadir a la lista del ppedido y sumar al precio total
            order.getTickets().add(ticket);
            totalPrice = totalPrice.add(type.getPrice());
        }

        if (request.getCampings() != null && !request.getCampings().isEmpty()) {
            // Procesar los campings
            for (CampingRequestDTO campingDto : request.getCampings()) {
                CampingType type = campingTypeRepository.findById(campingDto.getCampingTypeId())
                        .orElseThrow(() -> new AppException("msg.error.campingTypeNotFound"));

                if (type.getStockAvailable() <= 0) {
                    throw new AppException("msg.error.noStock", type.getName());
                }

                // Actualizar el stock
                type.setStockAvailable(type.getStockAvailable() - 1);
                campingTypeRepository.save(type);

                // Crear entidad Camping
                Camping camping = new Camping();
                camping.setFirstName(campingDto.getFirstName());
                camping.setLastName(campingDto.getLastName());
                camping.setDocumentType(campingDto.getDocumentType());
                camping.setDocumentNumber(campingDto.getDocumentNumber());
                camping.setBirthDate(campingDto.getBirthDate());
                camping.setQrCode(UUID.randomUUID().toString());
                camping.setCampingType(type);
                camping.setOrder(order);
                camping.setStatus(TicketStatus.ACTIVE);

                // Añadir a la lista del ppedido y sumar al precio total
                order.getCampings().add(camping);
                totalPrice = totalPrice.add(type.getPrice());
            }
        }

        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }
}
