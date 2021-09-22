package ec.carper.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.carper.oms.data.model.Orders;
import ec.carper.oms.data.payloads.request.OrdersRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;
import ec.carper.oms.data.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    public MessageResponse addOrder(OrdersRequest ordersRequest){
        Orders order = new Orders();
        order.setOrderNumber(ordersRequest.getOrderNumber());
        order.setDate(ordersRequest.getDate());
        order.setCustomer(ordersRequest.getCustomer());
        order.setShippingAddresses(ordersRequest.getShippingAddresses());
        order.setPaymentType(ordersRequest.getPaymentType());
        order.setLines(ordersRequest.getLines());
        order.setTotal(ordersRequest.getTotal());
        ordersRepository.save(order);
        return new MessageResponse("New Order created successfully");
    } 
}
