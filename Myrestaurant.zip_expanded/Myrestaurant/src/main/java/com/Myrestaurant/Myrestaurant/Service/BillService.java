package com.Myrestaurant.Myrestaurant.Service;
import com.Myrestaurant.Myrestaurant.Entity.BillEntity;
import com.Myrestaurant.Myrestaurant.Repositery.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<BillEntity> getAllBills() {
        return billRepository.findAll();
    }

    public BillEntity getBillById(Long id) {
        Optional<BillEntity> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }

    public BillEntity createBill(BillEntity bill) {
        return billRepository.save(bill);
    }

    public BillEntity updateBill(Long id, BillEntity bill) {
        if (billRepository.existsById(id)) {
            bill.setOrdId(id); // Ensure the correct ID is set
            return billRepository.save(bill);
        } else {
            return null;
        }
    }

    public boolean deleteBill(Long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
