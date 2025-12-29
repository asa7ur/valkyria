package org.iesalixar.daw2.GarikAsatryan.valkyrfest.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.CampingPurchaseDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.PurchaseRequestDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto.TicketPurchaseDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.*;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.CampingTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.OrderRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.TicketTypeRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final CampingTypeRepository campingTypeRepository;

    @Transactional
    public Order executePurchase(PurchaseRequestDTO request, String userEmail) {

        // Validamos que no intente comprar nada (ambas listas vacías)
        boolean hasTickets = request.getTickets() != null && !request.getTickets().isEmpty();
        boolean hasCampings = request.getCampings() != null && !request.getCampings().isEmpty();

        if (!hasTickets && !hasCampings) {
            throw new AppException("msg.validation.atLeastOne");
        }

        // Buscamos al usuario que realiza la compra
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException("msg.error.userNotFound"));

        // Creamos la cabecera del pedido
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PAID); // Asumimos pago inmediate por ahora
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Procesamos los tickets
        if (hasTickets) {
            for (TicketPurchaseDTO tDto : request.getTickets()) {
                TicketType type = ticketTypeRepository.findById(tDto.getTicketTypeId())
                        .orElseThrow(() -> new AppException("msg.error.ticketTypeNotFound"));

                // Validar stock
                if (type.getStockAvailable() <= 0) throw new AppException("msg.error.noStock", type.getName());

                // Crear entidad Ticket
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

                // Reducir stock
                type.setStockAvailable(type.getStockAvailable() - 1);
                ticketTypeRepository.save(type);
            }
        }

        // Procesamos el Camping
        if (hasCampings) {
            for (CampingPurchaseDTO cDto : request.getCampings()) {
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
        
        // Guardamos el pedido final con el precio total
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }
}


















