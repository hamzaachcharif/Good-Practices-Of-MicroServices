package org.openlab.ensetbillingservice.services;

import org.openlab.ensetbillingservice.dto.InvoiceRequestDTO;
import org.openlab.ensetbillingservice.dto.InvoiceResponseDTO;
import org.openlab.ensetbillingservice.entities.Customer;
import org.openlab.ensetbillingservice.entities.Invoice;
import org.openlab.ensetbillingservice.exceptions.CustomerNotFoundException;
import org.openlab.ensetbillingservice.mappers.InvoiceMapper;
import org.openlab.ensetbillingservice.openFeign.CustomerRestClient;
import org.openlab.ensetbillingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException {
        /*
        * vérificationd'intégerité reféretelles invoice<=> Customer
        * */
        Customer customer=null;
        try {
         customer=customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());

        }catch (Exception e){
            throw new CustomerNotFoundException("Customer Not Found");
        }
        Invoice invoice =invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice savedInvoice=invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {

        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }


    @Override
    public List<InvoiceResponseDTO> invoiceByCustomerId(String customerId) {

        List<Invoice> invoices=invoiceRepository.findByCustomerId(customerId);
        for(Invoice invoice:invoices){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(i->invoiceMapper.fromInvoice(i)
                ).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices=invoiceRepository.findAll();
        invoices.forEach(i->{
            Customer customer=customerRestClient.getCustomer(i.getCustomerId());
            i.setCustomer(customer);
        });
        return invoices.stream()
                .map((i->invoiceMapper.fromInvoice(i))).collect(Collectors.toList());

    }
}
