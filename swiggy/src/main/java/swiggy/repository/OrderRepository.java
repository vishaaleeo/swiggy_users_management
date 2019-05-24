package swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swiggy.domain.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {


    @Query("select max(r.orderGroupIdentifier) from Order r where r.userIdentifier=?1")
    Integer findMaxGroupOrderIdentifierByUser(Integer userIdentifier);

    @Query("select r from Order r where r.userIdentifier=?1 and r.deleteFlag!=true and r.orderStatus='oncart'")
    List<Order> findOnCart(Integer userIdentifier);


}
