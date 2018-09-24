package be.leerstad.address.service.mapper;

import be.leerstad.address.business.Address;
import be.leerstad.address.service.dto.AddressDTO;
import service.AbstractMapper;

public class AddressMapper extends AbstractMapper<Address, AddressDTO> {
    @Override
    public AddressDTO mapToDTO(Address address) {
        if (address == null)
            return null;
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setNumber(String.valueOf(address.getNumber()));
        addressDTO.setStreet(address.getStreet());
        addressDTO.setZipCode(String.valueOf(address.getZipCode()));
        return addressDTO;
    }

    @Override
    public Address mapToObj(AddressDTO addressDTO) {
        if (addressDTO == null)
            return null;
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setZipCode(Integer.valueOf(addressDTO.getZipCode()));
        address.setStreet(addressDTO.getStreet());
        address.setNumber(Integer.valueOf(addressDTO.getNumber()));
        return address;
    }
}
