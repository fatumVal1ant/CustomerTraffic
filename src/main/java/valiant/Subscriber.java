package valiant;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private String number;
    private float totalCostAllCalls;
    private List<Call> calls;
    private Tariff tariff;

    Subscriber (String Data) {
        String[] callData = Data.split(", ");
        this.number = callData[1];
        this.tariff = new Tariff(callData[4]);
        this.calls = new ArrayList<>();

        try {
            Call call = new Call(callData[0], callData[2], callData[3], tariff);
            this.calls.add(call);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public float getTotalCost() {
        getTotalCostAllCalls();
        return this.tariff.getTotalCost(totalCostAllCalls);
    }

    public String getNumber () {
        return this.number;
    }

    public String getTariffIndex () {
        return this.tariff.getTariffIndex();
    }

    public List<Call> getCalls () {
        return this.calls;
    }

    public void addCall (String Data) {
        String[] callData = Data.split(", ");
        try {
            Call call = new Call(callData[0], callData[2], callData[3], this.tariff);
            this.calls.add(call);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTotalCostAllCalls () {
        this.totalCostAllCalls = 0.0f;
        for (Call call : this.calls) {
            this.totalCostAllCalls += call.getCostCall();
        }
    }
}
