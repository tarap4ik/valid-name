package com.org.test.validatenametest.controller;

import com.org.test.validatenametest.dto.SettingsDTO;
import com.org.test.validatenametest.service.SettingsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Operation(summary = "Получить все настройки", description = "Получить все настройки из базы данных")
    @GetMapping
    public List<SettingsDTO> getSettings() {
        return settingsService.getAllSettings();
    }

    @Operation(summary = "Получить настройку", description = "Добавить настройку по имени в базе данных")
    @GetMapping("/{name}")
    public SettingsDTO getSettings(@PathVariable @NotBlank String name) {
        return settingsService.getSettingsById(name);
    }

    @Operation(summary = "Добавить настройку", description = "Добавить настройку в базу данных")
    @PostMapping
    public ResponseEntity<Long> setSettings(@RequestBody @Valid SettingsDTO settings) {
        return new ResponseEntity<>(settingsService.createSettings(settings), HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить настройку", description = "Обновить настройку по имени в базе данных")
    @PutMapping("/{name}")
    public ResponseEntity<Void> updateSettings(@PathVariable @NotBlank String name, @RequestBody @Valid SettingsDTO settings) {
        settingsService.updateSettings(name, settings);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удалить настройку", description = "Удалить настройку по имени из базы данных")
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteSettings(@PathVariable @NotBlank String name) {
        settingsService.deleteSettings(name);
        return ResponseEntity.noContent().build();
    }


}
