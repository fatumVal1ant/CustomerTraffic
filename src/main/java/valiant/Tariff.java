package valiant;

public class Tariff {
    private String tariffIndex;
    private long totalDuration;

    public Tariff(String tariffIndex) {
        this.tariffIndex = tariffIndex;
        this.totalDuration = 0L;
    }

    public String getTariffIndex() {
        return tariffIndex;
    }

    public float getCost(String typeCall, long duration) {
        if (this.tariffIndex.equalsIgnoreCase("03")) {
            return (float)Math.ceil(duration / 60000f) * 1.5f;

        } else if (this.tariffIndex.equalsIgnoreCase("06")) {
            float firstExpression = (float)Math.floor((totalDuration + duration - 18000000f) / 60000f);
            float secondExpression = (float)Math.floor(duration / 60000f);
            totalDuration += duration;
            return Math.min(Math.max(firstExpression, 0), secondExpression);

        } else {
            if (typeCall.equalsIgnoreCase("01")) {
                float firstExpression = (float)Math.floor((totalDuration + duration - 6000000f) / 60000f);
                float secondExpression = (float)Math.floor((totalDuration - 6000000f) / 60000f);
                float thirdExpression = (float)Math.floor((6000000f - totalDuration) / 60000f);
                float fourthExpression = (float)Math.floor((duration) / 60000f);
                totalDuration += duration;
                return (Math.max(firstExpression, 0) - Math.max(secondExpression, 0)) * 1.5f +
                        Math.max(Math.min(thirdExpression, fourthExpression), 0) * 0.5f;
            } else {
                return 0.0f;
            }
        }
    }

    public float getTotalCost(float cost) {
        if (tariffIndex.equalsIgnoreCase("06")) {
            return 100.0f + cost;
        }
        return cost;
    }
}
