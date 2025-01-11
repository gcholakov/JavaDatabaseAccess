package org.uniplovdiv.fmi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "COUNTRIES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Country {
    @Id
    @Column(name = "COUNTRY_ID", nullable = false, length = 2)
    private String countryId;

    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "REGION_ID")
    @ToString.Exclude
    private Region region;
}