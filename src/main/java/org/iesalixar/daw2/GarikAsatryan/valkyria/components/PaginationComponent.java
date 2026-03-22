package org.iesalixar.daw2.GarikAsatryan.valkyria.components;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.FilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationComponent {

    /**
     * Convierte los parámetros de FilterDTO en un objeto Pageable.
     */
    public Pageable createPageable(FilterDTO filterDTO, String defaultOrder) {
        String sortProperty = (filterDTO.getOrder() == null || filterDTO.getOrder().isBlank())
                ? defaultOrder : filterDTO.getOrder();

        Sort sort = "desc".equalsIgnoreCase(filterDTO.getOrderBy())
                ? Sort.by(sortProperty).descending()
                : Sort.by(sortProperty).ascending();

        int page = Math.max(filterDTO.getPage(), 0);
        int size = (filterDTO.getItemsPerPage() < 1) ? 9 : filterDTO.getItemsPerPage();

        return PageRequest.of(page, size, sort);
    }

    /**
     * Actualiza los metadatos de FilterDTO basándose en el resultado de la página.
     */
    public void updateFilterMetadata(FilterDTO filterDTO, Page<?> page) {
        filterDTO.setTotalPages(page.getTotalPages());
        filterDTO.setTotalElements((int) page.getTotalElements());
    }
}