package vmfEditor;

import basic.Loggger;

import java.util.ArrayList;

public class VmfSolid {

    public int id;
    public ArrayList<VmfSolidSide> sides = new ArrayList<>();
    public ArrayList<VmfEditor> editor = new ArrayList<>();

    public VmfSolid() {

    }
    public boolean comparePoints(double[] point1, double[] point2) {
        return(Double.compare(point1[0], point2[0]) == 0
            && Double.compare(point1[1], point2[1]) == 0
            && Double.compare(point1[2], point2[2]) == 0);

    }

    /** Get all 6 sides of solid, for each side,
     * points.add( plane1(x,y,z), plane2(x,y,z), plane3(x,y,z), plane4(x,y,z) )
     * @index index is matched with solid ID to confirm correct solid
     * @return  points arraylist, empty if ids not match*/
    public ArrayList<ArrayList<double[]>> getBrushPoints(int solidId) {
        ArrayList<ArrayList<double[]>> points = new ArrayList<>();
        if(this.id == solidId) {
            for (VmfSolidSide side : this.sides) {
                ArrayList<double[]> pos = new ArrayList<>();
                pos.add(side.plane1);
                pos.add(side.plane2);
                pos.add(side.plane3);
                pos.add(side.plane4);
                points.add(pos);
            }
        }
        return points;
    }
}
