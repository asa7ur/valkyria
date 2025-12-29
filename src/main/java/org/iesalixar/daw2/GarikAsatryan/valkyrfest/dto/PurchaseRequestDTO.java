package org.iesalixar.daw2.GarikAsatryan.valkyrfest.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseRequestDTO {
    private List<TicketPurchaseDTO> tickets = new ArrayList<>();
    private List<CampingPurchaseDTO> campings = new ArrayList<>();
}