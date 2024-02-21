package fastfood.domain;

import fastfood.DeliveryApplication;
import fastfood.domain.DeliveryCompleted;
import fastfood.domain.FoodPickedup;
import fastfood.domain.RiderAssigned;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String riderId;

    private String userId;

    private Long storeId;

    private String orderId;

    private String address;

    private String status;

    private Date deliveryDt;

    @PostPersist
    public void onPostPersist() {
        RiderAssigned riderAssigned = new RiderAssigned(this);
        riderAssigned.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        FoodPickedup foodPickedup = new FoodPickedup(this);
        foodPickedup.publishAfterCommit();

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void propagateDeliveryCall(CookStarted cookStarted) {
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        RiderAssigned riderAssigned = new RiderAssigned(delivery);
        riderAssigned.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(cookStarted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            RiderAssigned riderAssigned = new RiderAssigned(delivery);
            riderAssigned.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
