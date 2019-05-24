package swiggy.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import swiggy.domain.Offer;
import swiggy.domain.Order;
import swiggy.repository.OfferRepository;
import swiggy.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Order createOrder(Order order) {


        List<Order> onCartOrders = orderRepository.findOnCart(order.getUserIdentifier());


        System.out.println(onCartOrders.toString());

        if (onCartOrders.isEmpty()) {
            Integer identifier = orderRepository.findMaxGroupOrderIdentifierByUser(order.getUserIdentifier());

            if(identifier==null) {
                identifier=0;
            }

            System.out.print(identifier);
            order.setOrderGroupIdentifier(identifier+1);
            order.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            order.setUpdatedTme(new Timestamp(System.currentTimeMillis()));
            order.setOrderStatus("oncart");

            return orderRepository.save(order);
        }

        else if (onCartOrders.get(0).getRestaurantIdentifier() == order.getRestaurantIdentifier()) {

            for (Order onCartOrder : onCartOrders) {

                if (onCartOrder.getFoodIdentifier() == order.getFoodIdentifier()) {

                    if(order.getFoodCount()!=onCartOrder.getFoodCount()) {

                        onCartOrder.setFoodCount(onCartOrder.getFoodCount());

                        return orderRepository.save(onCartOrder);
                    }

                }
            }
            onCartOrders.get(0).setOrderIdentifier(null);
            onCartOrders.get(0).setFoodIdentifier(order.getFoodIdentifier());
            onCartOrders.get(0).setFoodCount(order.getFoodCount());
            onCartOrders.get(0).setCustomizationIdentifier(order.getCustomizationIdentifier());
            return orderRepository.save(onCartOrders.get(0));

        }
        else {
            return null;
        }
    }





    public Order updateOrder(Order order) {

        Order toUpdateOrder =orderRepository.findOne(order.getOrderIdentifier());
        if(order.getOrderStatus()!=null){
            toUpdateOrder.setOrderStatus(order.getOrderStatus());
        }

        if(order.getFoodCount()!=null && order.getFoodCount()!=toUpdateOrder.getFoodCount()) {

            if(order.getFoodCount()==0) {
                deleteOrder(toUpdateOrder);
            }
            toUpdateOrder.setFoodCount(order.getFoodCount());
        }

        if(order.getCustomizationIdentifier()!=null && order.getCustomizationIdentifier()!="") {

            toUpdateOrder.setCustomizationIdentifier(order.getCustomizationIdentifier());
        }

        if(order.getOrderStatus()!=null && order.getOrderStatus()!="") {

            toUpdateOrder.setOrderStatus(order.getOrderStatus());
        }
        toUpdateOrder.setUpdatedTme(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(toUpdateOrder);

    }


    public void deleteOrder(Order toDeleteOrder) {

        toDeleteOrder.setDeleteFlag(true);
        orderRepository.save(toDeleteOrder);


    }

    public Order checkOffer(Order order) {

        Offer offer=offerRepository.findOne(order.getOfferIdentifier());
        if(offer==null)
            return null;
        if(offer.getRestaurantIdentifier()==order.getRestaurantIdentifier()) {

            applyOffer(offer,order);
        }
        return order;

    }

    public void applyOffer(Offer offer,Order order) {



    }

    public void displayOrder() {

    }


}
