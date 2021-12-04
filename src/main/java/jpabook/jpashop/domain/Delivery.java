package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="deliverry_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order oder;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DelivertyStatus status; //READY, COMP
}
