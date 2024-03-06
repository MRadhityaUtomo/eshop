package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentTest {
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Miku");
        product2.setProductQuantity(1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", 
            products, 1708560000L, "Mbak SekaiDe");
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
                "", null, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherStatusPending() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusInvalid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
                "", order, paymentData, "bazinga");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusNull() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
                "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferStatusPending() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferStatusSuccess() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferStatusRejected() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferStatusInvalid() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
                "", order, paymentData, "bazinga");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferStatusNull() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
                "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("bazinga");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToNullStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToSuccess() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToRejected() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToInvalidStatus() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("bazinga");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToNullStatus() {
        paymentData.put("bankName", "MANDIRI");
        paymentData.put("referenceCode", "0123456789");

        Payment payment = new Payment("a7c8124f-67d3-4a22-9e45-8f6b7d2e9f55", 
            "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }
}