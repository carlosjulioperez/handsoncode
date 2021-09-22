package ec.carper.oms.service;

import org.springframework.stereotype.Component;

import ec.carper.oms.data.payloads.request.OrdersRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;

@Component
public interface OrdersService {
    MessageResponse addOrder(OrdersRequest ordersRequest);
}