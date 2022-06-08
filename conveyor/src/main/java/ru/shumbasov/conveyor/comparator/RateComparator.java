package ru.shumbasov.conveyor.comparator;

import ru.shumbasov.conveyor.dto.LoanOfferDTO;

import java.util.Comparator;

public class RateComparator implements Comparator<LoanOfferDTO> {
    @Override
    public int compare(LoanOfferDTO o1, LoanOfferDTO o2) {
        if (o1.getRate().compareTo(o2.getRate()) == -1) {
            return 1;
        } else if (o1.getRate().compareTo(o2.getRate()) == 1) {
            return -1;
        } else {
            return 0;
        }
    }
}
