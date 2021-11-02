package org.openlab.ensetbillingservice.services;

import org.openlab.ensetbillingservice.dto.InvoiceRequestDTO;
import org.openlab.ensetbillingservice.dto.InvoiceResponseDTO;
import org.openlab.ensetbillingservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException;
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoiceByCustomerId(String customerId);
    List<InvoiceResponseDTO> allInvoices();

}
