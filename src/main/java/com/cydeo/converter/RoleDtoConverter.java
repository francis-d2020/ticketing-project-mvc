package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


//this claas converts the strings from the html to RoleDTO objects
@Component
@ConfigurationPropertiesBinding //in any situation when we have conversion problem spring will
//check these classes (no need to add it anymore, spring does it automatically whenever we use the convert package
public class RoleDtoConverter implements Converter<String,RoleDTO> {

    RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {

        if (source==null || source.equals(""))//in case select is chosen in the form so null is checked
            //in the notNull validation
            return null;

        return roleService.findById(Long.parseLong(source));
    }
}
