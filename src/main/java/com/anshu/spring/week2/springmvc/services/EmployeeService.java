package com.anshu.spring.week2.springmvc.services;

import com.anshu.spring.week2.springmvc.Exceptions.ResourceNotFoundException;
import com.anshu.spring.week2.springmvc.dto.EmployeeDTO;
import com.anshu.spring.week2.springmvc.entities.EmployeeEntity;
import com.anshu.spring.week2.springmvc.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public void isEmployeeIdExist(Integer ID){
        boolean exist= employeeRepository.existsById(ID);
        if(!exist) throw new ResourceNotFoundException("Employee Not found with id "+ID);
    }


    public  EmployeeDTO createNewEmployee(EmployeeDTO employeeEntity) {

        EmployeeEntity toSaveEntity= modelMapper.map(employeeEntity,EmployeeEntity.class);
        EmployeeEntity saveEmployee = employeeRepository.save(toSaveEntity);
        return modelMapper.map(saveEmployee,EmployeeDTO.class);
    }


    public  List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());

    }

    public Optional<EmployeeDTO> getEmployeeById(Integer id){
       return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }


    public boolean deleteEmployeeById(Integer id) {

        isEmployeeIdExist(id);
        employeeRepository.deleteById(id);
        return true;

        }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Integer id) {

        //Get the required key and value from client to be updated in DB and set it as updated Map
        isEmployeeIdExist(id);

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //
        updates.forEach((field, value) -> {
        // Finding the  fields to be updated  in Entity class to be updated and setting it to public
            Field fieldToBeUpdated =ReflectionUtils.findRequiredField(EmployeeEntity.class , field);
            fieldToBeUpdated.setAccessible(true);
        // Updating the required field with the new value
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity , value);

        });
        return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDTO.class);

    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Integer id) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO , EmployeeEntity.class);
        isEmployeeIdExist(id);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}

