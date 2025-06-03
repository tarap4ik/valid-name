package com.org.test.validatenametest.controller;

import com.org.test.validatenametest.dto.person.PersonRequestDTO;
import com.org.test.validatenametest.service.DataService;
import com.org.test.validatenametest.service.FactorService;
import com.org.test.validatenametest.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final DataService dataService;
    private final FactorService factorService;
    private final PersonService personService;

    public PersonController(DataService dataService, FactorService factorService, PersonService personService) {
        this.dataService = dataService;
        this.factorService = factorService;
        this.personService = personService;
    }

    @Operation(summary = "Получить все реквесты и имена", description = "Получить все реквесты и имена regPerson и verified_name")
    @GetMapping
    public ResponseEntity<List<PersonRequestDTO>> getPersonRequests() {
        var result = personService.getAllPersonRequests();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить стоп факторы для всех реквестов", description = "Получить стоп факторы в виде связки id реквеста и булевого значения для всех реквестов")
    @GetMapping("/validate/factor")
    public Map<UUID, Boolean> request() {
        return factorService.validateFactor();
    }

    @Operation(summary = "Получить стоп факторы для реквеста", description = "Получить стоп факторы для реквеста по его id")
    @GetMapping("/validate/factor/{uuid}")
    public boolean request(UUID uuid) {
        return factorService.validateFactor(uuid);
    }

    @Operation(summary = "Загрузить json контент в БД", description = "Загрузить json контент в виде сущностей в БД")
    @PostMapping
    public ResponseEntity<Void> post() {
        dataService.setDataFromJSONContent();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить excel отчет по id реквеста", description = "Получить excel отчет по рассчету расстояния Левенштейна для всех комбинаций по id реквеста")
    @GetMapping("/report/{uuid}")
    public ResponseEntity<FileSystemResource> getReport(UUID uuid) {
        File file = new File(uuid + ".xlsx");
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new FileSystemResource(file));
    }

}
