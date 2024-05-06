package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.OrderItem;
import tn.spring.pispring.Entities.Orderr;

import java.util.List;

public interface OrderrInterface {
    Orderr addOrderr(Orderr orderr);

    List<Orderr> retrieveOrderr();

    List<Orderr> retrieveOrderrbyuser();


    Orderr updateOrderr(Orderr orderr);

    Orderr updateOrderr(int id, Orderr order);

    Orderr retrieveOrderr(int id);

    void removeOrder(int id);


}
