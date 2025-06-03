package com.org.test.validatenametest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "settings")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Setter
@Getter
public class SettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

}
