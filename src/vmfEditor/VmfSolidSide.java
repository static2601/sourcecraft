package vmfEditor;

public class VmfSolidSide {

    public int id;
    public double[] plane1 = new double[3];
    public double[] plane2 = new double[3];
    public double[] plane3 = new double[3];
    public double[] plane4 = new double[3];


    public String material;
    public String uAxis;
    public String vAxis;
    public int rotation;
    public int lightMapScale;
    public int smoothingGroups;

    public VmfSolidSide() {
    }
    public String getBrushMaterial() {
        return this.material;
    }
}
