package webgr7.hotelbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webgr7.hotelbk.dto.VoucherDTO;
import webgr7.hotelbk.service.implement.VoucherServiceImp;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherServiceImp voucherService;

    @GetMapping("/all-vouchers")
    //List<Voucher>
    public ResponseEntity<?> getAllVouchers(){
        try{
            return ResponseEntity.ok(voucherService.getAllVouchers());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error fetching vouchers " + e.getMessage());
        }
    }

    @GetMapping("/{voucherId}")
    //Voucher
    public ResponseEntity<?> getVoucherById(@PathVariable Long voucherId){
        try{
            if(voucherService.getVoucherById(voucherId)== null) {
                return ResponseEntity.badRequest().body("Voucher not found");
            }
            return ResponseEntity.ok(voucherService.getVoucherById(voucherId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error fetching voucher " + e.getMessage());
        }
    }

    @PostMapping("/create")
    //Voucher
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucherDTO){
        try{
            return ResponseEntity.ok(voucherService.createVoucher(voucherDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error creating voucher " + e.getMessage());
        }
    }

    @PutMapping("/update/{voucherId}")
    //Voucher
    public ResponseEntity<?> updateVoucher(@PathVariable Long voucherId, @RequestBody VoucherDTO voucherDTO){
        try{
            if(voucherService.getVoucherById(voucherId)== null) {
                return ResponseEntity.badRequest().body("Voucher not found");
            }
            return ResponseEntity.ok(voucherService.updateVoucherById(voucherId, voucherDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating voucher " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{voucherId}")
    //List<Voucher>
    public ResponseEntity<?> deleteVoucher(@PathVariable Long voucherId){
        try{
            if(voucherService.getVoucherById(voucherId)== null) {
                return ResponseEntity.badRequest().body("Voucher not found");
            }
            voucherService.deleteVoucherById(voucherId);
            return ResponseEntity.ok("Voucher deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error deleting voucher " + e.getMessage());
        }
    }
}
