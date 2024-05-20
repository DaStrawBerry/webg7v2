package webgr7.hotelbk.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import webgr7.hotelbk.dto.VoucherDTO;
import webgr7.hotelbk.model.Voucher;
import webgr7.hotelbk.repository.VoucherRepository;
import webgr7.hotelbk.service.VoucherService;

import java.util.List;

@Service
@RequestMapping("/voucher")
public class VoucherServiceImp implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Override
    public List<Voucher> getAllVouchers(){
        return voucherRepository.findAll();
    }
    @Override
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
    @Override
    public Voucher getVoucherById(Long voucherId) {
        return voucherRepository.findById(voucherId).orElse(null);
    }
    @Override
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
    @Override
    public void deleteVoucherById(Long voucherId){
        voucherRepository.deleteById(voucherId);
    }
}
