package org.uniplovdiv.fmi.springjpa.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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