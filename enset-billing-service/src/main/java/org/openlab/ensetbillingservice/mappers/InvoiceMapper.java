package org.openlab.ensetbillingservice.mappers;


import org.mapstruct.Mapper;
import org.openlab.ensetbillingservice.dto.InvoiceRequestDTO;
import org.openlab.ensetbillingservice.dto.InvoiceResponseDTO;
import org.openlab.ensetbillingservice.entities.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
