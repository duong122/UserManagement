package com.example.vti_2506_usermanagement.modelMapper;

import com.example.vti_2506_usermanagement.dto.UserGroupMappingDTO;
import com.example.vti_2506_usermanagement.entity.UserGroupMapping;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper mapper = new ModelMapper();
//
//        // Cấu hình an toàn
//        mapper.getConfiguration()
//                .setFieldMatchingEnabled(true)
//                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) // chú ý import này
//                .setSkipNullEnabled(true);
//
//        // Cấu hình riêng cho UserGroupMapping
//        mapper.typeMap(UserGroupMappingDTO.class, UserGroupMapping.class)
//                .addMappings(m -> m.skip(UserGroupMapping::setId)); // bỏ qua id để tránh lỗi trùng
//
//        return mapper;
//    }

}
