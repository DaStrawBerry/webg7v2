package webgr7.hotelbk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import webgr7.hotelbk.dto.VoucherDTO;
import webgr7.hotelbk.model.Voucher;
import webgr7.hotelbk.repository.VoucherRepository;

import java.util.Date;
import java.util.List;

@Service
@RequestMapping("/voucher")
public class VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers(){
        return voucherRepository.findAll();
    }

    public Voucher createVoucher(VoucherDTO voucherDTO){
        Voucher voucher = new Voucher();
        voucher.setName(voucherDTO.getName());
        voucher.setType(voucherDTO.getType());
        voucher.setHotelType(voucherDTO.getHotelType());
        voucher.setLocation(voucherDTO.getLocation());
        voucher.setStTime(voucherDTO.getStTime());
        voucher.setEdTime(voucherDTO.getEdTime());
        voucher.setValue(voucherDTO.getValue());
        voucher.setPercent(voucherDTO.getPercent());
        voucher.setQuantity(voucherDTO.getQuantity());
        voucher.setDes(voucherDTO.getDes());
        return voucherRepository.save(voucher);
    }

    public Voucher getVoucherById(Long voucherId) {
        return voucherRepository.findById(voucherId).orElse(null);
    }

    public Voucher updateVoucherById(Long voucherId, VoucherDTO voucherDTO){
        Voucher voucher = voucherRepository.findById(voucherId).orElse(null);
        if(voucher != null){
            voucher.setName(voucherDTO.getName());
            voucher.setType(voucherDTO.getType());
            voucher.setHotelType(voucherDTO.getHotelType());
            voucher.setLocation(voucherDTO.getLocation());
            voucher.setStTime(voucherDTO.getStTime());
            voucher.setEdTime(voucherDTO.getEdTime());
            voucher.setValue(voucherDTO.getValue());
            voucher.setPercent(voucherDTO.getPercent());
            voucher.setQuantity(voucherDTO.getQuantity());
            voucher.setDes(voucherDTO.getDes());
            return voucher;
        }else{
            return null;
        }
    }

    public void deleteVoucherById(Long voucherId){
        voucherRepository.deleteById(voucherId);
    }
}
