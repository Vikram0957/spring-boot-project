package com.micorservicesarch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity

public record Employee (@Id
                        @GeneratedValue(strategy = GenerationType.AUTO)
                        int id,
                        String name){}
