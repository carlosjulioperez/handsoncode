package ec.carper.oms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.carper.oms.data.payloads.request.OrdersRequest;
import ec.carper.oms.data.payloads.response.MessageResponse;
import ec.carper.oms.service.OrdersService;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/order")
@ApiResponses(value = {
    @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
    @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
    @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
})
public class OrdersController {
    @Autowired
    OrdersService ordersService; 

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addOrder( @RequestBody OrdersRequest ordersRequest){
        MessageResponse newOrder = ordersService.addOrder(ordersRequest);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

}
