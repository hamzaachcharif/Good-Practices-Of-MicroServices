package org.openlab.openlabcustomerservice.service;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    public CustomerResponseDTO getCustomer(String id);
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomer();

}
