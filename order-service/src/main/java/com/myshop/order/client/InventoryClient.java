package com.myshop.order.client;


import com.myshop.order.client.request.InventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @PostMapping("/inventories/check")
    Boolean checkStock(@RequestBody InventoryRequest request);

    @PostMapping("inventories/update")
    void updateStock(@RequestBody InventoryRequest request);


}