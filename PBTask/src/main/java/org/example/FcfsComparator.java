package org.example;

import java.util.Comparator;

public class FcfsComparator implements Comparator<MyProcess> {
    public int compare(MyProcess p, MyProcess p2) {
        return p.getArrival() - p2.getArrival();

    }
}
