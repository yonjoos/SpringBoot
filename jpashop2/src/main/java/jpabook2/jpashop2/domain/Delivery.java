package jpabook2.jpashop2.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistrationManager;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    /*
    ####################  ATTRIBUTES  ###################
     */
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;


    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;



    /*
    ####################  연관관계   ###################
     */

    // ORDER 1 : 1
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;


}
