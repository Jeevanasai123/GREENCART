package com.greencart.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(length = 1000)
    private String description;
    
    private Double price;
    private String imageUrl;

    private String waterNeeds;
    private String sunlight;
    private Boolean isBeginnerFriendly;
    private Boolean isLowMaintenance;

    public Plant() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getWaterNeeds() { return waterNeeds; }
    public void setWaterNeeds(String waterNeeds) { this.waterNeeds = waterNeeds; }
    public String getSunlight() { return sunlight; }
    public void setSunlight(String sunlight) { this.sunlight = sunlight; }
    public Boolean getIsBeginnerFriendly() { return isBeginnerFriendly; }
    public void setIsBeginnerFriendly(Boolean isBeginnerFriendly) { this.isBeginnerFriendly = isBeginnerFriendly; }
    public Boolean getIsLowMaintenance() { return isLowMaintenance; }
    public void setIsLowMaintenance(Boolean isLowMaintenance) { this.isLowMaintenance = isLowMaintenance; }
}
