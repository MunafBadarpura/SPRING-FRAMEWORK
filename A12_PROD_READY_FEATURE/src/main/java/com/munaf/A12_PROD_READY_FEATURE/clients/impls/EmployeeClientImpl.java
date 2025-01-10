package com.munaf.A12_PROD_READY_FEATURE.clients.impls;

import com.munaf.A12_PROD_READY_FEATURE.advices.ApiResponse;
import com.munaf.A12_PROD_READY_FEATURE.clients.EmployeeClient;
import com.munaf.A12_PROD_READY_FEATURE.dtos.EmployeeDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    public EmployeeClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        try {
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("getEmployeeById/{empId}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            return employeeDTO.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<EmployeeDTO> getAllEmployee() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                        .uri("getAllEmployees")
                        .retrieve()
                        .body(new ParameterizedTypeReference<>(){});

            return employeeDTOList.getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> createdEmployee = restClient.post()
                    .uri("createNewEmployee")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req,res) -> { // error handling
                        System.out.println("ERROR : " + new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>(){});
            return createdEmployee.getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> updatedEmployee = restClient.put()
                    .uri("updateEmployeeById/{empId}", id)
                    .body(employeeDTO)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            return updatedEmployee.getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteEmployeeById(Long id) {
        try {
            ApiResponse<Boolean> isEmployeeDeleted = restClient.delete()
                    .uri("deleteEmployeeById/{empId}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            return isEmployeeDeleted.getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
