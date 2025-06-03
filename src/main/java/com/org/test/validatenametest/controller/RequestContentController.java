package com.org.test.validatenametest.controller;

import com.org.test.validatenametest.dto.RequestContentDTO;
import com.org.test.validatenametest.service.RequestContentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/content")
public class RequestContentController {

    private final RequestContentService requestContentService;

    public RequestContentController(RequestContentService requestContentService) {
        this.requestContentService = requestContentService;
    }

    @Operation(summary = "Получить json контент", description = "Получить json контент загруженный через миграции")
    @GetMapping
    public List<RequestContentDTO> getContent() {
        return requestContentService.getAllContent();
    }

    @Operation(summary = "Получить json контент по id", description = "Получить json контент по ключу загруженный через миграции")
    @GetMapping("/{id}")
    public String getContentById(@PathVariable @Min(1) Long id) {
        return requestContentService.getContentById(id);
    }

    @Operation(summary = "Добавить json контент", description = "Добавить json контент в базу данных")
    @PostMapping
    public ResponseEntity<Long> addContent(@RequestBody @NotBlank String content) {
        return new ResponseEntity<>(requestContentService.createContent(content), HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить json контент", description = "Обновить json контент в базе данных")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable @Min(1) Long id, @RequestBody @NotBlank String content) {
        requestContentService.updateContentById(id, content);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удалить json контент", description = "Удалить json контент из базы данных")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable @Min(1) Long id) {
        requestContentService.deleteContentById(id);
        return ResponseEntity.noContent().build();
    }


}
