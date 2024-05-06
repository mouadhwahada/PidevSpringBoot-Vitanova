package tn.spring.pispring;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.spring.pispring.Entities.Orderr;
import tn.spring.pispring.dtos.OrderDto;
import org.mapstruct.Mappings; // Import manquant
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;



@Mapper

public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mappings({
            @Mapping(source = "orderr.idOrder", target = "idOrder"),
            @Mapping(source = "orderr.dateOrder", target = "dateOrder"),
            @Mapping(source = "orderr.status", target = "status"),
            @Mapping(source = "orderr.costOrder", target = "costOrder"),
            @Mapping(source = "orderr.codeOrder", target = "codeOrder"),
            @Mapping(source = "orderr.orderItems", target = "orderItems"),
            @Mapping(source = "orderr.user", target = "user")
            // Ajoutez d'autres mappings si nécessaire
    })
    OrderDto toOrderDto(Orderr orderr);}