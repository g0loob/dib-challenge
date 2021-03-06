package com.example.dibchallenge.beer;

import com.example.dibchallenge.beer.mash_temp.BeerMashTemperatureEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beer", schema = "public")
public class BeerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "external_id", unique = true)
    private Long externalId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(
            mappedBy = "beer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<BeerMashTemperatureEntity> temperatures = new ArrayList<>();

    public BeerEntity() {
    }

    private BeerEntity(Long externalId, String name, String description) {
        this.externalId = externalId;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BeerMashTemperatureEntity> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<BeerMashTemperatureEntity> temperatures) {
        this.temperatures = temperatures;
    }

    public BigDecimal getMeanTemperature() {
        if (getTemperatures().size() == 0) {
            return BigDecimal.ZERO;
        }

        return getTemperatures()
                .stream()
                .map(BeerMashTemperatureEntity::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(getTemperatures().size()), RoundingMode.HALF_UP);
    }

    // TODO: add methods for adding/removing temperature

    // https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BeerEntity))
            return false;

        BeerEntity other = (BeerEntity) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BeerEntity{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Long externalId;
        private String name;
        private String description;

        public Builder setExternalId(Long externalId) {
            this.externalId = externalId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public BeerEntity build() {
            return new BeerEntity(externalId, name, description);
        }
    }

}
