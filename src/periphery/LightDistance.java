package periphery;

public class LightDistance {
    private int distance50;
    private int distance100;

    public LightDistance() {
        this.distance50 = 96;
        this.distance100 = 256;
    }
    public LightDistance(int distance50, int distance100) {
        this.distance50 = distance50;
        this.distance100 = distance100;
    }

    public void setDistance50(int distance50) {
        this.distance50 = distance50;
    }
    public void setDistance100(int distance100) {
        this.distance100 = distance100;
    }
    public LightDistance copy() {
        return new LightDistance(this.distance50, this.distance100);
    }

    public int[] getDistances() {
        return new int[]{this.distance50, this.distance100};
    }
    public String toString() {
        return "distance50:" + this.distance50 + ", distance100:" + this.distance100;
    }
}
