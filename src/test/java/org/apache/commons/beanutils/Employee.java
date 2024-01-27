package org.apache.commons.beanutils;

import java.util.Map;

public class Employee {
    /**
     * key???home, company
     */
    private Map<String,Object> address;



    public Map<String, Object> getAddress() {
        return address;
    }

    public void setAddress(Map<String, Object> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "address=" + address +
                '}';
    }
}
