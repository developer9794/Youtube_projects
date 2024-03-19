package com.Myrestaurant.Myrestaurant.Controller;

import com.Myrestaurant.Myrestaurant.Entity.BillEntity;
import com.Myrestaurant.Myrestaurant.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<BillEntity>> getAllBills() {
        List<BillEntity> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillEntity> getBillById(@PathVariable Long id) {
        BillEntity bill = billService.getBillById(id);
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BillEntity> createBill(@RequestBody BillEntity bill) {
        BillEntity createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillEntity> updateBill(@PathVariable Long id, @RequestBody BillEntity bill) {
        BillEntity updatedBill = billService.updateBill(id, bill);
        if (updatedBill != null) {
            return new ResponseEntity<>(updatedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        boolean deleted = billService.deleteBill(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
