package fastfood.domain;

import fastfood.domain.*;
import fastfood.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CookCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String userId;
    private Long menuId;
    private String menuName;
    private Integer qty;
    private String address;
    private String status;
    private Date cookingDt;

    public CookCancelled(Store aggregate) {
        super(aggregate);
    }

    public CookCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
