package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.model.Voucher;
import webgr7.hotelbk.repository.ClientRepo;
import webgr7.hotelbk.repository.VoucherRepo;
import webgr7.hotelbk.service.VoucherService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherServiceImp implements VoucherService {
    @Autowired
    private VoucherRepo voucherRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Override
    public List<Voucher> getClientVoucher(Long clientId) {
        return voucherRepo.findAllByClients(clientRepo.findById(clientId).get());
    }
}
