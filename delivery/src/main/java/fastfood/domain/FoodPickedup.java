package fastfood.domain;

import fastfood.domain.*;
import fastfood.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class FoodPickedup extends AbstractEvent {

    private Long id;
    private String riderId;
    private String userId;
    private Long storeId;
    private String orderId;
    private String address;
    private String status;
    private Date deliveryDt;

    public FoodPickedup(Delivery aggregate) {
        super(aggregate);
    }

    public FoodPickedup() {
        super();
    }
}
//>>> DDD / Domain Event
