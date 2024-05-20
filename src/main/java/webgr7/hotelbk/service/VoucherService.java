package webgr7.hotelbk.service;


import webgr7.hotelbk.model.Voucher;

import java.util.List;

public interface VoucherService {
    public List<Voucher> getClientVoucher(Long clientId);
}
