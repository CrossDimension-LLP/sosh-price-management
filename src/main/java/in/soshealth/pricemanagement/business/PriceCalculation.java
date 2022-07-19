package in.soshealth.pricemanagement.business;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.Amount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class PriceCalculation {
    public Amount customerPriceCalculation(Amount actualPrice, BigDecimal deductionRate) {
        MathContext mc = new MathContext(0);
        BigDecimal actualPriceValue = new BigDecimal(actualPrice.getValue());
        BigDecimal customerPriceTemp = actualPriceValue.multiply(deductionRate);
        BigDecimal customerPrice = actualPriceValue.subtract(customerPriceTemp.divide( new BigDecimal(100)).round(mc)) ;
        Amount customerAmount = new Amount();
        customerAmount.setUnit(actualPrice.getUnit());
        customerAmount.setValue(customerPrice.toString());
         return customerAmount;
    }

    public Amount taxDeductedPriceCalculation(Amount customerPrice, BigDecimal taxDeductionRate) {
        BigDecimal customerPriceValue = new BigDecimal(customerPrice.getValue());
        BigDecimal taxDeductedPriceTemp = customerPriceValue.multiply(taxDeductionRate);
        BigDecimal taxDeductedPrice = customerPriceValue.subtract(taxDeductedPriceTemp.divide( new BigDecimal(100)));
        Amount taxDeductedAmount = new Amount();
        taxDeductedAmount.setUnit(customerPrice.getUnit());
        taxDeductedAmount.setValue(taxDeductedPrice.toString());
        return taxDeductedAmount;
    }

    public Amount calculateTaxAmount(Amount customerPrice, Amount taxDeductedServiceCharge) {
        BigDecimal customerPriceValue = new BigDecimal(customerPrice.getValue());
        BigDecimal taxDeductedPriceValue = new BigDecimal(taxDeductedServiceCharge.getValue());
        Amount taxAmount = new Amount();
        taxAmount.setValue(customerPriceValue.subtract(taxDeductedPriceValue).toString());
        taxAmount.setUnit(customerPrice.getUnit());
        return taxAmount;
    }
}
