package com.example.adventureprogearjava.mapper;

import com.example.adventureprogearjava.dto.ProductAttributeDTO;
import com.example.adventureprogearjava.dto.ProductDTO;
import com.example.adventureprogearjava.entity.Product;
import com.example.adventureprogearjava.entity.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {
    String api = "https://prime-tax-production.up.railway.app/api/v1/productAttributes/";

    @Mapping(target = "selfLink", source = "productAttribute.id", qualifiedByName = "idToLink")
    ProductAttributeDTO toDto(ProductAttribute productAttribute);

    ProductAttribute toEntity(ProductAttributeDTO dto);

    @Named("idToLink")
    default String getLink(Long id) {
        return api + id;
    }
}
