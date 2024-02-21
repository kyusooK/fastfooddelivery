package fastfood.domain;

import fastfood.StoreApplication;
import fastfood.domain.CookCancelled;
import fastfood.domain.CookStarted;
import fastfood.domain.OrderAccepted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Store_table")
@Data
//<<< DDD / Aggregate Root
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String userId;

    private Long menuId;

    private String menuName;

    private Integer qty;

    private String address;

    private String status;

    private Date cookingDt;

    @PostPersist
    public void onPostPersist() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        CookCancelled cookCancelled = new CookCancelled(this);
        cookCancelled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public static StoreRepository repository() {
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    //<<< Clean Arch / Port Method
    public static void acceptOrder(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        OrderAccepted orderAccepted = new OrderAccepted(store);
        orderAccepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            OrderAccepted orderAccepted = new OrderAccepted(store);
            orderAccepted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelCook(OrderCancelled orderCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        CookCancelled cookCancelled = new CookCancelled(store);
        cookCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            CookCancelled cookCancelled = new CookCancelled(store);
            cookCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
