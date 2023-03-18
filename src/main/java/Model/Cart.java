package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cart {
	ArrayList productsList = new ArrayList();
	User user;
    CartStatus status;
    LocalDate orderDate;
    LocalDate deliveryDate;
    //DeliveryMan deliveryMan;
}
