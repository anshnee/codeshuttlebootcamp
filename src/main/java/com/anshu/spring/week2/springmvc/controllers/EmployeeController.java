package com.anshu.spring.week2.springmvc.controllers;

import com.anshu.spring.week2.springmvc.Exceptions.ResourceNotFoundException;
import com.anshu.spring.week2.springmvc.dto.EmployeeDTO;
import com.anshu.spring.week2.springmvc.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer employeeId){

        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(employeeId);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with Id "+ employeeId));
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee= employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById (@RequestBody @Valid EmployeeDTO employeeDTO , @PathVariable @Valid Integer employeeId){
        EmployeeDTO updatedEmployee= employeeService.updateEmployeeById(employeeDTO,employeeId);
        if(updatedEmployee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Integer employeeId){
         Boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
         if(gotDeleted) return ResponseEntity.ok(true);
         return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map <String,Object> updates , @PathVariable Integer employeeId){

        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(updates, employeeId);
        if(employeeDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);

    }

}
