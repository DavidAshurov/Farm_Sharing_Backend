package farm_sharing.client.service;


import farm_sharing.client.dto.ClientDto;
import farm_sharing.client.dto.NewClientDto;

import java.util.List;

public interface ClientService {
    boolean addClient(NewClientDto newClientDto);
    ClientDto findClientById(Long id);
    ClientDto updateClientName(Long id, String name);
    ClientDto updateClientPhoneNumber(Long id, String phoneNumber);
    ClientDto updateClientCity(Long id, String city);
    ClientDto updateClientEmail(Long id, String email);
    ClientDto removeClient(Long id);

    List<ClientDto> findClientsByCity(String city);
    List<ClientDto> findClientsByName(String name);
    List<ClientDto> findClientsByPhoneNumber(String phoneNumber);
    List<ClientDto> findClientsByEmail(String email);
    List<ClientDto> findAllClients();
    boolean existsById(Long id);

}
