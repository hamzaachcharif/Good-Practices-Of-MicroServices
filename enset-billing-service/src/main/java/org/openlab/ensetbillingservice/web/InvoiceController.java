package org.openlab.ensetbillingservice.web;

import org.openlab.ensetbillingservice.dto.InvoiceRequestDTO;
import org.openlab.ensetbillingservice.dto.InvoiceResponseDTO;
import org.openlab.ensetbillingservice.entities.Invoice;
import org.openlab.ensetbillingservice.services.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path="/invoices")
    public List<InvoiceResponseDTO> allInvoices(){
        return invoiceService.allInvoices();
    }
    @GetMapping(path="/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id")String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }

    @GetMapping(path="/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoiceByCustomer(@PathVariable String customerId){

        return invoiceService.invoiceByCustomerId(customerId);
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        return e.getMessage();
    }
}
