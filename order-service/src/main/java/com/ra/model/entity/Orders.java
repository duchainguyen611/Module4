package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.model.entity.ENUM.StatusOrders;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrders statusOrders;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private LocalDate createdAt;
    private LocalDate receivedAt;
    private Long userId;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<Order_Detail> orderDetails;


}
