package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webgr7.hotelbk.service.VoucherService;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/client/{client_id}")
    private ResponseEntity<?> getMyVoucher(@PathVariable Long client_id){
        return ResponseEntity.ok(voucherService.getClientVoucher(client_id));
    }
}
