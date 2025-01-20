package com.munaf.A12_PROD_READY_FEATURE.clients.impls;

import com.munaf.A12_PROD_READY_FEATURE.advices.ApiResponse;
import com.munaf.A12_PROD_READY_FEATURE.clients.EmployeeClient;
import com.munaf.A12_PROD_READY_FEATURE.dtos.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient EmployeeServiceRestClient;

    public EmployeeClientImpl(RestClient restClient) {
        this.EmployeeServiceRestClient = restClient;
    }

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        try {
            ApiResponse<EmployeeDTO> employeeDTO = EmployeeServiceRestClient.get()
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
        log.error("error log");
        log.warn("warn log");
        log.info("info log");
        log.debug("debug log");
        log.trace("trace log");

        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = EmployeeServiceRestClient.get()
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
            ApiResponse<EmployeeDTO> createdEmployee = EmployeeServiceRestClient.post()
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
            ApiResponse<EmployeeDTO> updatedEmployee = EmployeeServiceRestClient.put()
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
            ApiResponse<Boolean> isEmployeeDeleted = EmployeeServiceRestClient.delete()
                    .uri("deleteEmployeeById/{empId}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            return isEmployeeDeleted.getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}